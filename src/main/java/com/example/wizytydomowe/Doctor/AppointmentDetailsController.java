package com.example.wizytydomowe.Doctor;

import com.example.wizytydomowe.HereApi.DistanceService;
import com.example.wizytydomowe.HereApi.PriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
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
    private final PriceService priceService;
    private static final Logger logger = LoggerFactory.getLogger(AppointmentDetailsController.class);
    @PostMapping
    ResponseEntity<List<DoctorWithPriceDto>> findAvailableDoctors(@RequestBody LocationDto locationDto) {
        double userLongitude = locationDto.getLongitude();
        double userLatitude = locationDto.getLatitude();
        String specialization = locationDto.getSpecialization();

        List<DoctorDto> availableDoctorsWithGivenSpecialization = doctorService.findBySpecialization(specialization);
        List<DoctorWithPriceDto> doctorsWithPrices = availableDoctorsWithGivenSpecialization
                .stream()
                .filter(doctorDto -> distanceService.calculateDistance(doctorDto, userLatitude, userLongitude) <= 50)
                .map(doctorDto -> new DoctorWithPriceDto(doctorDto, priceService.calculateVisitPrice(doctorDto, userLatitude, userLongitude)))
                .collect(Collectors.toList());
        logger.info("Doctors list with prices: {}", doctorsWithPrices);

        return ResponseEntity.ok(doctorsWithPrices);
    }
}
