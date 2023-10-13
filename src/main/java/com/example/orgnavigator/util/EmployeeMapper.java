package com.example.orgnavigator.util;

import com.example.orgnavigator.dto.EmployeeBasicDTO;
import com.example.orgnavigator.dto.EmployeeDetailedDTO;
import com.example.orgnavigator.model.Employee;

public class EmployeeMapper {

    public static EmployeeBasicDTO mapToBasicDTO(Employee employee) {
        EmployeeBasicDTO dto = new EmployeeBasicDTO();
        dto.setId(employee.getId());
        dto.setFirstname(employee.getFirstName());
        dto.setLastname(employee.getLastName());
        dto.setPosition(employee.getPosition());

        return dto;
    }


    public static EmployeeDetailedDTO mapToDetailedDTO(Employee employee) {
        EmployeeDetailedDTO dto = new EmployeeDetailedDTO();
        dto.setId(employee.getId());
        dto.setFirstname(employee.getFirstName());
        dto.setLastname(employee.getLastName());
        dto.setPosition(employee.getPosition());
        dto.setBirthDate(employee.getBirthDate());
        dto.setSalary(employee.getSalary());
        dto.setWorkingAge(employee.getWorkingAge());

        return dto;
    }
}
