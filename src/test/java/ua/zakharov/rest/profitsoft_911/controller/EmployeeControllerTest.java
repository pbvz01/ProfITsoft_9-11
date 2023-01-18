package ua.zakharov.rest.profitsoft_911.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ua.zakharov.rest.profitsoft_911.entity.Department;
import ua.zakharov.rest.profitsoft_911.entity.Employee;
import ua.zakharov.rest.profitsoft_911.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ObjectMapper objMapper;

    @BeforeEach
    void setup(){
        employeeRepository.deleteAll();
    }

    @Test
    public void givenListOfEmployees_whenGetAllEmployees_thenReturnEmployeesList() throws Exception{
        List<Employee> employees = new ArrayList<>();
        employees.add(
                Employee.builder()
                        .name("Ramesh")
                        .lastname("Fadatare")
                        .email("ramesh@gmail.com")
                        .phone("123456789")
                        .department(Department.builder()
                                .location("Location1")
                                .country("Country1")
                                .name("Department1")
                                .city("City1")
                                .build())
                        .build());
        employees.add(
                Employee.builder()
                        .name("Tony")
                        .lastname("Stark")
                        .email("tony@gmail.com")
                        .phone("123456789")
                        .department(Department.builder()
                                .location("Location1")
                                .country("Country1")
                                .name("Department1")
                                .city("City1")
                                .build())
                        .build());
        employeeRepository.saveAll(employees);

        ResultActions response = mockMvc.perform(get("/api/employee"));

        response.andExpect(status().isFound())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(employees.size())));

    }

    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() throws Exception{
        Employee employee = Employee.builder()
                .name("Ramesh")
                .lastname("Fadatare")
                .email("ramesh@gmail.com")
                .phone("123456789")
                .department(Department.builder()
                        .location("Location1")
                        .country("Country1")
                        .name("Department1")
                        .city("City1")
                        .build())
                .build();
        employeeRepository.save(employee);

        ResultActions response = mockMvc.perform(get("/api/employee/{id}", employee.getId()));

        response.andExpect(status().isFound())
                .andDo(print())
                .andExpect(jsonPath("$.name", is(employee.getName())))
                .andExpect(jsonPath("$.lastname", is(employee.getLastname())))
                .andExpect(jsonPath("$.email", is(employee.getEmail())));

    }

    @Test
    public void givenEmployeeObject_whenCreateEmployee_thenReturnSavedEmployee() throws Exception{
        long department_id = 1L;

        Employee employee = Employee.builder()
                .name("Ramesh")
                .lastname("Fadatare")
                .email("ramesh@gmail.com")
                .phone("123456789")
                .department(Department.builder()
                        .id(department_id)
                        .location("Location1")
                        .country("Country1")
                        .name("Department1")
                        .city("City1")
                        .build())
                .build();

        ResultActions response = mockMvc.perform(post("/api/employee/{id}",department_id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objMapper.writeValueAsString(employee)));

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name",
                        is(employee.getName())))
                .andExpect(jsonPath("$.lastname",
                        is(employee.getLastname())))
                .andExpect(jsonPath("$.email",
                        is(employee.getEmail())))
                .andExpect(jsonPath("$.phone",
                        is(employee.getPhone())));
    }

    @Test
    public void givenUpdatedEmployee_whenUpdateEmployee_thenReturn404() throws Exception{
        Employee savedEmployee = Employee.builder()
                .name("Ramesh")
                .lastname("Fadatare")
                .email("ramesh@gmail.com")
                .phone("123456789")
                .department(Department.builder()
                        .location("Location1")
                        .country("Country1")
                        .name("Department1")
                        .city("City1")
                        .build())
                .build();
        employeeRepository.save(savedEmployee);

        Employee updatedEmployee = Employee.builder()
                .id(savedEmployee.getId())
                .name("Ram")
                .lastname("Jadhav")
                .email("ram@gmail.com")
                .phone("987654321")
                .department(Department.builder()
                        .location("Location1")
                        .country("Country1")
                        .name("Department1")
                        .city("City1")
                        .build())
                .build();

        employeeRepository.save(updatedEmployee);

        ResultActions response = mockMvc.perform(put("/api/employee/{id}", savedEmployee.getDepartment().getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objMapper.writeValueAsString(updatedEmployee)));

        response.andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.name", is(updatedEmployee.getName())))
                .andExpect(jsonPath("$.lastname", is(updatedEmployee.getLastname())))
                .andExpect(jsonPath("$.email", is(updatedEmployee.getEmail())))
                .andExpect(jsonPath("$.phone", is(updatedEmployee.getPhone())));
    }

    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenReturn200() throws Exception{
        Employee savedEmployee = Employee.builder()
                .name("Ramesh")
                .lastname("Fadatare")
                .email("ramesh@gmail.com")
                .phone("123456789")
                .department(Department.builder()
                        .location("Location1")
                        .country("Country1")
                        .name("Department1")
                        .city("City1")
                        .build())
                .build();
        employeeRepository.save(savedEmployee);

        ResultActions response = mockMvc.perform(delete("/api/employee/{id}", savedEmployee.getId()));

        response.andExpect(status().isCreated())
                .andDo(print());
    }

}