package com.example.wizytydomowe.Address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String city;
    private String zipCode; // w jakim formacie jako string zwykly czy np zeby z pauza bylo czy jako int zwykly
    private String street;
    private Integer houseNumber;
    private Integer flatNumber; // Optional
}
