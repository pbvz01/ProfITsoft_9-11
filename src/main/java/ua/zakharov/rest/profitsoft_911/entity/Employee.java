package ua.zakharov.rest.profitsoft_911.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Table(name = "employees")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "The name field must not be empty")
    private String name;

    @Column(name = "lastname")
    @NotEmpty(message = "The lastname field must not be empty")
    private String lastname;

    @Column(name = "email")
    @NotEmpty(message = "The email field must not be empty")
    private String email;

    @Column(name = "phone")
    @NotEmpty(message = "The phone field must not be empty")
    private String phone;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "department_id")
    private Department department;
}
