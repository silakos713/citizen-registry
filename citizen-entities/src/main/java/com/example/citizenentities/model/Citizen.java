package com.example.citizenentities.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;

@Entity
@Table(name = "citizens")
public class Citizen {

    @Id
    @Column(name = "id", length = 8, nullable = false, unique = true)
    @Size(min = 8, max = 8, message = "Ο ΑΤ πρέπει να έχει ακριβώς 8 χαρακτήρες")
    private String id;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "Το όνομα είναι υποχρεωτικό")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Το επίθετο είναι υποχρεωτικό")
    private String lastName;

    @Column(name = "gender", nullable = false)
    @NotBlank(message = "Το φύλο είναι υποχρεωτικό")
    private String gender;

    @Column(name = "birth_date", nullable = false)
    @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "Η ημερομηνία γέννησης πρέπει να είναι της μορφής ΧΧ-ΥΥ-ΚΚΚΚ")
    private String birthDate;

    @Column(name = "afm")
    @Pattern(regexp = "\\d{9}", message = "Το ΑΦΜ πρέπει να έχει 9 ψηφία")
    private String afm;

    @Column(name = "address")
    private String address;

    public Citizen() {}

  
    public Citizen(String id, String firstName, String lastName, String gender, String birthDate, String afm, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.afm = afm;
        this.address = address;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public String getAfm() { return afm; }
    public void setAfm(String afm) { this.afm = afm; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    // toString() για debugging
    @Override
    public String toString() {
        return "Citizen{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", afm='" + afm + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}