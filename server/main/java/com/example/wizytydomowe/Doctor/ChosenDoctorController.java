package com.example.wizytydomowe.Doctor;

import com.example.wizytydomowe.Sms.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/submittedDoctor")
@Slf4j
public class ChosenDoctorController {

    //private final SmsService smsService;
    @PostMapping
    public ResponseEntity<?> submitDoctor(@RequestBody DoctorDto doctorDto) {

        return ResponseEntity.ok().body("Doctor submitted successfully");
    }
}
