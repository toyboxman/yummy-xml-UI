package king.law.spring.aop;

import king.law.beans.EmployeeManagerProxy;
import king.law.dto.EmployeeDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringAop
{
    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        EmployeeManagerProxy manager = context.getBean(EmployeeManagerProxy.class);

        manager.getEmployeeById(11);
        manager.createEmployee(new EmployeeDTO());
    }
}
