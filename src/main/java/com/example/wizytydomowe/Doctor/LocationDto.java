package com.example.wizytydomowe.Doctor;

import com.example.wizytydomowe.Appointment.Importance;
import lombok.Data;

@Data
public class LocationDto {
        private double longitude;
        private double latitude;
        private Importance importance;
        private String specialization;

        public LocationDto(double longitude, double latitude, Importance importance, String specialization){
                this.longitude = longitude;
                this.latitude = latitude;
                this.importance = importance;
                this.specialization = specialization;
        }

        public LocationDto()
        {}
}
