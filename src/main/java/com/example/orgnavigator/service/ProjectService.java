package com.example.orgnavigator.service;

import com.example.orgnavigator.exceptions.ProjectException;
import com.example.orgnavigator.model.Employee;
import com.example.orgnavigator.model.Project;
import com.example.orgnavigator.repository.EmployeeRepository;
import com.example.orgnavigator.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Project> allProjects() {
        return projectRepository.findAll();
    }

    public String addNewProject(Project newProject) {
        projectRepository.save(newProject);
        return "Project " + newProject.getTitle() + " has been successfully made";
    }

    public Project findProjectById(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            return project.get();
        } else {
            throw new ProjectException("Project not found with ID: " + id);
        }
    }


    public String deleteProject(Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return "Project deleted successfully!";
        } else {
            throw new ProjectException("Project not found with ID: " + id);
        }
    }

    public String updateStatus(Long id, Project newStatus) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            Project existingProject = optionalProject.get();
            String newStatusValue = newStatus.getStatus();
            if (newStatusValue != null) {
                existingProject.setStatus(newStatusValue);
                projectRepository.save(existingProject);
                return "Status successfully updated";
            } else {
                throw new ProjectException("Status can't be null");
            }
        } else {
            throw new ProjectException("Project not found with ID: " + id);
        }
    }

    public String addEmployee(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        // Add your logic here for adding an employee to a project
        return "Employee added successfully";
    }
}
