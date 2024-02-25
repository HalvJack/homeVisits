package com.example.wizytydomowe.HereApi;

import com.example.wizytydomowe.Doctor.DoctorDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistanceServiceTest {
    private DistanceService distanceService;

    @BeforeEach
    public void setUp() {
        distanceService = new DistanceService();
    }

    @Test
    public void testCalculateDistanceZero() {
        DoctorDto doctor = new DoctorDto(1L, "John", "Doe", "Specialization", "PhoneNumber", "Email", 34.0522, -118.2437);
        // Testing distance from the point to itself should be zero
        double distance = distanceService.calculateDistance(doctor, 34.0522, -118.2437);
        assertEquals(0, distance, 0.1, "Distance between the same points should be zero.");
    }

    @Test
    public void testCalculateDistanceDifferentPoints() {
        DoctorDto doctor = new DoctorDto(2L, "Jane", "Doe", "Specialization", "PhoneNumber", "Email", 34.0522, -118.2437);
        // Los Angeles to New York: approximately 3936 kilometers
        double distance = distanceService.calculateDistance(doctor, 40.7128, -74.0060);
        assertEquals(3936, distance, 10, "Distance between Los Angeles and New York should be approximately 3936 kilometers.");
    }


}