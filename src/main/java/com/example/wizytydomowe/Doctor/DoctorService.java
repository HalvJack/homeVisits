package com.example.wizytydomowe.Doctor;

import com.example.wizytydomowe.Patient.Patient;
import com.example.wizytydomowe.Patient.PatientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorDtoMapper doctorDtoMapper;

    List<DoctorDto> findBySpecialization(String specialization){
        List<Doctor> doctorListBySpecialization = doctorRepository.findBySpecialization(specialization);
        return doctorListBySpecialization.stream()
                .map(doctorDtoMapper::map)
                .collect(Collectors.toList());
    }

    Optional<DoctorDto> getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .map(doctorDtoMapper::map);
    }

    List<DoctorDto> findAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream()
                .map(doctorDtoMapper::map)
                .collect(Collectors.toList());
    }

    DoctorDto saveDoctor(DoctorDto doctorDto) {
        Doctor doctor = doctorDtoMapper.map(doctorDto);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return doctorDtoMapper.map(savedDoctor);
    }

    void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

}
