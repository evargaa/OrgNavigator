package com.example.orgnavigator.controller;

import com.example.orgnavigator.model.Employee;
import com.example.orgnavigator.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id).getBody();
        return ResponseEntity.ok(employee);
    }

    @PostMapping("add")
    public ResponseEntity<Employee> addNewEmployee(@RequestBody Employee newEmployee) {
        Employee employee = employeeService.addNewEmployee(newEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        String message = employeeService.updateEmployee(id, updatedEmployee);
        return ResponseEntity.ok(message);
    }

    @GetMapping("findPosition/{position}")
    public ResponseEntity<List<Employee>> searchForWorkersByPosition(@PathVariable String position) {
        List<Employee> employees = employeeService.getEmployeesByPosition(position);
        return ResponseEntity.ok(employees);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        String message = employeeService.deleteEmployee(id);
        return ResponseEntity.ok(message);
    }
}
