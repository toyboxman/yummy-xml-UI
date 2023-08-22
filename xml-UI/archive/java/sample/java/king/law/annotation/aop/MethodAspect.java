package king.law.annotation.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

//增加新的aspect定义就需要在pom的aspectj-maven-plugin定义中指定，否则不会对源码做织入
//mvn aspectj:compile exec:java -Dexec.mainClass=king.law.annotation.aop.TraceTarget -f pom.xml
@Aspect
public class MethodAspect {
    @Pointcut(value = "execution(* king.law.annotation.aop.TraceTarget.hearSomething(..))")
    public void methodPointCut() {

    }

    @Before(value = "methodPointCut()") // before/after织入的action会在around织入行为前面和后面
    public void doActionBefore(JoinPoint pjp) throws Throwable {
        System.out.println("*** extra before method action @" + pjp.getSignature().getName());
    }

    @Around(value = "methodPointCut()")
    public Object doAction(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("*** extra around method action @" + pjp.getSignature().getName());
        return pjp.proceed();
    }
}
