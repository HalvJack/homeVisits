package com.example.wizytydomowe.Patient;

import com.example.wizytydomowe.Appointment.Appointment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientService {
    private final PatientRepository patientRepository;

    private final PatientDtoMapper patientDtoMapper;

    public PatientService(PatientRepository patientRepository, PatientDtoMapper patientDtoMapper) {
        this.patientRepository = patientRepository;
        this.patientDtoMapper = patientDtoMapper;
    }

    Optional<PatientDto> getPatientById(Integer id){
        return patientRepository.findById(id)
                .map(patientDtoMapper::map);
    }
    PatientDto savePatient(PatientDto patientDto){
        Patient patient = patientDtoMapper.map(patientDto);
        Patient savedPatient = patientRepository.save(patient);
        return patientDtoMapper.map(savedPatient);
    }
}
