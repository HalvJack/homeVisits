package com.example.wizytydomowe.Doctor;

import com.example.wizytydomowe.Patient.Patient;
import com.example.wizytydomowe.Patient.PatientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorDtoMapper doctorDtoMapper;


    Optional<DoctorDto> getDoctorById(Integer id){
        return doctorRepository.findById(id)
                .map(doctorDtoMapper::map);
    }
    DoctorDto saveDoctor(DoctorDto doctorDto){
        Doctor doctor = doctorDtoMapper.map(doctorDto);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return doctorDtoMapper.map(savedDoctor);
    }
}
