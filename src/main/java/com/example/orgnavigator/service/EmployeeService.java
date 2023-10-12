package com.example.orgnavigator.service;

import com.example.orgnavigator.model.Employee;
import com.example.orgnavigator.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            return new ResponseEntity<>(employeeRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addNewEmployee(Employee newEmployee) {
        try{
            employeeRepository.save(newEmployee);
            return new ResponseEntity<>("Successfully added " + newEmployee.getFirstName(), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error occurred while tried to add new employee", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Optional<Employee>> getEmployeeById(Long id) {
        try {
            if(employeeRepository.findById(id).isPresent()){
                return new ResponseEntity<>(employeeRepository.findById(id), HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateEmployee(Long id, Employee updatedEmployee) {
        try {
                Optional<Employee> optionalEmployee  = employeeRepository.findById(id);
            if(optionalEmployee.isPresent()) {
                Employee existingEmployee = optionalEmployee.get();
                existingEmployee.setSalary(updatedEmployee.getSalary());
                existingEmployee.setPosition(updatedEmployee.getPosition());
                employeeRepository.save(existingEmployee);

                return new ResponseEntity<>("Successfully edited " + existingEmployee.getFirstName(), HttpStatus.OK );
            } else {
                return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error occurred while updating Employee", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Employee>> getEmployeesByPosition(String position) {
        try {
            return new ResponseEntity<>(employeeRepository.findByPositionContaining(position), HttpStatus.FOUND);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteEmployee(Long id) {
        try{
            if(employeeRepository.findById(id).isPresent()){
                employeeRepository.deleteById(id);
                return new ResponseEntity<>("Successfully deleted the employee", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error occurred while deleting the employee", HttpStatus.BAD_REQUEST);
    }
}
