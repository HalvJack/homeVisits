package com.example.wizytydomowe.Sms;

import com.twilio.twiml.messaging.Message;
import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsResponseController {

    @PostMapping(value = "/sms", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> receiveAndRespondSMS(@RequestParam("Body") String body, @RequestParam("From") String from) {
        String message;
        if ("YES".equalsIgnoreCase(body.trim())) {
            message = "You are all set up with the appointment, check out a mail";
        } else if ("NO".equalsIgnoreCase(body.trim())) {
            message = "You rejected the appointment";
        } else {
            message = "Please reply with 'YES' or 'NO'";
        }

        Body responseBody = new Body.Builder(message).build();
        Message sms = new Message.Builder().body(responseBody).build();
        MessagingResponse twiml = new MessagingResponse.Builder().message(sms).build();

        return ResponseEntity.ok(twiml.toXml());
    }
}