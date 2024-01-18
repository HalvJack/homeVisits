package com.example.wizytydomowe.Address;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Data
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    @Pattern(regexp = "^[0-9]{2}-[0-9]{3}")
    private String zipCode;
    private String street;
    private Integer houseNumber;
    private Integer flatNumber;
    private Float longitude;
    private Float latitude;
}
