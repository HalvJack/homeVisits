package com.example.wizytydomowe.Doctor;

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
}
