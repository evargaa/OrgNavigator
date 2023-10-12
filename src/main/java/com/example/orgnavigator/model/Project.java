package com.example.orgnavigator.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;


    private String status;

    @ElementCollection
    private List<String> technologies;

    @ManyToMany
    @JoinTable(
            name = "project_employees",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Employee> employees;

    public Project(){
    }

    public Project(Long id, String title, String description, List<String> technologies, List<Employee> employees, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.technologies = technologies;
        this.employees = employees;
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTechnologies(List<String> technologies) {
        this.technologies = technologies;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
