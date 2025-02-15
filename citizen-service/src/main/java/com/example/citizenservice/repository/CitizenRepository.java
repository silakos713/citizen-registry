package com.example.citizenservice.repository;

import com.example.citizenentities.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, String> {
    Optional<Citizen> findById(String id);
    List<Citizen> findByFirstNameContainingIgnoreCase(String firstName);
    List<Citizen> findByLastNameContainingIgnoreCase(String lastName);
    List<Citizen> findByGender(String gender);
    List<Citizen> findByAfm(String afm);
}
