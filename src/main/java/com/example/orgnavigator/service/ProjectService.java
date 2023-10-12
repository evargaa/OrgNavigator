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

    public ResponseEntity<List<Project>> allProjects() {
        return new ResponseEntity<>(projectRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> addNewProject(Project newProject) {
        projectRepository.save(newProject);
        return new ResponseEntity<>("Project " + newProject.getTitle() + " has been successfully made", HttpStatus.CREATED);
    }

    public ResponseEntity<Optional<Project>> findProjectById(Long id) {
        if (projectRepository.findById(id).isEmpty()) {
            throw new ProjectException("Project not found with ID: " + id);
        } else {
            return new ResponseEntity<>(projectRepository.findById(id), HttpStatus.OK);
        }
    }

    public ResponseEntity<String> deleteProject(Long id) {
        if (projectRepository.findById(id).isEmpty()) {
            throw new ProjectException("Project not found with ID: " + id);
        } else {
            projectRepository.deleteById(id);
            return new ResponseEntity<>("Project deleted successfully!", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> updateStatus(Long id, Project newStatus) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            Project existingProject = optionalProject.get();
            String newStatusValue = newStatus.getStatus();
            if (newStatusValue != null) {
                existingProject.setStatus(newStatusValue);
                projectRepository.save(existingProject);
                return new ResponseEntity<>("Status successfully updated", HttpStatus.OK);
            } else {
                throw new ProjectException("Status cant be null");            }
        } else {
            throw new ProjectException("Project not found with ID: " + id);        }
    }
}
