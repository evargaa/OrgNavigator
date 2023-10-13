package com.example.orgnavigator.service;

import com.example.orgnavigator.exceptions.EmployeeException;
import com.example.orgnavigator.model.Employee;
import com.example.orgnavigator.model.Project;
import com.example.orgnavigator.repository.EmployeeRepository;
import com.example.orgnavigator.repository.ProjectRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee addNewEmployee(Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }

    public ResponseEntity<Employee> getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return ResponseEntity.ok(employee.get());
        } else {
            throw new EmployeeException("Employee not found with ID: " + id);
        }
    }

    public String updateEmployee(Long id, Employee updatedEmployee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setSalary(updatedEmployee.getSalary());
            existingEmployee.setPosition(updatedEmployee.getPosition());
            employeeRepository.save(existingEmployee);
            return "Successfully updated " + existingEmployee.getFirstName();
        } else {
            throw new EmployeeException("Error occurred while updating employee");
        }
    }

    public List<Employee> getEmployeesByPosition(String position) {
        List<Employee> employees = employeeRepository.findByPositionContaining(position);
        if (employees.isEmpty()) {
            throw new EmployeeException("No employees found with position: " + position);
        } else {
            return employees;
        }
    }

    @Transactional
    public String deleteEmployee(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            for (Project project : employee.getProjects()) {
                project.getEmployees().remove(employee);
            }
            projectRepository.saveAll(employee.getProjects());
            employeeRepository.delete(employee);
            return "Successfully deleted the employee";
        } else {
            throw new EmployeeException("Employee not found with ID: " + id);
        }
    }
}
