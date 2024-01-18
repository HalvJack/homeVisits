package com.example.wizytydomowe.Appointment;

import com.example.wizytydomowe.Patient.PatientDtoMapper;
import org.springframework.stereotype.Service;

@Service
public class AppointmentDtoMapper {

    private final PatientDtoMapper patientDtoMapper;

    public AppointmentDtoMapper(PatientDtoMapper patientDtoMapper){
        this.patientDtoMapper = patientDtoMapper;
    }
    AppointmentDto map(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setId(appointment.getId());
        appointmentDto.setDate(appointment.getDate());
        appointmentDto.setImportance(appointment.getImportance());
        appointmentDto.setComments(appointment.getComments());
        appointmentDto.setSpecialization(appointmentDto.getSpecialization());
        appointmentDto.setPrice(appointment.getPrice());
        appointmentDto.setPatient(patientDtoMapper.map(appointment.getPatient()));
        appointmentDto.setDoctor(appointment.getDoctor());
        return appointmentDto;
    }

    Appointment map(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment();
        appointment.setId(appointmentDto.getId());
        appointment.setDate(appointmentDto.getDate());
        appointment.setImportance(appointmentDto.getImportance());
        appointment.setComments(appointmentDto.getComments());
        appointment.setSpecialization(appointmentDto.getSpecialization());
        appointment.setPrice(appointmentDto.getPrice());
        appointment.setPatient(patientDtoMapper.map(appointmentDto.getPatient()));
        appointment.setDoctor(appointmentDto.getDoctor());
        return appointment;
    }
}
