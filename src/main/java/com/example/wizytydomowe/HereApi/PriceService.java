package com.example.wizytydomowe.HereApi;

import com.example.wizytydomowe.Appointment.AppointmentDto;
import com.example.wizytydomowe.Appointment.AppointmentService;
import com.example.wizytydomowe.Appointment.Importance;
import com.example.wizytydomowe.Doctor.DoctorDto;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Optional;

@Service
public class PriceService {
    private DistanceService distanceService;
    private TripSummary tripSummary;
    private AppointmentService appointmentService;

    private static final double FUEL_COST = 6.5;
    private static final double FUEL_CONSUMPTION = 8.0;
    private static final double PER_100_KM = 100.0;
    private static final double PRICE_PER_MINUTE = 5.0;

    public PriceService(DistanceService distanceService, TripSummary tripSummary) {
        this.distanceService = distanceService;
        this.tripSummary = tripSummary;
    }

    public double calculateVisitPrice(DoctorDto doctorDto, double visitLatitude, double visitLongitude) {
        double distance = distanceService.calculateDistance(doctorDto, visitLatitude, visitLongitude);
        String origin = doctorDto.getLatitude() + "," + doctorDto.getLongitude();
        String destination = visitLatitude + "," + visitLongitude;
        double travelTimeSeconds = tripSummary.getRouteSummary(origin, destination);

        return (distanceCost(distance) +
                travelTimeCost(travelTimeSeconds) +
                calculateAdditionalCostForSpecialization(doctorDto.getSpecialization()) +
                visitTimeCost(LocalTime.now())) * importanceCost(getImportance());
    }


    public double calculateAdditionalCostForSpecialization(String specialization) {
        SpecializationGroup group = SpecializationMapper.getSpecializationGroup(specialization);
        return group.getPriceIncrement();
    }

    public Importance getImportance() {
        Optional<AppointmentDto> serviceAppointmentById = appointmentService.getAppointmentById(appointmentService.getLastAppointmentId());
        return serviceAppointmentById.map(AppointmentDto::getImportance).orElse(null);
    }

    private double distanceCost(double distance) {
        return distance * FUEL_COST * FUEL_CONSUMPTION / PER_100_KM;
    }

    private double travelTimeCost(double travelTimeSeconds) {
        double travelTimeMinutes = travelTimeSeconds / 60;
        return travelTimeMinutes * PRICE_PER_MINUTE;
    }

    private double importanceCost(Importance importance) {
        return ImportancePricing.getAdditionalCost(importance);
    }

    private double visitTimeCost(LocalTime visitTime) {
        TimeOfDayPricing timeOfDay = TimeOfDayPricing.fromHour(visitTime.getHour());
        return timeOfDay.getPriceIncrement();
    }
}