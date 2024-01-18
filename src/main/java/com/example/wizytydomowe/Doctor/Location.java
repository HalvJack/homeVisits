package com.example.wizytydomowe.Doctor;

import com.example.wizytydomowe.Appointment.Importance;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Location {
    private double longitude;
    private double latitude;
    private Importance importance;
    private String specialization;
}
