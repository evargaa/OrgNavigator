package com.example.orgnavigator.dto;

import java.util.List;

public class ProjectWithBasicEmployeeDTO {

    private Long id;

    private String title;

    private List<EmployeeBasicDTO> employees;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<EmployeeBasicDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeBasicDTO> employees) {
        this.employees = employees;
    }
}
