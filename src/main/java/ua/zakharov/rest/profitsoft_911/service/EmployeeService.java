package ua.zakharov.rest.profitsoft_911.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.zakharov.rest.profitsoft_911.aop.exception_handling.exception.EmployeeNotFoundException;
import ua.zakharov.rest.profitsoft_911.entity.Department;
import ua.zakharov.rest.profitsoft_911.entity.Employee;
import ua.zakharov.rest.profitsoft_911.repository.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentService departmentService;
    //Get
    public Iterable<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }
    public Employee findEmployeeById(Long id) {
        return employeeRepository
                .findById(id)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    //Post
    public Employee saveEmployee(Employee employee, Long department_id) {
        Department department = departmentService.findDepartmentById(department_id);
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

//    //Put
//    public Employee updateEmployee(Employee employee, Long department_id) {
//        Department department = departmentService.findDepartmentById(department_id);
//        Employee emp = employeeRepository.findById(employee.getId()).orElseThrow(EmployeeNotFoundException::new);
//        return employeeRepository.save(employee);
//    }

    //Delete
    public void deleteEmployeeById(Long id) {
        Employee employee = findEmployeeById(id);
        employeeRepository.delete(employee);
    }
}
