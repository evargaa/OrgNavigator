package com.example.orgnavigator.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String firstname;
    String lastname;
    LocalDate birthDate;
    int workingAge;
    int salary;
    String position;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public int getAge() {
        if (birthDate != null) {
            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(birthDate, currentDate);
            return period.getYears();
        } else {
            return 0; // Handle the case where birthDate is null
        }
    }

    public void incrementAge() {
        int newAge = getAge() + 1;
    }
}
