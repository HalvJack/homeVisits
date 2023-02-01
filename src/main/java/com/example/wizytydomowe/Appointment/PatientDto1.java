package com.example.wizytydomowe.Appointment;

import com.example.wizytydomowe.Doctor.Doctor;
import com.example.wizytydomowe.Patient.Patient;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientDto1 {
    private Integer id;
    private LocalDate date;
    private Importance importance;
    private Integer price;
    private Doctor doctor;
    private Patient patient;

}
