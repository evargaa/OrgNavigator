package com.example.orgnavigator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.example.orgnavigator.model.Employee;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {



    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testAllEmployees() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:5000/employee/all"; // Az API végpontja
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }


    @Test
    public void testFindEmployee() {
        Long id = 9L;
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://localhost:" + port + "/employee/find/" + id;

        ResponseEntity<Employee> response = restTemplate.getForEntity(apiUrl, Employee.class);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void addEmployee() {

         RestTemplate restTemplate = new RestTemplate();
         String apiUrl = "http://localhost:5000/employee/add";

        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setPosition("Software Engineer");

        HttpEntity<Employee> requestEntity = new HttpEntity<>(employee);

        ResponseEntity<Employee> response = restTemplate.postForEntity(apiUrl, requestEntity, Employee.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}