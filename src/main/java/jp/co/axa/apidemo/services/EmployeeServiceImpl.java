package jp.co.axa.apidemo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.modals.Result;
import jp.co.axa.apidemo.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public Result retrieveEmployees() {
		List<Employee> employees = new ArrayList<Employee>();
		try {
			employees = employeeRepository.findAll();
			return Result.builder().result(employees).isError(Boolean.FALSE).message("SUCCESS").build();

		} catch (Exception ex) {
			return Result.builder().result(employees).isError(Boolean.TRUE).message(ex.getMessage()).build();
		}
	}

	public Result getEmployee(Long employeeId) {
		Optional<Employee> optEmp = null;
		try {
			optEmp = employeeRepository.findById(employeeId);
			return Result.builder().result(optEmp.get()).isError(Boolean.FALSE).message("SUCCESS").build();
		} catch (Exception ex) {
			return Result.builder().result(optEmp).isError(Boolean.TRUE).message(ex.getMessage()).build();
		}
	}

	public Result saveEmployee(Employee employee) {
		Employee emp = null;
		try {
			emp = employeeRepository.save(employee);
			return Result.builder().result(emp).isError(Boolean.FALSE).message("SUCCESS").build();
		} catch (Exception ex) {
			return Result.builder().result(emp).isError(Boolean.TRUE).message(ex.getMessage()).build();
		}

	}

	public Result deleteEmployee(Long employeeId) {
		try {
			employeeRepository.deleteById(employeeId);
			return Result.builder().result(employeeId).isError(Boolean.FALSE).message("SUCCESS").build();
		} catch (Exception ex) {
			return Result.builder().result(null).isError(Boolean.TRUE).message(ex.getMessage()).build();
		}
	}

	public Result updateEmployee(Long employeeId, Employee employee) {
		Employee emp = null;
		try {
			Optional<Employee> optEmp = employeeRepository.findById(employeeId);
			if (optEmp.isPresent()) {
				emp = Employee.builder().id(employeeId).name(employee.getName()).salary(employee.getSalary())
						.department(employee.getDepartment()).build();
				emp = employeeRepository.save(emp);
			}
			return Result.builder().result(emp).isError(Boolean.FALSE).message("SUCCESS").build();
		} catch (Exception ex) {
			return Result.builder().result(emp).isError(Boolean.TRUE).message(ex.getMessage()).build();
		}
	}
}