package com.example.citizenentities.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CitizenTest {

    @Test
    public void testCitizenConstructorAndGetters() {
        Citizen citizen = new Citizen("ID123456", "John", "Doe", "Male", "01-01-1990", "123456789", "Athens");

        assertEquals("ID123456", citizen.getId());
        assertEquals("John", citizen.getFirstName());
        assertEquals("Doe", citizen.getLastName());
        assertEquals("Male", citizen.getGender());
        assertEquals("01-01-1990", citizen.getBirthDate());
        assertEquals("123456789", citizen.getAfm());
        assertEquals("Athens", citizen.getAddress());
    }

    @Test
    public void testCitizenSetters() {
        Citizen citizen = new Citizen();
        citizen.setId("ID654321");
        citizen.setFirstName("Jane");
        citizen.setLastName("Smith");
        citizen.setGender("Female");
        citizen.setBirthDate("02-02-1992");
        citizen.setAfm("987654321");
        citizen.setAddress("Thessaloniki");

        assertEquals("ID654321", citizen.getId());
        assertEquals("Jane", citizen.getFirstName());
        assertEquals("Smith", citizen.getLastName());
        assertEquals("Female", citizen.getGender());
        assertEquals("02-02-1992", citizen.getBirthDate());
        assertEquals("987654321", citizen.getAfm());
        assertEquals("Thessaloniki", citizen.getAddress());
    }
}
