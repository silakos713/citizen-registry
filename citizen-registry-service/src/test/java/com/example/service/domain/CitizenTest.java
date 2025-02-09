package com.example.service.domain;

import com.example.domain.Citizen;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

public class CitizenTest {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Test
    public void testCitizenCreation() {
        Citizen citizen = new Citizen();
        citizen.setAt("123456789");
        citizen.setFirstName("John");
        citizen.setLastName("Doe");
        citizen.setGender("Male");
        citizen.setDateOfBirth(LocalDate.parse("07-06-1991", formatter)); // ✅ Σωστό format
        citizen.setAfm("123456789");
        citizen.setAddress("Athens, Greece");

        assertThat(citizen.getFirstName()).isEqualTo("John");
        assertThat(citizen.getDateOfBirth()).isEqualTo(LocalDate.parse("07-06-1991", formatter)); // ✅ Σωστή σύγκριση
    }
}
