package com.example.wizytydomowe.Sms;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController
@RequiredArgsConstructor
public class SmsController {

    @Value("${twilio.sid}")
    private String SID;

    @Value("${twilio.authToken}")
    private String AUTH_TOKEN;

    @Value("${twilio.number}")
    private String TWILIO_NUMBER;

    @GetMapping(value = "/sendSMS")
    public ResponseEntity<String> sendSMS() {
        Twilio.init(SID, AUTH_TOKEN);
        //Twilio.setUsername(Twilio.getRestClient().getAccountSid());

        Message.creator(new PhoneNumber("+48321184460"),
                new PhoneNumber(TWILIO_NUMBER), "Lekarz specjalista ginekolog jk chce zarezerwować dla ciebie wizytę domowa, skusisz się;) ?").create();

        return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
    }
}
