package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.modals.Result;


public interface EmployeeService {

     Result retrieveEmployees();

     Result getEmployee(Long employeeId);

     Result saveEmployee(Employee employee);

     Result deleteEmployee(Long employeeId);

     Result updateEmployee(Long employeeId,Employee employee);
}