package com.example.wizytydomowe.Patient;

public class Address {
    private Integer id;
    private String city;
    private String zipCode; // w jakim formacie jako string zwykly czy np zeby z pauza bylo czy jako int zwykly
    private String street;
    private Integer houseNumber;
    private Integer flatNumber; // czy to prawidłowo

    public Address(Integer id, String city, String zipCode, String street, Integer houseNumber, Integer flatNumber) {
        this.id = id;
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Integer getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(Integer flatNumber) {
        this.flatNumber = flatNumber;
    }
}
