package com.example.wizytydomowe.Patient;

import com.example.wizytydomowe.Appointment.Appointment;
import com.example.wizytydomowe.Appointment.Importance;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PatientRepository {
    private final List<Patient> patients = new ArrayList<>();

    public PatientRepository() {
        patients.add(
                new Patient(1, "Aleksander", "Turocha", "00233100123",
                        LocalDate.of(2000, 3, 31), new Address(1, "Jaslo", "38-200",
                        "Dobrzanskiego", 35, 0), "535110460",
                        new Appointment(LocalDate.now(),
                                Importance.URGENT, 200))
        );
        patients.add(
                new Patient(2, "Jakub", "Hałucha", "00242200996",
                        LocalDate.of(2000, 4, 22), new Address(2, "Lipinki Łużyckie",
                        "38-212", "Mickiewicza", 22, 15), "111231346",
                        new Appointment(LocalDate.now(), Importance.MEDIUM, 350))
        );
    }

    List<Patient> findAll() {
        return patients;
    }

    // /patients?name=Jakub
    List<Patient> findAllByName(String name) {
        return patients.stream().
                filter(p -> p.getName().equalsIgnoreCase(name)).
                toList();
    }

    // /patients/2
    Optional<Patient> findById(int id) {
        if (id > patients.size()) {
            return Optional.empty();
        } else {
            return Optional.of(patients.get(id - 1));
        }
    }
}
