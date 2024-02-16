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

    public DoctorDto(Long id, String name, String surname, String phoneNumber, String specialization, String email,
                     double latitude, double longitude){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public DoctorDto()
    {}
}
