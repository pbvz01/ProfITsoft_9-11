package ua.zakharov.rest.profitsoft_911.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "departments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "The name field must not be empty")
    private String name;

    @Column(name = "location")
    @NotEmpty(message = "The location field must not be empty")
    private String location;

    @Column(name = "city")
    @NotEmpty(message = "The city field must not be empty")
    private String city;

    @Column(name = "country")
    @NotEmpty(message = "The country field must not be empty")
    private String country;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "department",
            fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Employee> employees;

    @Override
    public String toString() {
        return "Department {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
