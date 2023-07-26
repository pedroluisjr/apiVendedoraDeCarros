package br.com.car.api.controller;

import br.com.car.api.dto.ManufacturerDto;
import br.com.car.api.model.Manufacturer;
import br.com.car.api.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/fabricante")
@RestController
public class ManufacturerController {

    @Autowired
    ManufacturerService manufacturerService;

    @PostMapping
    public ResponseEntity<Manufacturer> addManufacturer(@RequestBody ManufacturerDto manufacturerDto){
        return manufacturerService.manufacturerAdd(manufacturerDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> getManufacturerById(@PathVariable("id") Long id) {
        return manufacturerService.getManufacturerById(id);
    }

}
