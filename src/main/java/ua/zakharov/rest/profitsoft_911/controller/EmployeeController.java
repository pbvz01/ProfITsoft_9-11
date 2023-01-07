package ua.zakharov.rest.profitsoft_911.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.zakharov.rest.profitsoft_911.entity.Employee;
import ua.zakharov.rest.profitsoft_911.service.EmployeeService;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Iterable<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.findAllEmployees(), HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) {
        return new ResponseEntity<>(employeeService.findEmployeeById(id), HttpStatus.FOUND);
    }

    @PostMapping("/{department_id}")
    public ResponseEntity<Employee> createEmployee(
            @RequestBody @Valid Employee employee,
            @PathVariable("department_id") Long id) {
        return new ResponseEntity<>(employeeService.saveEmployee(employee, id), HttpStatus.CREATED);
    }

    //вававав
//    @PutMapping
//    public ResponseEntity<Employee> updateEmployee(@RequestBody @Valid Employee employee) {
//        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}