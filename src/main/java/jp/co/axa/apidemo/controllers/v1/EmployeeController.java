package jp.co.axa.apidemo.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.modals.ApiResponse;
import jp.co.axa.apidemo.modals.Result;
import jp.co.axa.apidemo.services.EmployeeService;
import jp.co.axa.apidemo.utils.ApiConstants;

@RestController
@RequestMapping(ApiConstants.EMPLOYEE_CONTROLLER_MAPPING+ApiConstants.VERSION_ONE_MAPPING)
public class EmployeeController implements jp.co.axa.apidemo.controllers.EmployeeController {

	final static String EMPLOYEE_MAPPING = ApiConstants.EMPLOYEE_MAPPING;
	final static String RESOURCE_MAPPING = EMPLOYEE_MAPPING + "/{employeeId}";

	public static final String API_SUCCESS_MESSAGE = ApiConstants.API_SUCCESS_MESSAGE;

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping(EMPLOYEE_MAPPING)
	public ApiResponse getEmployees() {
		Result result = employeeService.retrieveEmployees();
		return ApiResponse.builder().data(result).status(HttpStatus.OK.toString()).message(API_SUCCESS_MESSAGE)
				.build();
	}

	@GetMapping(RESOURCE_MAPPING)
	public ApiResponse getEmployee(@PathVariable(name = "employeeId") Long employeeId) {

		Result result = employeeService.getEmployee(employeeId);

		return ApiResponse.builder().data(result).status(HttpStatus.OK.toString())
				.message(API_SUCCESS_MESSAGE).build();
	}

	@PostMapping(EMPLOYEE_MAPPING)
	public ApiResponse saveEmployee(@RequestBody Employee employee) {
		Result result = employeeService.saveEmployee(employee);
		return ApiResponse.builder().data(result).status(HttpStatus.OK.toString()).message(API_SUCCESS_MESSAGE).build();
	}

	@DeleteMapping(RESOURCE_MAPPING)
	public ApiResponse deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {
		Result result = employeeService.deleteEmployee(employeeId);
		return ApiResponse.builder().data(result).status(HttpStatus.OK.toString()).message(API_SUCCESS_MESSAGE)
				.build();
	}

	@PutMapping(RESOURCE_MAPPING)
	public ApiResponse updateEmployee(@RequestBody Employee employee,
			@PathVariable(name = "employeeId") Long employeeId) {
			Result result = employeeService.updateEmployee(employeeId,employee);
		return ApiResponse.builder().data(result).status(HttpStatus.OK.toString()).message(API_SUCCESS_MESSAGE)
				.build();
	}

}
