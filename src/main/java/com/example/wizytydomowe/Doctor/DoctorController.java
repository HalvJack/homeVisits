package com.example.wizytydomowe.Doctor;

import com.example.wizytydomowe.Appointment.AppointmentDto;
import com.example.wizytydomowe.Appointment.AppointmentService;
import com.example.wizytydomowe.Patient.PatientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.print.Doc;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequiredArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    @GetMapping("/{id}")
    ResponseEntity<DoctorDto> getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    ResponseEntity<List<DoctorDto>> findAllDoctors() {
        List<DoctorDto> allDoctors = doctorService.findAllDoctors();
        return ResponseEntity.ok(allDoctors);
    }

    @PostMapping
    ResponseEntity<DoctorDto> saveDoctor(@Valid @RequestBody DoctorDto doctorDto) {
        DoctorDto savedDoctor = doctorService.saveDoctor(doctorDto);
        Optional<AppointmentDto> lastAppointment = appointmentService.getAppointmentById(appointmentService.getLastAppointmentId());

       /* lastAppointment.setDoctorId(savedDoctor.getId());

        appointmentService.saveAppointment(lastAppointment);*/

        URI savedAppointmentUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedDoctor.getId())
                .toUri();
        return ResponseEntity.created(savedAppointmentUri).body(savedDoctor);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();

    }
}
