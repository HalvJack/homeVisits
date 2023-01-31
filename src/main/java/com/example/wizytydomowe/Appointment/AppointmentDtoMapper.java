package com.example.wizytydomowe.Appointment;

import org.springframework.stereotype.Service;

@Service
public class AppointmentDtoMapper {
    AppointmentDto map(Appointment appointment){
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setId(appointment.getId());
        appointmentDto.setDate(appointment.getDate());
        appointmentDto.setImportance(appointment.getImportance());
        appointmentDto.setPrice(appointment.getPrice());
        appointmentDto.setDoctor(appointment.getDoctor());
        appointmentDto.setPatient(appointment.getPatient());
        return appointmentDto;
    }
    Appointment map(AppointmentDto appointmentDto){
        Appointment appointment = new Appointment();
        appointment.setId(appointmentDto.getId());
        appointment.setDate(appointmentDto.getDate());
        appointment.setImportance(appointmentDto.getImportance());
        appointment.setPrice(appointmentDto.getPrice());
        appointment.setPatient(appointmentDto.getPatient());
        appointment.setDoctor(appointmentDto.getDoctor());
        return appointment;
    }
}
