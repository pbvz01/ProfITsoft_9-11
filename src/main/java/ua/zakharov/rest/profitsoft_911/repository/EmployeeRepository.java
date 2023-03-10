package ua.zakharov.rest.profitsoft_911.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.zakharov.rest.profitsoft_911.entity.Employee;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findById(Long id);
    Optional<Page<Employee>> findAllByNameAndLastname(String name, String lastname, Pageable pageable);
}
