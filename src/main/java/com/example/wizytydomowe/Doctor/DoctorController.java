package com.example.wizytydomowe.Doctor;

import com.example.wizytydomowe.Patient.PatientDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.print.Doc;
import java.net.URI;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/{id}")
    ResponseEntity<DoctorDto> getDoctorById(@PathVariable Integer id){
        return doctorService.getDoctorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    ResponseEntity<DoctorDto> saveDoctor(@RequestBody DoctorDto doctorDto){
        DoctorDto savedDoctor = doctorService.saveDoctor(doctorDto);
        URI savedAppointmentUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedDoctor.getId())
                .toUri();
        return ResponseEntity.created(savedAppointmentUri).body(savedDoctor);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteDoctor(@PathVariable Integer id){
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();

    }
}
