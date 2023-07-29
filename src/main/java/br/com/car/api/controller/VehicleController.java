package br.com.car.api.controller;

import br.com.car.api.dto.VehicleDto;
import br.com.car.api.model.Vehicle;
import br.com.car.api.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/veiculo")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@RequestBody VehicleDto vehicleDto) {
        return vehicleService.addVehicle(vehicleDto);
    }

}
