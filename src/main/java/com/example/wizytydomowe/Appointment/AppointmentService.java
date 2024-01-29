package com.example.wizytydomowe.Appointment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentDtoMapper appointmentDtoMapper;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              AppointmentDtoMapper appointmentDtoMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentDtoMapper = appointmentDtoMapper;
    }

    public Optional<AppointmentDto> getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .map(appointmentDtoMapper::map);
    }

    public AppointmentDto saveAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = appointmentDtoMapper.map(appointmentDto);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return appointmentDtoMapper.map(savedAppointment);
    }

    public Long getLastAppointmentId() {
        return (long) appointmentRepository.findAll().size();
    }


    void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

}
