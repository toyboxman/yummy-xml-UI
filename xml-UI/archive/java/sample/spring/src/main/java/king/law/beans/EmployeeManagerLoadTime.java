package king.law.beans;

import king.law.dto.EmployeeDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// refer to https://www.cnblogs.com/wade-luffy/p/6073702.html
@Component
public class EmployeeManagerLoadTime
{
    public EmployeeDTO getEmployeeById(Integer employeeId) {
        System.out.println("Method getEmployeeById() called");
        EmployeeDTO employeeDTO = new EmployeeDTO();
        updateEmployee(employeeDTO);
        return employeeDTO;
    }

    public List<EmployeeDTO> getAllEmployee() {
        System.out.println("Method getAllEmployee() called");
        return new ArrayList<EmployeeDTO>();
    }

    public void createEmployee(EmployeeDTO employee) {
        System.out.println("Method createEmployee() called");
    }

    public void deleteEmployee(Integer employeeId) {
        System.out.println("Method deleteEmployee() called");
    }

    public void updateEmployee(EmployeeDTO employee) {
        System.out.println("Method updateEmployee() called");
    }
}
