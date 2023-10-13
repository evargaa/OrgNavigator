package com.example.orgnavigator.service;

import com.example.orgnavigator.exceptions.PartnerException;
import com.example.orgnavigator.model.Partners;
import com.example.orgnavigator.repository.PartnersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


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
        Partners partner = partnersRepository.findByNameContaining(name);
        if(partner == null){
            throw new PartnerException("Partner not found with this name: " + name);
        } else {
            return new ResponseEntity<>(partner, HttpStatus.OK);
        }
    }

    public ResponseEntity<String> deletePartner(Long id) {
        if (partnersRepository.findById(id).isEmpty()) {
            throw new PartnerException("Partner not found with ID: " + id);
        } else {
            partnersRepository.deleteById(id);
            return new ResponseEntity<>("Successfully deleted partner", HttpStatus.OK);
        }
    }
}
