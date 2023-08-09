package br.com.car.api.controller;

import br.com.car.api.dto.AddressDto;
import br.com.car.api.model.Address;
import br.com.car.api.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/endereco")
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping
    public ResponseEntity<Address> addAddress(@RequestBody AddressDto addressDto) {
        return addressService.addAddress(addressDto);
    }

    @GetMapping
    public Page<AddressDto> getAddress(Pageable pageable) {
        return addressService.getAddress(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable("id") Long id) {
        return addressService.getAddressById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Address> deleteAddress(@PathVariable("id") Long id) {
        return addressService.deleteAddress(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Address> attAddress(@PathVariable("id") Long id, @RequestBody AddressDto addressDto) {
        return addressService.attAddress(id, addressDto);
    }

}
