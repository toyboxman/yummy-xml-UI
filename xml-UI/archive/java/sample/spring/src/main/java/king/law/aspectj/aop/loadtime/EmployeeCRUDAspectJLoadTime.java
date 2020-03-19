package king.law.aspectj.aop.loadtime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class EmployeeCRUDAspectJLoadTime {

    @Before("execution(* king.law.beans.EmployeeManagerLoadTime.getEmployeeById(..))")
    public void logBeforeV1(JoinPoint joinPoint)
    {
        System.out.println("EmployeeCRUDAspectJLoadTime.logBeforeV1() : " + joinPoint.getSignature().getName());
    }

    @Before("execution(* king.law.beans.EmployeeManagerLoadTime.*(..))")
    public void logBeforeV2(JoinPoint joinPoint)
    {
        System.out.println("EmployeeCRUDAspectJLoadTime.logBeforeV2() : " + joinPoint.getSignature().getName());
    }

    @After("execution(* king.law.beans.EmployeeManagerLoadTime.getEmployeeById(..))")
    public void logAfterV1(JoinPoint joinPoint)
    {
        System.out.println("EmployeeCRUDAspectJLoadTime.logAfterV1() : " + joinPoint.getSignature().getName());
    }

    @After("execution(* king.law.beans.EmployeeManagerLoadTime.*(..))")
    public void logAfterV2(JoinPoint joinPoint)
    {
        System.out.println("EmployeeCRUDAspectJLoadTime.logAfterV2() : " + joinPoint.getSignature().getName());
    }
}
