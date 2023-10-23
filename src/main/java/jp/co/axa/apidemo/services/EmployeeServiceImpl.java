package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{


    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> retrieveEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        return employees;
    }

    public Employee getEmployee(Long employeeId) {
        Optional<Employee> optEmp = employeeRepository.findById(employeeId);
        return optEmp.get();
    }

    public Employee saveEmployee(Employee employee){

        Employee result = employeeRepository.save(employee);
        return result;
    }

    public Boolean deleteEmployee(Long employeeId){
        try{
            employeeRepository.deleteById(employeeId);
            return Boolean.TRUE;
        }catch(Exception ex){
            return Boolean.FALSE;
        }
    }

    public Employee updateEmployee(Employee employee) {
        Employee result = employeeRepository.save(employee);
        return result;
    }
}