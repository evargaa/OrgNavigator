package com.example.orgnavigator.service;

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
        if (projectRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(projectRepository.findById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteProject(Long id) {
        if (projectRepository.findById(id).isPresent()) {
            projectRepository.deleteById(id);
            return new ResponseEntity<>("Successfully deleted project", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Project not found, please provide a correct ID", HttpStatus.BAD_REQUEST);
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
                return new ResponseEntity<>("Status field cannot be null", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Didn't find a project by ID", HttpStatus.NOT_FOUND);
        }
    }
}
