package com.example.wizytydomowe.Address;

import org.springframework.stereotype.Service;

@Service
public class AddressDtoMapper {
    AddressDto map(Address address){
        AddressDto addressDto = new AddressDto();
        addressDto.setId(address.getId());
        addressDto.setCity(address.getCity());
        addressDto.setZipCode(address.getZipCode());
        addressDto.setStreet(address.getStreet());
        addressDto.setHouseNumber(address.getHouseNumber());
        addressDto.setFlatNumber(address.getFlatNumber());
        return addressDto;
    }
    Address map(AddressDto addressDto){
        Address address = new Address();
        address.setCity(addressDto.getCity());
        address.setZipCode(addressDto.getZipCode());
        address.setStreet(addressDto.getStreet());
        address.setHouseNumber(addressDto.getHouseNumber());
        address.setFlatNumber(addressDto.getFlatNumber());
        return address;
    }
}
