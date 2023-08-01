package br.com.car.api.controller;

import br.com.car.api.dto.VehicleDto;
import br.com.car.api.model.Vehicle;
import br.com.car.api.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/veiculo")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@Valid @RequestBody VehicleDto vehicleDto) {
        return vehicleService.addVehicle(vehicleDto);
    }

    @GetMapping
    public Page<VehicleDto> getVehicle(Pageable pageable) {
        return vehicleService.getVehicle(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") Long id) {
        return vehicleService.getVehicleById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Vehicle> deleteVehicle(@PathVariable("id") Long id) {
        return vehicleService.deleteVehicle(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Vehicle> attVehicle(@PathVariable("id") Long id, @RequestBody VehicleDto vehicleDto) {
        return vehicleService.attVechicle(id, vehicleDto);
    }

}
