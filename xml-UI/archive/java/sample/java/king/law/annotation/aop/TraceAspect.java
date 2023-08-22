package king.law.annotation.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.reflect.InvocationTargetException;

//增加新的aspect定义就需要在pom的aspectj-maven-plugin定义中指定，否则不会对源码做织入
//mvn aspectj:compile exec:java -Dexec.mainClass=king.law.annotation.aop.TraceTarget -f pom.xml
@Aspect
public class TraceAspect {
    @Pointcut(value = "execution(@king.law.annotation.aop.Trace * *(..))")
    public void tracePointCut() {

    }

    @Before(value = "tracePointCut() && @annotation(trace)") // 仅仅Trace调用使用时候触发
//    @Before(value = "@annotation(trace)") // Trace调用和本身的class初始化都会触发
    public void traceStart(JoinPoint joinPoint, Trace trace) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException, NoSuchMethodException,
            InvocationTargetException {
        System.out.println("start trace " + joinPoint.getSignature().getName());
        System.out.println("annotation " + trace.value());
        if (trace.log()) {
            System.out.println("log a record here");
        }
        if (!trace.visit().getName().equals(Trace.Visitor.class.getName())) {
            System.out.println(trace.visit().getName());
            Class<?> visitor = Class.forName(trace.visit().getName());
//            System.out.println(visitor);
//            Arrays.stream(visitor.getDeclaredConstructors()).forEach(System.out::println);
//            Arrays.stream(visitor.getDeclaredMethods()).forEach(System.out::println);
            Trace.Visitor v = (Trace.Visitor) visitor.newInstance();
            v.visit(joinPoint);
        }
    }

    @After(value = "tracePointCut() && @annotation(trace)") // 仅仅Trace调用使用时候触发
//    @Before(value = "@annotation(trace)") // Trace调用和本身的class初始化都会触发
    public void traceEnd(JoinPoint joinPoint, Trace trace) {
        System.out.println("finish trace " + joinPoint.getSignature().getName());
        if (trace.log()) {
            System.out.println("log a record here");
        }
        System.out.println("\n----------\n");
    }
}
