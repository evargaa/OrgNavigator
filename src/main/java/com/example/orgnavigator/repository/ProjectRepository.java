package com.example.orgnavigator.repository;

import com.example.orgnavigator.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
