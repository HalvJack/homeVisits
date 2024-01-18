package com.example.wizytydomowe.Appointment;

import com.example.wizytydomowe.Doctor.Doctor;
import com.example.wizytydomowe.Patient.Patient;
import com.example.wizytydomowe.Patient.PatientDto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentDto {
    private Long id;
    private LocalDate date;
    private Importance importance;
    private String comments;
    private String specialization;
    private Integer price;
    private Doctor doctor;
    private PatientDto patient;
}
