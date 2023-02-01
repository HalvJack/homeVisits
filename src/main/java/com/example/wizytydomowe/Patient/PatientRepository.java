package com.example.wizytydomowe.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
