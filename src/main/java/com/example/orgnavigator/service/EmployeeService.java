package com.example.orgnavigator.service;

import com.example.orgnavigator.model.Employee;
import com.example.orgnavigator.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> addNewEmployee(Employee newEmployee) {
        employeeRepository.save(newEmployee);
        return new ResponseEntity<>("Successfully added " + newEmployee.getFirstName(), HttpStatus.CREATED);
    }

    public ResponseEntity<?> getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> updateEmployee(Long id, Employee updatedEmployee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setSalary(updatedEmployee.getSalary());
            existingEmployee.setPosition(updatedEmployee.getPosition());
            employeeRepository.save(existingEmployee);
            return new ResponseEntity<>("Successfully edited " + existingEmployee.getFirstName(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Employee>> getEmployeesByPosition(String position) {
        List<Employee> employees = employeeRepository.findByPositionContaining(position);
        if (!employees.isEmpty()) {
            return new ResponseEntity<>(employees, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> deleteEmployee(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            employeeRepository.deleteById(id);
            return new ResponseEntity<>("Successfully deleted the employee", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }
    }
}
