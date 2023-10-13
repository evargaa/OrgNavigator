package com.example.orgnavigator.dto;

import java.time.LocalDate;

public class EmployeeDetailedDTO {
    private Long id;
    private String firstname;

    private String lastname;
    private String position;
    private LocalDate birthDate;
    private double salary;

    private int workingAge;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    public int getWorkingAge() {
        return workingAge;
    }

    public void setWorkingAge(int workingAge) {
        this.workingAge = workingAge;
    }
}
