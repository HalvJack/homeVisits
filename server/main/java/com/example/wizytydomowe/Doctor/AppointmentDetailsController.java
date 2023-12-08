package com.example.wizytydomowe.Doctor;

import com.example.wizytydomowe.HereApi.DistanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/location")
@Slf4j
public class AppointmentDetailsController {

    private final DoctorService doctorService;
    private final DistanceService distanceService;
    private static final Logger logger = LoggerFactory.getLogger(AppointmentDetailsController.class);
    @PostMapping
    ResponseEntity<List<DoctorDto>> findAvailableDoctors(@RequestBody LocationDto locationDto) {
        double userLongitude = locationDto.getLongitude();
        double userLatitude = locationDto.getLatitude();
        String specialization = locationDto.getSpecialization();

        List<DoctorDto> availableDoctorsWithGivenSpecialization = doctorService.findBySpecialization(specialization);
        List<DoctorDto> availableDoctorsInTheGivenLocation = availableDoctorsWithGivenSpecialization
                .stream()
                .filter(doctorDto -> distanceService.calculateDistance(doctorDto, userLatitude, userLongitude) <= 50)
                .collect(Collectors.toList());
        logger.info("Doctors list: {}", availableDoctorsInTheGivenLocation);
        return ResponseEntity.ok(availableDoctorsInTheGivenLocation);
    }
}
