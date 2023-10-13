package com.example.orgnavigator.service;

import com.example.orgnavigator.exceptions.PartnerException;
import com.example.orgnavigator.model.Partners;
import com.example.orgnavigator.repository.PartnersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public final class PartnersService {

    @Autowired
    private PartnersRepository partnersRepository;

    public List<Partners> getAllPartners() {
        return partnersRepository.findAll();
    }

    public String addPartner(final Partners newPartner) {
        partnersRepository.save(newPartner);
        return "Successfully added new partner!";
    }

    public Partners findPartnerByName(final String name) {
        Partners partner = partnersRepository.findByNameContaining(name);
        if (partner == null) {
            throw new PartnerException(
                    "Partner not found with this name: " + name);
        }
        return partner;
    }

    public String deletePartner(final Long id) {
        if (partnersRepository.findById(id).isEmpty()) {
            throw new PartnerException("Partner not found with ID: " + id);
        }
        partnersRepository.deleteById(id);
        return "Successfully deleted partner";
    }
}

