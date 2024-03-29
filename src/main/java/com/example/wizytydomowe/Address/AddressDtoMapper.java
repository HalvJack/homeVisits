package com.example.wizytydomowe.Address;

import org.springframework.stereotype.Service;

@Service
public class AddressDtoMapper {
    AddressDto map(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setCity(address.getCity());
        addressDto.setZipCode(address.getZipCode());
        addressDto.setStreet(address.getStreet());
        addressDto.setHouseNumber(address.getHouseNumber());
        addressDto.setFlatNumber(address.getFlatNumber());
        addressDto.setLatitude(addressDto.getLatitude());
        addressDto.setLongitude(addressDto.getLongitude());
        return addressDto;
    }

    Address map(AddressDto addressDto) {
        Address address = new Address();
        address.setId(addressDto.getId());
        address.setCity(addressDto.getCity());
        address.setZipCode(addressDto.getZipCode());
        address.setStreet(addressDto.getStreet());
        address.setHouseNumber(addressDto.getHouseNumber());
        address.setFlatNumber(addressDto.getFlatNumber());
        address.setLatitude(addressDto.getLatitude());
        address.setLongitude(addressDto.getLongitude());
        return address;
    }
}
