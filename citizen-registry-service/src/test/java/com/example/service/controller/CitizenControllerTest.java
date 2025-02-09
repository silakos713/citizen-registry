package com.example.service.controller;

import com.example.domain.Citizen;
import com.example.service.repository.CitizenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CitizenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;  // Για καθαρισμό βάσης

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void cleanDatabase() {
        jdbcTemplate.execute("DELETE FROM citizens");
    }

    @Test
    public void testCreateCitizen() throws Exception {
        Citizen citizen = new Citizen("AT123456", "John", "Doe", "Male", LocalDate.of(1990, 5, 20), "123456789", "Athens");

        mockMvc.perform(post("/citizens")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(citizen)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    @Test
    public void testGetAllCitizens() throws Exception {
        Citizen citizen1 = new Citizen("AT123456", "John", "Doe", "Male", LocalDate.of(1990, 5, 20), "123456789", "Athens");
        Citizen citizen2 = new Citizen("AT654321", "Jane", "Smith", "Female", LocalDate.of(1995, 8, 15), "987654321", "Thessaloniki");

        citizenRepository.save(citizen1);
        citizenRepository.save(citizen2);

        mockMvc.perform(get("/citizens"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testGetCitizenById() throws Exception {
        Citizen citizen = new Citizen("AT123456", "John", "Doe", "Male", LocalDate.of(1990, 5, 20), "123456789", "Athens");
        Citizen savedCitizen = citizenRepository.save(citizen);

        mockMvc.perform(get("/citizens/{id}", savedCitizen.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    @Test
    public void testUpdateCitizen() throws Exception {
        Citizen citizen = new Citizen("AT123456", "John", "Doe", "Male", LocalDate.of(1990, 5, 20), "123456789", "Athens");
        Citizen savedCitizen = citizenRepository.save(citizen);

        savedCitizen.setAddress("Patras");

        mockMvc.perform(put("/citizens/{id}", savedCitizen.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedCitizen)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value("Patras"));
    }

    @Test
    public void testDeleteCitizen() throws Exception {
        Citizen citizen = new Citizen("AT123456", "John", "Doe", "Male", LocalDate.of(1990, 5, 20), "123456789", "Athens");
        Citizen savedCitizen = citizenRepository.save(citizen);

        mockMvc.perform(delete("/citizens/{id}", savedCitizen.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/citizens/{id}", savedCitizen.getId()))
                .andExpect(status().isNotFound());
    }
}
