package com.example.wizytydomowe.Doctor;


import org.springframework.stereotype.Service;

@Service
public class DoctorDtoMapper {
    DoctorDto map(Doctor doctor) {
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(doctor.getId());
        doctorDto.setName(doctor.getName());
        doctorDto.setSurname(doctor.getSurname());
        doctorDto.setPhoneNumber(doctor.getPhoneNumber());
        doctorDto.setSpecialization(doctor.getSpecialization());
        doctorDto.setEmail(doctor.getEmail());
        return doctorDto;
    }
}
