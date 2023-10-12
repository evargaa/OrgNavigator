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
        try {
            return new ResponseEntity<>(partnersRepository.findAll(), HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addPartner(Partners newPartner) {
        try {
            partnersRepository.save(newPartner);
            return new ResponseEntity<>("Succesfully added new partner!", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error occurred while adding new partner", HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<Partners> findPartnerByName(String name) {
        try {
            return new ResponseEntity<>(partnersRepository.findByNameContaining(name), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
