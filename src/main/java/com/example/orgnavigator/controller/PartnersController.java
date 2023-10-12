package com.example.orgnavigator.controller;


import com.example.orgnavigator.model.Partners;
import com.example.orgnavigator.service.PartnersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partners/")
public class PartnersController {

    @Autowired
    PartnersService partnersService;

    @GetMapping("all")
    public ResponseEntity<List<Partners>> allPartners() {
        return partnersService.allPartners();
    }

    @PostMapping("add")
    public ResponseEntity<String> addPartner(@RequestBody Partners newPartner){
        return partnersService.addPartner(newPartner);
    }


    @GetMapping("findByName/{name}")
    public ResponseEntity<Partners> findPartnerByName(@PathVariable String name) {
        return partnersService.findPartnerByName(name);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deletePartner(@PathVariable Long id) {
        return partnersService.deletePartner(id);
    }

}