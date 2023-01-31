package com.example.wizytydomowe.Address;

import lombok.Data;

@Data
public class AddressDto {
    private Integer id;
    private String city;
    private String zipCode; // w jakim formacie jako string zwykly czy np zeby z pauza bylo czy jako int zwykly
    private String street;
    private Integer houseNumber;
    private Integer flatNumber; // Optional
}
