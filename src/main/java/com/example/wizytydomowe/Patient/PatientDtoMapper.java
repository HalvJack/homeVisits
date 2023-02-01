package com.example.wizytydomowe.Patient;

import org.springframework.stereotype.Service;

@Service
public class PatientDtoMapper {
    public PatientDto map(Patient patient){
        PatientDto patientDto = new PatientDto();
        patientDto.setId(patient.getId());
        patientDto.setName(patient.getName());
        patientDto.setSurname(patient.getSurname());
        patientDto.setPesel(patient.getPesel());
//        patientDto.setDateOfBirth(patient.getDateOfBirth());
        patientDto.setAddress(patient.getAddress());
        patientDto.setPhoneNumber(patient.getPhoneNumber());
        patientDto.setEmail(patient.getEmail());
        return patientDto;
    }
    public Patient map(PatientDto patientDto){
        Patient patient = new Patient();
        patient.setName(patientDto.getName());
        patient.setSurname(patientDto.getSurname());
        patient.setPesel(patientDto.getPesel());
//        patient.setDateOfBirth(patientDto.getDateOfBirth());
        patient.setAddress(patientDto.getAddress());
        patient.setPhoneNumber(patientDto.getPhoneNumber());
        patient.setEmail(patientDto.getEmail());
        return patient;
    }
}
