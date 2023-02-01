package com.example.wizytydomowe.Address;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    @GetMapping("{id}")
    ResponseEntity<AddressDto> getAddressById(@PathVariable Integer id){
        return addressService.getAddressById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<AddressDto> saveAddress(@RequestBody AddressDto addressDto){
        AddressDto savedAddress = addressService.saveAddress(addressDto);
        URI savedAddressUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAddress.getId())
                .toUri();
//        return ResponseEntity.created(savedAddressUri).body(savedAddress);
        return ResponseEntity.created(savedAddressUri).body(savedAddress);
    }

}
