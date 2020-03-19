package king.law.aspectj.aop.compile;

import king.law.beans.EmployeeManagerCompile;
import king.law.dto.EmployeeDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAspectJCompileAop
{
    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("staticApplicationContext.xml");
        EmployeeManagerCompile manager = context.getBean(EmployeeManagerCompile.class);

        manager.getEmployeeById(11);
        manager.createEmployee(new EmployeeDTO());
    }
}
