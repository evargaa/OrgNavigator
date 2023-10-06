package com.example.orgnavigator.controller;

import com.example.orgnavigator.model.Project;
import com.example.orgnavigator.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects/")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("all")
    public ResponseEntity<List<Project>> allProjects(){
        return projectService.allProjects();
    }

    @PostMapping("add")
    public ResponseEntity<String> addNewProject(@RequestBody Project newProject){
        return projectService.addNewProject(newProject);
    }
}
