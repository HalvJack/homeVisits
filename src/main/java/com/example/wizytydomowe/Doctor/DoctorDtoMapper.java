package com.example.wizytydomowe.Doctor;


import org.springframework.stereotype.Service;

@Service
public class DoctorDtoMapper {
    public DoctorDto map(Doctor doctor) {
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(doctor.getId());
        doctorDto.setName(doctor.getName());
        doctorDto.setSurname(doctor.getSurname());
        doctorDto.setPhoneNumber(doctor.getPhoneNumber());
        doctorDto.setSpecialization(doctor.getSpecialization());
        doctorDto.setEmail(doctor.getEmail());
        return doctorDto;
    }

    public Doctor map(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        doctor.setId(doctorDto.getId());
        doctor.setName(doctorDto.getName());
        doctor.setSurname(doctorDto.getSurname());
        doctor.setEmail(doctorDto.getEmail());
        doctor.setPhoneNumber(doctorDto.getPhoneNumber());
        doctor.setSpecialization(doctorDto.getSpecialization());
        return doctor;
    }
}
