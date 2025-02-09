package com.example.service.controller;

import com.example.domain.Citizen;
import com.example.service.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citizens")
public class CitizenController {

    private final CitizenService citizenService;

    @Autowired
    public CitizenController(CitizenService citizenService) {
        this.citizenService = citizenService;
    }

    @PostMapping
    public ResponseEntity<Citizen> addCitizen(@RequestBody Citizen citizen) {
        Citizen savedCitizen = citizenService.addCitizen(citizen);
        return ResponseEntity.ok(savedCitizen);
    }

    @GetMapping
    public ResponseEntity<List<Citizen>> getAllCitizens() {
        return ResponseEntity.ok(citizenService.getAllCitizens());
    }

    @GetMapping("/{at}")
    public ResponseEntity<Citizen> getCitizenByAt(@PathVariable String at) {
        Citizen citizen = citizenService.getCitizenByAt(at);
        return citizen != null ? ResponseEntity.ok(citizen) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{at}")
    public ResponseEntity<Citizen> updateCitizen(@PathVariable String at, @RequestBody Citizen updatedCitizen) {
        Citizen updated = citizenService.updateCitizen(at, updatedCitizen);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{at}")
    public ResponseEntity<Void> deleteCitizen(@PathVariable String at) {
        citizenService.deleteCitizen(at);
        return ResponseEntity.noContent().build();
    }
}
