package com.example.orgnavigator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String firstname;

    String lastname;


    LocalDate birthDate;


    int workingAge;


    int salary;

    String position;

    @ManyToMany(mappedBy = "employees")
    @JsonIgnore
    private List<Project> projects;


    public Employee() {
    }
    public Employee(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }



    public int getWorkingAge() {
        return workingAge;
    }

    public void setWorkingAge(int workingAge) {
        this.workingAge = workingAge;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @JsonIgnore
    public int getAge() {
        if (birthDate != null) {
            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(birthDate, currentDate);
            return period.getYears();
        } else {
            return 0;
        }
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public void incrementAge() {
        int newAge = getAge() + 1;
    }


}
