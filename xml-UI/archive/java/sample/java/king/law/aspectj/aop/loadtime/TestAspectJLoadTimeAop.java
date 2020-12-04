package king.law.aspectj.aop.loadtime;

import king.law.beans.EmployeeManagerLoadTime;
import king.law.dto.EmployeeDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.stringtemplate.v4.gui.STViewFrame;

// refer to https://www.cnblogs.com/wade-luffy/p/6073702.html
public class TestAspectJLoadTimeAop
{
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("loadtimeApplicationContext.xml");
        EmployeeManagerLoadTime manager = context.getBean(EmployeeManagerLoadTime.class);
        manager.getEmployeeById(11);
        manager.createEmployee(new EmployeeDTO());
    }
}
