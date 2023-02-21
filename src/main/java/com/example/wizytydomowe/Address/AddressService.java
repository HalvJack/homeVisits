package com.example.wizytydomowe.Address;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressDtoMapper addressDtoMapper;

    public AddressService(AddressRepository adddressRepository, AddressDtoMapper addressDtoMapper) {
        this.addressRepository = adddressRepository;
        this.addressDtoMapper = addressDtoMapper;
    }

    Optional<AddressDto> getAddressById(Long id) {
        return addressRepository.findById(id)
                .map(addressDtoMapper::map);
    }

    AddressDto saveAddress(AddressDto addressDto) {
        Address address = addressDtoMapper.map(addressDto);
        Address savedAddress = addressRepository.save(address);
        return addressDtoMapper.map(savedAddress);
    }

    void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
