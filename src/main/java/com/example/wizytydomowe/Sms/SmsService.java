package com.example.wizytydomowe.Sms;

import com.example.wizytydomowe.Appointment.AppointmentDto;

public interface SmsService {
    void sendSMS(String toPhoneNumber, String message);
    String prepareMessage(AppointmentDto appointment);
}