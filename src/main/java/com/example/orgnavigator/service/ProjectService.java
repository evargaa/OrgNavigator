package com.example.orgnavigator.service;

import com.example.orgnavigator.model.Project;
import com.example.orgnavigator.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
