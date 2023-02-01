package com.example.wizytydomowe.Patient;

import com.example.wizytydomowe.Address.Address;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientDto {
    private Integer id;
    private String name;
    private String surname;
    private String pesel;
//    private LocalDate dateOfBirth;
    private Address address;
    private String phoneNumber;
    private String email;
}
