package com.example.wizytydomowe.Doctor;

import lombok.Data;

@Data
public class DoctorDto {
    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String specialization;
    private String email;
    private double latitude;
    private double longitude;
}
