package br.com.car.api.controller;

import br.com.car.api.dto.ManufacturerDto;
import br.com.car.api.dto.ManufacturerReturnDto;
import br.com.car.api.model.Manufacturer;
import br.com.car.api.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/fabricante")
@RestController
public class ManufacturerController {

    @Autowired
    ManufacturerService manufacturerService;

    @PostMapping
    public ResponseEntity<Manufacturer> addManufacturer(@Valid @RequestBody ManufacturerDto manufacturerDto){
        return manufacturerService.manufacturerAdd(manufacturerDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> getManufacturerById(@PathVariable("id") Long id) {
        return manufacturerService.getManufacturerById(id);
    }

    @GetMapping
    public Page<ManufacturerReturnDto> getAllManufacturer(@PageableDefault Pageable pageable) {
        return manufacturerService.getAllManufacturer(pageable);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Manufacturer> attManufacturer(@PathVariable("id") Long id,@RequestBody ManufacturerDto manufacturerDto) {
        return manufacturerService.attManufacturer(id, manufacturerDto);
    }

}
