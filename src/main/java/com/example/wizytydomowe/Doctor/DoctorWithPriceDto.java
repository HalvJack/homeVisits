package com.example.wizytydomowe.Doctor;



public class DoctorWithPriceDto {
    private DoctorDto doctor;
    private double appointmentPrice;

    public DoctorWithPriceDto(DoctorDto doctorDto, double price) {
        doctor = doctorDto;
        appointmentPrice = price;
    }
}
