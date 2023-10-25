package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.modals.ApiResponse;

public interface EmployeeController {

	ApiResponse getEmployees();

	ApiResponse getEmployee(Long employeeId);

	ApiResponse saveEmployee(Employee employee);

	ApiResponse deleteEmployee(Long employeeId);

	ApiResponse updateEmployee(Employee employee,
			Long employeeId);
}
