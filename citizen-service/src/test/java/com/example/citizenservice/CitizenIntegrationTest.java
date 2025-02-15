package com.example.citizenservice;

import com.example.citizenentities.model.Citizen;
import com.example.citizenservice.repository.CitizenRepository;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CitizenIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private CitizenRepository citizenRepository;

    private String baseUrl;

    @BeforeEach
    void setupDatabase() {
        baseUrl = "http://localhost:" + port + "/api/citizens";
        citizenRepository.deleteAll(); // Καθαρίζουμε τη βάση πριν από κάθε test

        // Δημιουργούμε έναν πολίτη για τα tests
        Citizen citizen = new Citizen("AB123456", "John", "Doe", "Male", "01-01-1990", "123456789", "Athens");
        citizenRepository.save(citizen);
    }

    @Test
    void testGetAllCitizens() {
        given()
                .when()
                .get(baseUrl)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$.size()", greaterThan(0));
    }

    @Test
    void testGetCitizenById() {
        given()
                .when()
                .get(baseUrl + "/AB123456")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo("AB123456"))
                .body("firstName", equalTo("John"))
                .body("lastName", equalTo("Doe"));
    }

    @Test
    void testAddCitizen() {
        Citizen newCitizen = new Citizen("CD789012", "Alice", "Smith", "Female", "05-05-1995", "987654321", "Thessaloniki");

        given()
                .contentType(ContentType.JSON)
                .body(newCitizen)
                .when()
                .post(baseUrl)
                .then()
                .statusCode(200)
                .body("id", equalTo("CD789012"))
                .body("firstName", equalTo("Alice"))
                .body("lastName", equalTo("Smith"));
    }

    @Test
    void testDeleteCitizen() {
        Citizen citizen = new Citizen("EF654321", "Bob", "Brown", "Male", "12-12-1985", "456789123", "Patras");
        citizenRepository.save(citizen); // Αποθηκεύουμε έναν πολίτη πριν τη διαγραφή

        given()
                .when()
                .delete(baseUrl + "/EF654321")
                .then()
                .statusCode(204);
    }
}
