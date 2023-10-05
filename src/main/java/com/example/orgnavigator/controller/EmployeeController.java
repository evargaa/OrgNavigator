package com.example.orgnavigator.controller;

import com.example.orgnavigator.model.Employee;
import com.example.orgnavigator.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee/")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("all")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return employeeService.getAllEmployees();
    }


    @GetMapping("find/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("add")
    public ResponseEntity<String> addNewEmployee(@RequestBody Employee newEmployee) {
        return employeeService.addNewEmployee(newEmployee);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Integer id, @RequestBody Employee updatedEmployee) {
        return employeeService.updateEmployee(id, updatedEmployee);
    }

    @GetMapping("findPosition/{position}")
    public ResponseEntity<List<Employee>> searchForWorkersByPositon(@PathVariable String position) {
        return employeeService.getEmployeesByPosition(position);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
        return employeeService.deleteEmployee(id);
    }
}
