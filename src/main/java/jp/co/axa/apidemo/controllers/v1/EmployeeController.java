package jp.co.axa.apidemo.controllers.v1;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.modals.ApiResponse;
import jp.co.axa.apidemo.modals.ImmutableApiResponse;
import jp.co.axa.apidemo.services.EmployeeService;
import jp.co.axa.apidemo.utils.ApiConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(ApiConstants.VERSION_ONE_MAPPING)
public class EmployeeController implements jp.co.axa.apidemo.controllers.EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public ApiResponse getEmployees() {
        List<Employee> employees = employeeService.retrieveEmployees();
        List<Employee> list = new ArrayList<>();
        list.add(new Employee());
        return ImmutableApiResponse.builder().data(list).status(HttpStatus.OK.toString()).message("SUCCESS").build();
    }

    public Employee getEmployee(@PathVariable(name="employeeId")Long employeeId) {
        return employeeService.getEmployee(employeeId);
    }


    public void saveEmployee(Employee employee){
        employeeService.saveEmployee(employee);
        System.out.println("Employee Saved Successfully");
    }


    public void deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
        employeeService.deleteEmployee(employeeId);
        System.out.println("Employee Deleted Successfully");
    }


    public void updateEmployee(@RequestBody Employee employee,
                               @PathVariable(name="employeeId")Long employeeId){
        Employee emp = employeeService.getEmployee(employeeId);
        if(emp != null){
            employeeService.updateEmployee(employee);
        }

    }

}
