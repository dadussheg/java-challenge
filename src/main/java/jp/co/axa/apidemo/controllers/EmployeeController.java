package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.modals.ApiResponse;
import jp.co.axa.apidemo.utils.ApiConstants;
import org.springframework.web.bind.annotation.*;


@RequestMapping(ApiConstants.EMPLOYEE_CONTROLLER_MAPPING)
public interface EmployeeController {

    String EMPLOYEE_MAPPING = ApiConstants.EMPLOYEE_MAPPING;
    String RESOURCE_MAPPING = EMPLOYEE_MAPPING+"/{employeeId}";

    @GetMapping(EMPLOYEE_MAPPING)
     ApiResponse getEmployees();

    @GetMapping(RESOURCE_MAPPING)
     ApiResponse getEmployee(@PathVariable(name="employeeId")Long employeeId);

    @PostMapping(EMPLOYEE_MAPPING)
    ApiResponse saveEmployee(Employee employee);

    @DeleteMapping(RESOURCE_MAPPING)
    ApiResponse deleteEmployee(@PathVariable(name="employeeId")Long employeeId);

    @PutMapping(RESOURCE_MAPPING)
    ApiResponse updateEmployee(@RequestBody Employee employee,
                               @PathVariable(name="employeeId")Long employeeId);
}
