package ua.zakharov.rest.profitsoft_911.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.zakharov.rest.profitsoft_911.entity.Department;
import ua.zakharov.rest.profitsoft_911.service.DepartmentService;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<Iterable<Department>> getAllDepartments() {
        return new ResponseEntity<>(departmentService.findAllDepartments(), HttpStatus.FOUND);
    }

}
