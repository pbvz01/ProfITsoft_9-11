package ua.zakharov.rest.profitsoft_911.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ua.zakharov.rest.profitsoft_911.entity.Department;
import ua.zakharov.rest.profitsoft_911.repository.DepartmentRepository;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class DepartmentControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setup(){
        departmentRepository.deleteAll();
    }

    @Test
    public void givenListOfDepartaments_whenGetAllEmployees_thenReturnDepList() throws Exception{
        List<Department> departments = new ArrayList<>();
        departments.add(Department.builder().name("Departmane1").city("City1").location("Location1").country("Country1").build());
        departments.add(Department.builder().name("Departmane2").city("City2").location("Location2").country("Country2").build());
        departmentRepository.saveAll(departments);

        ResultActions response = mockMvc.perform(get("/api/department"));

        response.andExpect(status().isFound())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(departments.size())));

    }

}