package br.com.car.api.controller;

import br.com.car.api.dto.AddressTypeDto;
import br.com.car.api.model.AddressType;
import br.com.car.api.service.AddressTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tpendereco")
public class AddressTypeController {

    @Autowired
    AddressTypeService addressTypeService;

    @PostMapping
    public ResponseEntity<AddressType> addAddressType(@RequestBody AddressTypeDto addressTypeDto) {
        return addressTypeService.addAddressType(addressTypeDto);
    }

    @GetMapping
    public Page<AddressTypeDto> getAddressType(Pageable pageable) {
        return addressTypeService.getAddressType(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressType> getAddressTypeId(@PathVariable("id") Long id) {
        return addressTypeService.getAddressTypeId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AddressType> deleteAddressType(@PathVariable("id") Long id) {
        return addressTypeService.deleteAddressType(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AddressType> attAddressType(@PathVariable("id") Long id, @RequestBody AddressTypeDto addressTypeDto) {
        return addressTypeService.attAddressType(id, addressTypeDto);
    }

}
