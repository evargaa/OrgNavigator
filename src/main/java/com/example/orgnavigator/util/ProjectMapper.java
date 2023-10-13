package com.example.orgnavigator.util;

import com.example.orgnavigator.dto.EmployeeBasicDTO;
import com.example.orgnavigator.dto.ProjectWithBasicEmployeeDTO;
import com.example.orgnavigator.model.Project;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectMapper {

    public static ProjectWithBasicEmployeeDTO mapToProjectWithBasicEmployeesDTO(Project project) {
            ProjectWithBasicEmployeeDTO dto = new ProjectWithBasicEmployeeDTO();
            dto.setId(project.getId());
            dto.setTitle(project.getTitle());

            List<EmployeeBasicDTO> employeeDTOs = project.getEmployees().stream()
                    .map(employee -> {
                        EmployeeBasicDTO employeeInfo = new EmployeeBasicDTO();
                        employeeInfo.setId(employee.getId());
                        employeeInfo.setFirstname(employee.getFirstName());
                        employeeInfo.setLastname(employee.getLastName());
                        employeeInfo.setPosition(employee.getPosition());
                        return employeeInfo;
                    })
                    .collect(Collectors.toList());
            dto.setEmployees(employeeDTOs);
            return dto;
        }
    }


