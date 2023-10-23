package jp.co.axa.apidemo.controllers.v1;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.modals.ApiResponse;
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
        return ApiResponse.builder().data(employees).status(HttpStatus.OK.toString()).message("SUCCESS").build();
    }

    public ApiResponse getEmployee(@PathVariable(name="employeeId")Long employeeId) {
        return ApiResponse.builder().data(employeeService.getEmployee(employeeId)).status(HttpStatus.OK.toString()).message("SUCCESS").build();
    }


    public ApiResponse saveEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return ApiResponse.builder().data(employee).status(HttpStatus.OK.toString()).message("SUCCESS").build();
    }


    public ApiResponse deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ApiResponse.builder().data(employeeId).status(HttpStatus.OK.toString()).message("SUCCESS").build();
    }


    public ApiResponse updateEmployee(@RequestBody Employee employee,
                               @PathVariable(name="employeeId")Long employeeId){
        Employee emp = employeeService.getEmployee(employeeId);
        if(emp != null){
            employeeService.updateEmployee(employee);
        }
        return ApiResponse.builder().data(employeeId).status(HttpStatus.OK.toString()).message("SUCCESS").build();
    }

}
