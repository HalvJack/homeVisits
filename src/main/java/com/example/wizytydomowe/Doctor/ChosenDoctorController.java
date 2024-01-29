package com.example.wizytydomowe.Doctor;

import com.example.wizytydomowe.Appointment.Appointment;
import com.example.wizytydomowe.Appointment.AppointmentDto;
import com.example.wizytydomowe.Appointment.AppointmentDtoMapper;
import com.example.wizytydomowe.Appointment.AppointmentService;
import com.example.wizytydomowe.Sms.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/submittedDoctor")
@Slf4j
public class ChosenDoctorController {
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;
    private final AppointmentDtoMapper appointmentDtoMapper;
    private final DoctorDtoMapper doctorDtoMapper;
    private final SmsService smsService;

    @PostMapping
    public ResponseEntity<?> submitDoctor(@RequestBody DoctorDto doctorDto) {
        Doctor doctor = doctorDtoMapper.map(doctorService.getDoctorByPhoneNumber(doctorDto.getPhoneNumber()));
        if (doctor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
        }

        Optional<AppointmentDto> recentAppointment = appointmentService.getAppointmentById(appointmentService.getLastAppointmentId());

        if (recentAppointment.isPresent()) {
            Appointment appointment = appointmentDtoMapper.map(recentAppointment.get());
            appointment.setDoctor(doctor);
            appointmentService.saveAppointment(appointmentDtoMapper.map(appointment));
            try {
                String messageContent = smsService.prepareMessage(recentAppointment.get());
                smsService.sendSMS(doctor.getPhoneNumber(), messageContent);
                return ResponseEntity.ok().body("Doctor submitted successfully for recent appointment");
            } catch (Exception e) {
                // Log the error and handle it appropriately
                log.error("Error sending SMS: ", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Appointment saved, but failed to send SMS");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No recent appointment found");
        }
    }

}
