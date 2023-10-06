package com.example.orgnavigator.service;

import com.example.orgnavigator.model.Employee;
import com.example.orgnavigator.model.Project;
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

    public ResponseEntity<List<Project>> allProjects() {
        try {
            return new ResponseEntity<>(projectRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addNewProject(Project newProject) {
        try {
            projectRepository.save(newProject);
            return new ResponseEntity<>("Project " + newProject.getTitle() +
                    "has been successfully made", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
            return new ResponseEntity<>("Error occurred while making " + newProject.getTitle() + " project.", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Optional<Project>> findProjectById(Long id) {
        try {
            if (projectRepository.findById(id).isPresent()) {
                return new ResponseEntity<>(projectRepository.findById(id), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteProject(Long id) {
        try {
            if(projectRepository.findById(id).isPresent()){
                projectRepository.deleteById(id);
                return new ResponseEntity<>("Successfully deleted project", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Project not found please another correct ID", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error occured while deleting project", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateStatus(Long id, Project newStatus) {
        try {
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
                return new ResponseEntity<>("Didn't find project by id", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error occurred while updating status", HttpStatus.BAD_REQUEST);
    }
}
