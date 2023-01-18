package ua.zakharov.rest.profitsoft_911.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.zakharov.rest.profitsoft_911.aop.exception_handling.exception.EmployeeNotFoundException;
import ua.zakharov.rest.profitsoft_911.entity.Department;
import ua.zakharov.rest.profitsoft_911.entity.Employee;
import ua.zakharov.rest.profitsoft_911.repository.EmployeeRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentService departmentService;

    public Iterable<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }
    public Employee findEmployeeById(Long id) {
        return employeeRepository
                .findById(id)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public Map<String, Object> findEmployeeByNameAndLastnameWithPagination(
            String name, String lastname, Pageable pageable) {
        Page<Employee> employeePage = employeeRepository
                .findAllByNameAndLastname(name, lastname, pageable)
                .orElseThrow(EmployeeNotFoundException::new);

        Map<String, Object> response = new HashMap<>();
        response.put("employees", employeePage.getContent());
        response.put("currentPage", employeePage.getNumber());
        response.put("totalItems", employeePage.getTotalElements());
        response.put("totalPages", employeePage.getTotalPages());
        return response;

    }

    public Employee saveEmployee(Employee employee, Long department_id) {
        Department department = departmentService.findDepartmentById(department_id);
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee, Long department_id) {
        Department department = departmentService.findDepartmentById(department_id);
        Employee emp = employeeRepository.findById(employee.getId()).orElseThrow(EmployeeNotFoundException::new);
        return employeeRepository.save(employee);
    }

    public void deleteEmployeeById(Long id) {
        Employee employee = findEmployeeById(id);
        employeeRepository.delete(employee);
    }
}
