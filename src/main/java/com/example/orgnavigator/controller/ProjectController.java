package com.example.orgnavigator.controller;

import com.example.orgnavigator.dto.ProjectWithBasicEmployeeDTO;
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
    public ResponseEntity<List<ProjectWithBasicEmployeeDTO>> allProjects() {
        List<ProjectWithBasicEmployeeDTO> projectDTOs = projectService.allProjects();
        return ResponseEntity.ok(projectDTOs);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<?> findProjectById(@PathVariable Long id) {
        try {
            Optional<Project> project = Optional.ofNullable(projectService.findProjectById(id));
            if (project.isPresent()) {
                return ResponseEntity.ok(project.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            String message = e.getMessage();
            return ResponseEntity.badRequest().body(message);
        }
    }


    @PostMapping("add")
    public ResponseEntity<String> addNewProject(@RequestBody Project newProject) {
        String message = projectService.addNewProject(newProject);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        String message;
        try {
            message = projectService.deleteProject(id);
            return ResponseEntity.badRequest().body(message);
        } catch (Exception e) {
            message = e.getMessage();
            return ResponseEntity.badRequest().body(message);
        }
    }

    @PutMapping("updateStatus/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @RequestBody Project newStatus) {
        String message;
        try {
            message = projectService.updateStatus(id, newStatus);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message = e.getMessage();
            return ResponseEntity.badRequest().body(message);
        }
    }

    @PutMapping("addEmployee/{id}")
    public ResponseEntity<String> addEmployee(@PathVariable Long id) {
        String message;
        try {
            message = projectService.addEmployee(id);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message = e.getMessage();
            return ResponseEntity.badRequest().body(message);
        }
    }
}
