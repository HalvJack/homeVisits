package com.example.wizytydomowe.Patient;

import com.example.wizytydomowe.Appointment.Appointment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping
    List<Patient> getPatients(@RequestParam(required = false) String name) {
        if (name == null) {
            return patientRepository.findAll();
        } else {
            return patientRepository.findAllByName(name);
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<Patient> getPatientById(@PathVariable Integer id) {
        return patientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/appointment")
    ResponseEntity<Appointment> getAppointmentById(@PathVariable Integer id){
        return patientRepository.findById(id)
                .map(Patient::getAppointment)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/example")
    @ResponseStatus(HttpStatus.CREATED)
    void example(){

    }
}
