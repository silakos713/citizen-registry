package com.example.service.service;

import com.example.domain.Citizen;
import com.example.service.repository.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitizenService {

    private final CitizenRepository citizenRepository;

    @Autowired
    public CitizenService(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    public Citizen addCitizen(Citizen citizen) {
        return citizenRepository.save(citizen);
    }

    public List<Citizen> getAllCitizens() {
        return citizenRepository.findAll();
    }

    public Citizen getCitizenByAt(String at) {
        return citizenRepository.findById(at).orElse(null);
    }
    
    public Citizen findByAt(String at) {
        return citizenRepository.findById(at).orElse(null);
    }

    public Citizen updateCitizen(String at, Citizen updatedCitizen) {
        Optional<Citizen> existingCitizen = citizenRepository.findById(at);
        if (existingCitizen.isPresent()) {
            Citizen citizen = existingCitizen.get();
            citizen.setAfm(updatedCitizen.getAfm());
            citizen.setAddress(updatedCitizen.getAddress());
            return citizenRepository.save(citizen);
        }
        return null;
    }

    public void deleteCitizen(String at) {
        citizenRepository.deleteById(at);
    }
}
