package com.example.wizytydomowe.Patient;

import com.example.wizytydomowe.Appointment.Appointment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Validator;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    private final PatientDtoMapper patientDtoMapper;
    //private final Validator validator;


    Optional<PatientDto> getPatientById(Long id) {
        return patientRepository.findById(id)
                .map(patientDtoMapper::map);
    }

    PatientDto savePatient(PatientDto patientDto) {
        Patient patient = patientDtoMapper.map(patientDto);
        Patient savedPatient = patientRepository.save(patient);
        return patientDtoMapper.map(savedPatient);
    }

    void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
