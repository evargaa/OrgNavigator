package com.example.orgnavigator.repository;

import com.example.orgnavigator.model.Partners;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnersRepository extends JpaRepository<Partners, Long> {
    Partners findByNameContaining(String name);

    boolean findByName(String name);
}
