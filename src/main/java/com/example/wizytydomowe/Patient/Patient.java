package com.example.wizytydomowe.Patient;

import com.example.wizytydomowe.Address.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    private String pesel;
    private LocalDate dateOfBirth;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    private String phoneNumber;
    private String email;
}
