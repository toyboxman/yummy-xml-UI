package king.law.annotation.processor;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.SuppressAjWarnings;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class CheckAspectDef extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // get elements annotated with the @Aspect annotation
        Set<? extends Element> annotatedElements1 = roundEnv
                .getElementsAnnotatedWith(Aspect.class);
        Set<? extends Element> annotatedElements2 = roundEnv
                .getElementsAnnotatedWith(SuppressAjWarnings.class);
        Set<? extends Element> annotatedElements = annotatedElements1.stream().filter(
                element -> annotatedElements2.contains(element))
                .collect(Collectors.toSet());

        for (Element element : annotatedElements) {
            if (element.getKind() == ElementKind.METHOD) {
                // ExecutableElement是表示method, constructor, or initializer的模型
                //checkMethod((ExecutableElement) element);
            }
            if (element.getKind() == ElementKind.CLASS) {
                checkClass((TypeElement) element);
            }
        }
        return false;
    }

    private void checkClass(TypeElement class_element) {
        Name name = class_element.getQualifiedName();
        try {
            Class<?> target_class = Class.forName(name.toString());
            Method[] methods = target_class.getDeclaredMethods();
            if (methods != null) {
                Set<Method> set = Arrays.stream(methods).filter(
                                m -> (m.getDeclaredAnnotation(Before.class) != null)
                        || (m.getDeclaredAnnotation(After.class) != null))
                        .collect(Collectors.toSet());
                Set<String> def_set = set.stream().map(m -> {
                            return m.getDeclaredAnnotation(Before.class) != null ?
                                    m.getDeclaredAnnotation(Before.class).value() :
                                    m.getDeclaredAnnotation(After.class).value();
                        })
                        .collect(Collectors.toSet());
                Set<String> method_set = def_set.stream().map(
                                def -> {
                                    String substring = def.substring(
                                            0, def.lastIndexOf('('));
                                    return substring.substring(substring.lastIndexOf('.') + 1);
                                })
                        .collect(Collectors.toSet());
                printWarn(class_element, def_set.toString());
                printWarn(class_element, method_set.toString());
            }
        } catch (ClassNotFoundException e) {
            printWarn(class_element, e.toString());
        }
    }

    private void checkMethod(ExecutableElement method) {
        // 得到当前method的标签对象
        Before annotation = method.getAnnotation(Before.class);
        String value = annotation.value();
        try {
            Class<?> targetClass = Class.forName("king.law.beans.EmployeeManagerCompile");
            Set<String> nameSet = Arrays.stream(targetClass.getDeclaredMethods())
                    .parallel().map(Method::getName).collect(Collectors.toSet());
            if (!nameSet.contains("getEmployeeById12")) {
                printError(method, "do not contain getEmployeeById123 method");
            }
        } catch (ClassNotFoundException e) {
            printWarn(method, e.toString());
        }

        // check, if setter is public
        if (!method.getModifiers().contains(Modifier.PUBLIC)) {
            printError(method, "setter must be public");
        }

        // check, if method is static
        if (method.getModifiers().contains(Modifier.STATIC)) {
            printError(method, "setter must not be static");
        }
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(Before.class.getName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private void printError(Element element, String message) {
        messager.printMessage(Diagnostic.Kind.ERROR, message, element);
    }

    private void printWarn(Element element, String message) {
        messager.printMessage(Diagnostic.Kind.MANDATORY_WARNING, message, element);
    }

    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
    }
}
