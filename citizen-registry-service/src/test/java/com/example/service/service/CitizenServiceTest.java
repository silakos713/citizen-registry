package com.example.service.service;

import com.example.domain.Citizen;
import com.example.service.repository.CitizenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CitizenServiceTest {

    @Autowired
    private CitizenRepository citizenRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Test
    public void testSaveCitizen() {
        Citizen citizen = new Citizen();
        citizen.setAt("123456789");
        citizen.setFirstName("John");
        citizen.setLastName("Doe");
        citizen.setGender("Male");
        citizen.setDateOfBirth(LocalDate.parse("07-06-1991", formatter));
        citizen.setAfm("123456789");
        citizen.setAddress("Athens, Greece");

        Citizen savedCitizen = citizenRepository.save(citizen);

        assertThat(savedCitizen.getId()).isNotNull();
        assertThat(savedCitizen.getFirstName()).isEqualTo("John");
    }
}
