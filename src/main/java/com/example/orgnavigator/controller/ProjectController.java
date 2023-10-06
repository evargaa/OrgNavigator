package com.example.orgnavigator.controller;

import com.example.orgnavigator.model.Project;
import com.example.orgnavigator.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects/")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("all")
    public ResponseEntity<List<Project>> allProjects(){
        return projectService.allProjects();
    }

    @GetMapping("find/{id}")
    public ResponseEntity<Optional<Project>> findProjectById(@PathVariable Long id) {
        return projectService.findProjectById(id);
    }

    @PostMapping("add")
    public ResponseEntity<String> addNewProject(@RequestBody Project newProject){
        return projectService.addNewProject(newProject);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        return projectService.deleteProject(id);
    }

    @PutMapping("updateStatus/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestBody Project newStatus) {
        return projectService.updateStatus(id, newStatus);
    }
}
