package com.example.citizenservice.controller;

import com.example.citizenentities.model.Citizen;
import com.example.citizenservice.service.CitizenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/citizens")
public class CitizenController {

    private final CitizenService citizenService;

    public CitizenController(CitizenService citizenService) {
        this.citizenService = citizenService;
    }

    // 🔹 GET /api/citizens
    @GetMapping
    public ResponseEntity<List<Citizen>> getAllCitizens() {
        List<Citizen> citizens = citizenService.getAllCitizens();
        if (citizens.isEmpty()) {
            return ResponseEntity.noContent().build(); // Αν δεν υπάρχουν πολίτες, επιστρέφει 204 No Content
        }
        return ResponseEntity.ok(citizens); // Αν υπάρχουν πολίτες, επιστρέφει 200 OK
    }

    // 🔹 GET /api/citizens/{id} - Αναζήτηση με βάση το ID
    @GetMapping("/{id}")
    public ResponseEntity<Citizen> getCitizenById(@PathVariable String id) {
        Optional<Citizen> citizen = citizenService.getCitizenById(id);
        return citizen.map(ResponseEntity::ok) // Αν υπάρχει, επιστρέφει 200 OK με τον πολίτη
                      .orElseGet(() -> ResponseEntity.notFound().build()); // Αν δεν υπάρχει, επιστρέφει 404
    }

    // 🔹 POST /api/citizens - Προσθήκη νέου πολίτη
    @PostMapping
    public ResponseEntity<Citizen> addCitizen(@RequestBody Citizen citizen) {
        Citizen savedCitizen = citizenService.addCitizen(citizen);
        return ResponseEntity.ok(savedCitizen); // 200 OK με τον αποθηκευμένο πολίτη
    }

    // 🔹 DELETE /api/citizens/{id} - Διαγραφή με βάση το ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCitizen(@PathVariable String id) {
        boolean deleted = citizenService.deleteCitizen(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content αν διαγράφηκε επιτυχώς
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found αν ο πολίτης δεν βρέθηκε
        }
    }

}
