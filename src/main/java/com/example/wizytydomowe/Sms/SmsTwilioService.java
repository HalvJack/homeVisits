package com.example.wizytydomowe.Sms;

import com.example.wizytydomowe.Appointment.AppointmentDto;
import com.example.wizytydomowe.Patient.PatientDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.time.format.DateTimeFormatter;

@Service
public class SmsTwilioService implements SmsService {

    @Value("${twilio.sid}")
    private String SID;

    @Value("${twilio.authToken}")
    private String AUTH_TOKEN;

    @Value("${twilio.number}")
    private String TWILIO_NUMBER;

    @Override
    public void sendSMS(String toPhoneNumber, String message) {
        Twilio.init(SID, AUTH_TOKEN);
        Message.creator(new PhoneNumber(toPhoneNumber),
                new PhoneNumber(TWILIO_NUMBER), message).create();
    }
    @Override
    public String prepareMessage(AppointmentDto appointment) {
        PatientDto patient = appointment.getPatient();

        return String.format("Patient %s %s booked a visit of %s importance on %s. Phone number to the patient (%s %s) is:" +
                        " %s. Answer YES to accept the visit, answer NO to reject the visit.",
                patient.getName(), patient.getSurname(), appointment.getImportance(),
                patient.getAddress(), patient.getName(), patient.getSurname(),
                patient.getPhoneNumber());
    }
}
