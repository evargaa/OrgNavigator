package com.example.orgnavigator.repository;

import com.example.orgnavigator.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByPositionContaining(String position);
}
