package king.law.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.SuppressAjWarnings;

@Aspect
@SuppressAjWarnings
public class CheckMeInCompile {
    @Before("execution(* king.law.beans.EmployeeManagerCompile.*(..))")
    public void logBeforeV2(JoinPoint joinPoint) {
        System.out.println("EmployeeCRUDAspectJCompile.logBeforeV2() : " + joinPoint.getSignature().getName());
    }

    @After("execution(* king.law.beans.EmployeeManagerCompile.getEmployeeById(..))")
    public void logAfterV1(JoinPoint joinPoint) {
        System.out.println("EmployeeCRUDAspectJCompile.logAfterV1() : " + joinPoint.getSignature().getName());
    }

    public static void main(String[] args) {
        A1 a1 = new A1();//此处编译会报错 Users/gene/source/nbProjects/spring/src/main/java/king/law/annotation/CheckMeInCompile.java:20:17
        //java: setter name must start with "set"
        a1.setMethod();
        a1.getMethod();
        System.out.println("A1");
    }
}

class A1 {

    @Checker
    public void setMethod() {

    }

    /**
     * 去掉注释就会提示编译错误
     */
//    @Checker
    public void getMethod() {

    }
}
