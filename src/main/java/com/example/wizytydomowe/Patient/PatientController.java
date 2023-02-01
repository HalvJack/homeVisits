package com.example.wizytydomowe.Patient;

import com.example.wizytydomowe.Appointment.PatientDto1;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    @GetMapping("/{id}")
    ResponseEntity<com.example.wizytydomowe.Patient.PatientDto> getPatientById(@PathVariable Integer id){
        return patientService.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    ResponseEntity<PatientDto> savePatient(@RequestBody PatientDto patientDto){
        PatientDto savedPatient = patientService.savePatient(patientDto);
        URI savedAppointmentUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPatient.getId())
                .toUri();
        return ResponseEntity.created(savedAppointmentUri).body(savedPatient);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteJobOffer(@PathVariable Integer id){
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}

