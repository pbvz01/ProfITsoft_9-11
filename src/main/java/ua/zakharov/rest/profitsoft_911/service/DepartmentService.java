package ua.zakharov.rest.profitsoft_911.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.zakharov.rest.profitsoft_911.aop.exception_handling.exception.DepartmentNotFoundException;
import ua.zakharov.rest.profitsoft_911.entity.Department;
import ua.zakharov.rest.profitsoft_911.repository.DepartmentRepository;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Department findDepartmentById(Long id) {
        return departmentRepository
                .findById(id)
                .orElseThrow(DepartmentNotFoundException::new);
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }
}
