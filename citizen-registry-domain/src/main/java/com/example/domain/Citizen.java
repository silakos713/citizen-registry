package com.example.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "citizens")
public class Citizen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Χρησιμοποιούμε Auto-Increment ID
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String at;
    
    private String firstName;
    private String lastName;
    private String gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    private String afm;
    private String address;

    // ✅ Default constructor (για JPA)
    public Citizen() {}

    // ✅ Custom Constructor (για Client)
    public Citizen(String at, String firstName, String lastName, String gender, LocalDate dateOfBirth, String afm, String address) {
        this.at = at;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.afm = afm;
        this.address = address;
    }

    // ✅ Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAt() { return at; }
    public void setAt(String at) { this.at = at; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getAfm() { return afm; }
    public void setAfm(String afm) { this.afm = afm; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return "Citizen{" +
                "id=" + id +
                ", at='" + at + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", afm='" + afm + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
