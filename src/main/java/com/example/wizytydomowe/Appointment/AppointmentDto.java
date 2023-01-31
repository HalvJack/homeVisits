package com.example.wizytydomowe.Appointment;

import com.example.wizytydomowe.Doctor.Doctor;
import com.example.wizytydomowe.Patient.Patient;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AppointmentDto {
    private Integer id;
    private LocalDate date;
    private Importance importance;
    private BigDecimal price;
    private Doctor doctor;
    private Patient patient;

}
