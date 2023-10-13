package com.example.orgnavigator.controller;


import com.example.orgnavigator.model.Partners;
import com.example.orgnavigator.service.PartnersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partners/")
public class PartnersController {

    @Autowired
    private PartnersService partnersService;

    @GetMapping("all")
    public ResponseEntity<List<Partners>> allPartners() {
        List<Partners> partners = partnersService.getAllPartners();
        return ResponseEntity.ok(partners);
    }

    @PostMapping("add")
    public ResponseEntity<String> addPartner(@RequestBody Partners newPartner) {
        String message = partnersService.addPartner(newPartner);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @GetMapping("findByName/{name}")
    public ResponseEntity<Partners> findPartnerByName(@PathVariable String name) {
        Partners partner = partnersService.findPartnerByName(name);
        return ResponseEntity.ok(partner);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deletePartner(@PathVariable Long id) {
        String message = partnersService.deletePartner(id);
        return ResponseEntity.ok(message);
    }
}
