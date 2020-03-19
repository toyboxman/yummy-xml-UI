package king.law.aspectj.aop.compile;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class EmployeeCRUDAspectJCompile {

    @Before("execution(* king.law.beans.EmployeeManagerCompile.getEmployeeById(..))")
    public void logBeforeV1(JoinPoint joinPoint)
    {
        System.out.println("EmployeeCRUDAspectJCompile.logBeforeV1() : " + joinPoint.getSignature().getName());
    }

    @Before("execution(* king.law.beans.EmployeeManagerCompile.*(..))")
    public void logBeforeV2(JoinPoint joinPoint)
    {
        System.out.println("EmployeeCRUDAspectJCompile.logBeforeV2() : " + joinPoint.getSignature().getName());
    }

    @After("execution(* king.law.beans.EmployeeManagerCompile.getEmployeeById(..))")
    public void logAfterV1(JoinPoint joinPoint)
    {
        System.out.println("EmployeeCRUDAspectJCompile.logAfterV1() : " + joinPoint.getSignature().getName());
    }

    @After("execution(* king.law.beans.EmployeeManagerCompile.*(..))")
    public void logAfterV2(JoinPoint joinPoint)
    {
        System.out.println("EmployeeCRUDAspectJCompile.logAfterV2() : " + joinPoint.getSignature().getName());
    }
}
