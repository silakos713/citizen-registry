package com.example.citizenservice.repository;

import com.example.citizenentities.model.Citizen;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CitizenRepositoryTest {

    @Autowired
    private CitizenRepository citizenRepository;

    @BeforeEach
    public void setUp() {
        citizenRepository.deleteAll();
    }

    @Test
    public void testSaveCitizen() {
        Citizen citizen = new Citizen("ID999999", "George", "Papadopoulos", "Male", "05-05-1985", "111222333", "Patras");
        Citizen savedCitizen = citizenRepository.save(citizen);

        assertNotNull(savedCitizen);
        assertEquals("George", savedCitizen.getFirstName());
    }

    @Test
    public void testFindById() {
        Citizen citizen = new Citizen("ID888888", "Maria", "Ioannou", "Female", "10-10-1990", "444555666", "Larisa");
        citizenRepository.save(citizen);

        Optional<Citizen> foundCitizen = citizenRepository.findById("ID888888");
        assertTrue(foundCitizen.isPresent());
        assertEquals("Maria", foundCitizen.get().getFirstName());
    }

    @Test
    public void testDeleteCitizen() {
        Citizen citizen = new Citizen("ID777777", "Dimitris", "Nikolaou", "Male", "15-07-1987", "777888999", "Volos");
        citizenRepository.save(citizen);

        citizenRepository.deleteById("ID777777");
        Optional<Citizen> deletedCitizen = citizenRepository.findById("ID777777");

        assertFalse(deletedCitizen.isPresent());
    }
}
