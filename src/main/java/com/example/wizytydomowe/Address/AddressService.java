package com.example.wizytydomowe.Address;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    private final AdddressRepository adddressRepository;
    private final AddressDtoMapper addressDtoMapper;

    public AddressService(AdddressRepository adddressRepository, AddressDtoMapper addressDtoMapper) {
        this.adddressRepository = adddressRepository;
        this.addressDtoMapper = addressDtoMapper;
    }

    Optional<AddressDto> getAddressById(Integer id){
        return adddressRepository.findById(id)
                .map(addressDtoMapper::map);
    }
    AddressDto saveAddress(AddressDto addressDto){
        Address address = addressDtoMapper.map(addressDto);
        Address savedAddress = adddressRepository.save(address);
        return addressDtoMapper.map(savedAddress);
    }
}
