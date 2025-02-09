package com.example.service.repository;

import com.example.domain.Citizen;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CitizenRepositoryTest {

    @Autowired
    private CitizenRepository citizenRepository;

    @Test
    public void testSaveCitizen() {
        Citizen citizen = new Citizen();
        citizen.setAt("123456789");
        citizen.setFirstName("John");
        citizen.setLastName("Doe");
        citizen.setGender("Male");
        citizen.setDateOfBirth(java.time.LocalDate.of(1991, 6, 7));
        citizen.setAfm("123456789");
        citizen.setAddress("Athens, Greece");

        Citizen savedCitizen = citizenRepository.save(citizen);

        assertThat(savedCitizen.getId()).isNotNull();
        assertThat(savedCitizen.getFirstName()).isEqualTo("John");
    }

    @Test
    public void testFindById() {
        Citizen citizen = new Citizen();
        citizen.setAt("987654321");
        citizen.setFirstName("Jane");
        citizen.setLastName("Doe");
        citizen.setGender("Female");
        citizen.setDateOfBirth(java.time.LocalDate.of(1995, 8, 12));
        citizen.setAfm("987654321");
        citizen.setAddress("Thessaloniki, Greece");

        Citizen savedCitizen = citizenRepository.save(citizen);

        Optional<Citizen> foundCitizen = citizenRepository.findById(String.valueOf(savedCitizen.getId()));

        assertThat(foundCitizen).isPresent();
        assertThat(foundCitizen.get().getFirstName()).isEqualTo("Jane");
    }

    @Test
    public void testFindAllCitizens() {
        Citizen citizen1 = new Citizen();
        citizen1.setAt("111111111");
        citizen1.setFirstName("Alice");
        citizen1.setLastName("Smith");
        citizen1.setGender("Female");
        citizen1.setDateOfBirth(java.time.LocalDate.of(1985, 1, 15));
        citizen1.setAfm("111111111");
        citizen1.setAddress("Patras, Greece");

        Citizen citizen2 = new Citizen();
        citizen2.setAt("222222222");
        citizen2.setFirstName("Bob");
        citizen2.setLastName("Brown");
        citizen2.setGender("Male");
        citizen2.setDateOfBirth(java.time.LocalDate.of(1980, 5, 21));
        citizen2.setAfm("222222222");
        citizen2.setAddress("Ioannina, Greece");

        citizenRepository.save(citizen1);
        citizenRepository.save(citizen2);

        List<Citizen> citizens = citizenRepository.findAll();

        assertThat(citizens).hasSize(2);
    }

    @Test
    public void testUpdateCitizen() {
        Citizen citizen = new Citizen();
        citizen.setAt("333333333");
        citizen.setFirstName("Chris");
        citizen.setLastName("Evans");
        citizen.setGender("Male");
        citizen.setDateOfBirth(java.time.LocalDate.of(1992, 3, 14));
        citizen.setAfm("333333333");
        citizen.setAddress("Heraklion, Greece");

        Citizen savedCitizen = citizenRepository.save(citizen);

        savedCitizen.setAddress("Rhodes, Greece");
        Citizen updatedCitizen = citizenRepository.save(savedCitizen);

        assertThat(updatedCitizen.getAddress()).isEqualTo("Rhodes, Greece");
    }

    @Test
    public void testDeleteCitizen() {
        Citizen citizen = new Citizen();
        citizen.setAt("444444444");
        citizen.setFirstName("Eve");
        citizen.setLastName("White");
        citizen.setGender("Female");
        citizen.setDateOfBirth(java.time.LocalDate.of(1990, 12, 30));
        citizen.setAfm("444444444");
        citizen.setAddress("Kavala, Greece");

        Citizen savedCitizen = citizenRepository.save(citizen);

        citizenRepository.deleteById(String.valueOf(savedCitizen.getId()));

        Optional<Citizen> deletedCitizen = citizenRepository.findById(String.valueOf(savedCitizen.getId()));
        assertThat(deletedCitizen).isEmpty();
    }
}
