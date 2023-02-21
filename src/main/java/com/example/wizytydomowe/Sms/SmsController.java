package com.example.wizytydomowe.Sms;

import com.example.wizytydomowe.Email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController
@RequiredArgsConstructor
public class SmsController {

    //private final String SID = "AC7966eefa166474ad1c7beb3c6479581b";
    //private final String Auth = "8f6344b9c113b87fed40dec803fa6406";
    //private final String Number = "+19402027513";

    @GetMapping(value = "/sendSMS")
    public ResponseEntity<String> sendSMS() {
        //SmsService smsService;
        //smsService = new SmsService();
        Twilio.init("AC7966eefa166474ad1c7beb3c6479581b", "8f6344b9c113b87fed40dec803fa6406");
        //Twilio.setUsername(Twilio.getRestClient().getAccountSid());

        Message.creator(new PhoneNumber("+48506193161"),
                new PhoneNumber("+19402027513"), "Pacjent Jakub Hałucha chce zarezerwować wizytę").create();

        return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
    }
}
