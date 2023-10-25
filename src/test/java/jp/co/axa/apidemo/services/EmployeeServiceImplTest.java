package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.modals.Result;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeServiceImpl;

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

    private Optional<Employee> getEmployeeList(Long employeeId) {
        Map<Long, Optional<Employee>> list = new HashMap<Long, Optional<Employee>>();
        list.put(new Long("1"),
                Optional.of(Employee.builder().id(new Long("1")).name("abc1").salary(new Integer("100")).department("ID1").build()));
        list.put(new Long("2"),
                Optional.of(Employee.builder().id(new Long("2")).name("abc2").salary(new Integer("200")).department("ID2").build()));
        list.put(new Long("3"),
                Optional.of(Employee.builder().id(new Long("3")).name("abc3").salary(new Integer("300")).department("ID3").build()));
        list.put(new Long("4"),
                Optional.of(Employee.builder().id(new Long("4")).name("abc4").salary(new Integer("400")).department("ID4").build()));
        list.put(new Long("5"),
                Optional.of(Employee.builder().id(new Long("5")).name("abc5").salary(new Integer("500")).department("ID5").build()));
        list.put(new Long("6"),
                Optional.of(Employee.builder().id(new Long("6")).name("abc6").salary(new Integer("600")).department("ID6").build()));
        list.put(new Long("7"),
                Optional.of(Employee.builder().id(new Long("7")).name("abc7").salary(new Integer("700")).department("ID7").build()));
        return list.get(employeeId);
    }

    @Test
    public void test_GET_EMPLOYEE_BY_EMPLOYEE_ID() {
        Long employeeId = new Long("3");
        Mockito.when(employeeRepository.findById(employeeId))
                .thenReturn(getEmployeeList(employeeId));
        Result result = employeeServiceImpl.getEmployee(employeeId);
        verify(employeeRepository,times(1)).findById(employeeId);
        assertEquals(Boolean.FALSE,result.getIsError());
        assertEquals("abc3",((Employee)result.getResult()).getName());
        assertEquals("ID3",((Employee)result.getResult()).getDepartment());
        assertEquals(new Integer(300),((Employee)result.getResult()).getSalary());
    }
}
