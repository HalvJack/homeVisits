package com.example.wizytydomowe.HereApi;

import com.example.wizytydomowe.Doctor.DoctorDto;
import org.springframework.stereotype.Service;

@Service
public class DistanceService {
    private static final double EARTH_RADIUS_KM = 6371.0;

    public double calculateDistance(DoctorDto doctorDto, double pointLatitude, double pointLongitude) {
        double officeLatitude = Math.toRadians(doctorDto.getLatitude());
        double officeLongitude = Math.toRadians(doctorDto.getLongitude());
        pointLatitude = Math.toRadians(pointLatitude);
        pointLongitude = Math.toRadians(pointLongitude);

        double dLat = pointLatitude - officeLatitude;
        double dLon = pointLongitude - officeLongitude;

        double a = Math.pow(Math.sin(dLat / 2), 2)
                + Math.cos(officeLatitude) * Math.cos(pointLatitude)
                * Math.pow(Math.sin(dLon / 2),2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_KM * c;
    }
}
