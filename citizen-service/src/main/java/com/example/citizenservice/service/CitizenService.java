package com.example.citizenservice.service;

import com.example.citizenentities.model.Citizen;
import com.example.citizenservice.repository.CitizenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CitizenService {

    private final CitizenRepository citizenRepository;

    public CitizenService(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    public List<Citizen> getAllCitizens() {
        return citizenRepository.findAll();
    }

    public Optional<Citizen> getCitizenById(String id) {
        return citizenRepository.findById(id);
    }

    @Transactional
    public Citizen addCitizen(Citizen citizen) {
        if (citizenRepository.existsById(citizen.getId())) {
            throw new RuntimeException("Ο πολίτης με ΑΤ " + citizen.getId() + " υπάρχει ήδη!");
        }
        return citizenRepository.save(citizen);
    }

    @Transactional
    public boolean deleteCitizen(String id) {
        Optional<Citizen> citizen = citizenRepository.findById(id);
        if (citizen.isPresent()) {
            citizenRepository.deleteById(id);
            return true; 
        }
        return false; 
    }

}
