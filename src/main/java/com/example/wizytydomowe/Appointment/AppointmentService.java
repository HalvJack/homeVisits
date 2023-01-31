package com.example.wizytydomowe.Appointment;

import com.example.wizytydomowe.Doctor.DoctorDtoMapper;
import com.example.wizytydomowe.Patient.PatientDtoMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentDtoMapper appointmentDtoMapper;
    private final DoctorDtoMapper doctorDtoMapper;
    private final PatientDtoMapper patientDtoMapper;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              AppointmentDtoMapper appointmentDtoMapper, DoctorDtoMapper doctorDtoMapper,
                              PatientDtoMapper patientDtoMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentDtoMapper = appointmentDtoMapper;
        this.doctorDtoMapper = doctorDtoMapper;
        this.patientDtoMapper = patientDtoMapper;
    }

    Optional<AppointmentDto> getAppointmentById(Integer id){
        return appointmentRepository.findById(id)
                .map(appointmentDtoMapper::map);
    }
    AppointmentDto saveAppointment(AppointmentDto appointmentDto){
        Appointment appointment = appointmentDtoMapper.map(appointmentDto);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return appointmentDtoMapper.map(savedAppointment);
    }
}
