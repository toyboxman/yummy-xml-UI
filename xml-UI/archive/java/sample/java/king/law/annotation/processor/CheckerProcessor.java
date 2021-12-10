package king.law.annotation.processor;

import king.law.annotation.Checker;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Collections;
import java.util.Set;

/**
 * https://nadundesilva.medium.com/reading-annotations-at-compile-time-using-java-annotation-processors-f5a7604bb2f7
 * https://riptutorial.com/java/example/19926/compile-time-processing-using-annotation-processor
 *
 * 1. Create annotation Checker
 * 2. Create CheckerProcessor.
 * 3. Append FQN(king.law.annotation.processor.CheckerProcessor) of the processor in META-INF/services/javax.annotation.processing.Processor.
 * 4. javac -cp processor.jar java_src
 */
public class CheckerProcessor extends AbstractProcessor {
    private Messager messager;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // get elements annotated with the @Checker annotation
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(Checker.class);

        for (Element element : annotatedElements) {
            if (element.getKind() == ElementKind.METHOD) {
                // only handle methods as targets
                checkMethod((ExecutableElement) element);
            }
        }

        // don't claim annotations to allow other processors to process them
        return false;
    }

    private void checkMethod(ExecutableElement method) {
        // check for valid name
        String name = method.getSimpleName().toString();
        if (!name.startsWith("set")) {
            printError(method, "setter name must start with \"set\"");
        } else if (name.length() == 3) {
            printError(method, "the method name must contain more than just \"set\"");
        } else if (Character.isLowerCase(name.charAt(3))) {
            if (method.getParameters().size() != 1) {
                printError(method, "character following \"set\" must be upper case");
            }
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

    private void printError(Element element, String message) {
        messager.printMessage(Diagnostic.Kind.ERROR, message, element);
    }

    @Override
    public void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);

        // get messager for printing errors
        messager = processingEnvironment.getMessager();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        //必须返回annotation类型，否则processor不会对指定标签生效
        return Collections.singleton(Checker.class.getName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        //必须返回java版本类型，默认是1.6,否则processor不会处理高版本文件
        return SourceVersion.latestSupported();
    }
}
