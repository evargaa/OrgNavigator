package com.example.orgnavigator.service;

import com.example.orgnavigator.dto.ProjectWithBasicEmployeeDTO;
import com.example.orgnavigator.exceptions.EmployeeException;
import com.example.orgnavigator.exceptions.ProjectException;
import com.example.orgnavigator.model.Employee;
import com.example.orgnavigator.model.Project;
import com.example.orgnavigator.repository.EmployeeRepository;
import com.example.orgnavigator.repository.ProjectRepository;
import com.example.orgnavigator.util.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public List<ProjectWithBasicEmployeeDTO> allProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .map(ProjectMapper::mapToProjectWithBasicEmployeesDTO)
                .collect(Collectors.toList());
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

    public Project addEmployee(Long projectId, Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new EmployeeException("Employee not found with ID:" + employeeId));
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectException("Project not found with ID: " + projectId));
        project.getEmployees().add(employee);
        return projectRepository.save(project);
    }
}
