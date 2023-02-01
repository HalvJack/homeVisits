package com.example.wizytydomowe.Appointment;
import com.example.wizytydomowe.Doctor.Doctor;
import com.example.wizytydomowe.Doctor.DoctorDtoMapper;
import com.example.wizytydomowe.Doctor.DoctorRepository;
import com.example.wizytydomowe.Patient.Patient;
import com.example.wizytydomowe.Patient.PatientDtoMapper;
import com.example.wizytydomowe.Patient.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final PatientDtoMapper patientDtoMapper;
    private final DoctorDtoMapper doctorDtoMapper;

    @GetMapping("{id}")
    ResponseEntity<PatientDto1> getAppointmentById(@PathVariable Integer id) {
        return appointmentService.getAppointmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    ResponseEntity<PatientDto1> saveAppointment(@RequestBody PatientDto1 appointmentDto){
        PatientDto1 savedAppointment = appointmentService.saveAppointment(appointmentDto);
        URI savedAppointmentUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAppointment.getId())
                .toUri();
        return ResponseEntity.created(savedAppointmentUri).body(savedAppointment);
    }

    @PostMapping("/save")
    String savePromotion(com.example.wizytydomowe.Patient.PatientDto patientDto) {
        patientRepository.save(patientDtoMapper.map(patientDto));
        return "redirect:/appointment/showPatient";
    }
    @GetMapping("/")
    String promotionList(Model model) {
        model.addAttribute("patientDto", new com.example.wizytydomowe.Patient.PatientDto());
        return "index";
    }

    @GetMapping("/showDoctors")
    String showDoctors(Model model) {
        model.addAttribute("doctors", doctorRepository.findAll());
        model.addAttribute("saveAppointment", new SaveAppointment());
        return "showDoctors";
    }

    @Transactional
    @PostMapping("/saveAppointment")
    String savePromotion(SaveAppointment saveAppointment) {
        System.out.println("**************************"+saveAppointment.getNumber());
        List<Patient> all = patientRepository.findAll();
        Doctor doctor = doctorRepository.findById(saveAppointment.getNumber()).get();
        Patient patient = all.get(all.size() - 1);
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointmentRepository.save(appointment);
        return "success";
    }

    @GetMapping("/showPatient")
    String showPatient(Model model) {
        List<Patient> all = patientRepository.findAll();
        model.addAttribute("patient", patientDtoMapper.map(all.get(all.size()-1)));
        return "showPatient";
    }
}
