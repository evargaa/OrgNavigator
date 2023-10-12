package com.example.orgnavigator.service;

import com.example.orgnavigator.model.Partners;
import com.example.orgnavigator.repository.PartnersRepository;
import com.example.orgnavigator.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartnersService {

    @Autowired
    PartnersRepository partnersRepository;

    public ResponseEntity<List<Partners>> allPartners() {
        return new ResponseEntity<>(partnersRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> addPartner(Partners newPartner) {
        partnersRepository.save(newPartner);
        return new ResponseEntity<>("Successfully added new partner!", HttpStatus.CREATED);
    }

    public ResponseEntity<Partners> findPartnerByName(String name) {
        return new ResponseEntity<>(partnersRepository.findByNameContaining(name), HttpStatus.OK);
    }

    public ResponseEntity<String> deletePartner(Long id) {
        if (partnersRepository.findById(id).isPresent()) {
            partnersRepository.deleteById(id);
            return new ResponseEntity<>("Successfully deleted partner", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ID not found", HttpStatus.NOT_FOUND);
        }
    }
}
