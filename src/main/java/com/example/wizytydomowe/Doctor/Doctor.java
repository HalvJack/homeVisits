package com.example.wizytydomowe.Doctor;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;

@Entity
@Data
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @Digits(integer = 11, fraction = 0)
    private String phoneNumber;
    private String specialization;
    @Email
    private String email;
}
