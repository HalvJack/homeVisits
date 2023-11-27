package com.example.wizytydomowe.HereApi;

import com.example.wizytydomowe.Doctor.Doctor;

public class DistanceService {
    // Earth radius in kilometers
    private static final double EARTH_RADIUS_KM = 6371.0;

    public double calculateDistance(Doctor doctor, double pointLatitude, double pointLongitude) {
        double officeLatitude = Math.toRadians(doctor.getLatitude());
        double officeLongitude = Math.toRadians(doctor.getLongitude());
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
