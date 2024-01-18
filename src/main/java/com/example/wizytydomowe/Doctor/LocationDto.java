package com.example.wizytydomowe.Doctor;

import com.example.wizytydomowe.Appointment.Importance;
import lombok.Data;

@Data
public class LocationDto {
        private double longitude;
        private double latitude;
        private Importance importance;
        private String specialization;
}
