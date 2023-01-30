package com.example.wizytydomowe.Appointment;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AppointmentRepository {
    private final List<Appointment> appointments = new ArrayList<>();

//    public AppointmentRepository() {
//        appointments.add(
//          new Appointment()
//        );
//    }
}
