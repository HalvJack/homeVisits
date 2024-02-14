package com.example.wizytydomowe.Doctor;


import lombok.Data;

@Data
public class DoctorWithPriceDto {
    private DoctorDto doctor;
    private double appointmentPrice;

    public DoctorWithPriceDto(DoctorDto doctorDto, double price) {
        doctor = doctorDto;
        appointmentPrice = price;
    }

    public DoctorWithPriceDto()
    {}
}
