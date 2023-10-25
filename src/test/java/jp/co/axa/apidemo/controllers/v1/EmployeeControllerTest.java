package jp.co.axa.apidemo.controllers.v1;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.modals.Result;
import jp.co.axa.apidemo.services.EmployeeService;

@WebMvcTest(EmployeeController.class)
@RunWith(SpringRunner.class)
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private EmployeeService employeeService;

	private Result getEmployeeList() {
		List<Employee> list = new ArrayList<Employee>();
		list.add(
				Employee.builder().id(new Long("1")).name("abc1").salary(new Integer("100")).department("ID1").build());
		list.add(
				Employee.builder().id(new Long("2")).name("abc2").salary(new Integer("200")).department("ID2").build());
		list.add(
				Employee.builder().id(new Long("3")).name("abc3").salary(new Integer("300")).department("ID3").build());
		list.add(
				Employee.builder().id(new Long("4")).name("abc4").salary(new Integer("400")).department("ID4").build());
		list.add(
				Employee.builder().id(new Long("5")).name("abc5").salary(new Integer("500")).department("ID5").build());
		list.add(
				Employee.builder().id(new Long("6")).name("abc6").salary(new Integer("600")).department("ID6").build());
		list.add(
				Employee.builder().id(new Long("7")).name("abc7").salary(new Integer("700")).department("ID7").build());
		return Result.builder().result(list).isError(Boolean.FALSE).message("SUCCESS").build();
	}

	private Result getEmployeeList(Long employeeId) {
		Map<Long, Result> list = new HashMap<Long, Result>();
		list.put(new Long("1"), Result.builder().result(
				Employee.builder().id(new Long("1")).name("abc1").salary(new Integer("100")).department("ID1").build())
				.isError(Boolean.FALSE).message("SUCCESS").build());
		list.put(new Long("2"), Result.builder().result(
				Employee.builder().id(new Long("2")).name("abc2").salary(new Integer("200")).department("ID2").build())
				.isError(Boolean.FALSE).message("SUCCESS").build());
		list.put(new Long("3"), Result.builder().result(
				Employee.builder().id(new Long("3")).name("abc3").salary(new Integer("300")).department("ID3").build())
				.isError(Boolean.FALSE).message("SUCCESS").build());
		list.put(new Long("4"), Result.builder().result(
				Employee.builder().id(new Long("4")).name("abc4").salary(new Integer("400")).department("ID4").build())
				.isError(Boolean.FALSE).message("SUCCESS").build());
		list.put(new Long("5"), Result.builder().result(
				Employee.builder().id(new Long("5")).name("abc5").salary(new Integer("500")).department("ID5").build())
				.isError(Boolean.FALSE).message("SUCCESS").build());
		list.put(new Long("6"), Result.builder().result(
				Employee.builder().id(new Long("6")).name("abc6").salary(new Integer("600")).department("ID6").build())
				.isError(Boolean.FALSE).message("SUCCESS").build());
		list.put(new Long("7"), Result.builder().result(
				Employee.builder().id(new Long("7")).name("abc7").salary(new Integer("700")).department("ID7").build())
				.isError(Boolean.FALSE).message("SUCCESS").build());
		return list.get(employeeId);
	}

	@Test
	public void test_GET_EMPLOYEES() {
		Mockito.when(employeeService.retrieveEmployees())
				.thenReturn(getEmployeeList());
		try {
			mvc.perform(get("/api/v1/employees")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content()
							.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.jsonPath("$.data.result.length()", is(7)))
					.andExpect(MockMvcResultMatchers.jsonPath("$.data.result[0].name", is("abc1")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.data.result[2].id", is(3)))
					.andExpect(MockMvcResultMatchers.jsonPath("$.data.result[4].salary", is(500)))
					.andExpect(MockMvcResultMatchers.jsonPath("$.data.result[6].department", is("ID7")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.data.message", is("SUCCESS")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.message", is("SUCCESS")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.status", is("200 OK")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test_GET_EMPLOYEE_BY_EMPLOYEE_ID() {
		Long employeeId = new Long("3");
		Mockito.when(employeeService.getEmployee(employeeId))
				.thenReturn(getEmployeeList(employeeId));
		try {
			mvc.perform(get("/api/v1/employees/3")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content()
							.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.jsonPath("$.data.result.name", is("abc3")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.data.result.salary", is(300)))
					.andExpect(MockMvcResultMatchers.jsonPath("$.data.result.department", is("ID3")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.data.message", is("SUCCESS")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.message", is("SUCCESS")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.status", is("200 OK")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
