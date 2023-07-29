package br.com.car.api.service;

import br.com.car.api.dto.VehicleDto;
import br.com.car.api.model.Vehicle;
import br.com.car.api.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    public ResponseEntity<Vehicle> addVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleDto.toVehicle();
        String toUpperCase = vehicleDto.getModelName().toUpperCase();
        vehicle.setModelName(toUpperCase);
        Optional<Vehicle> existVehicle = vehicleRepository.findByModelName(toUpperCase);
        if (existVehicle.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(vehicle);
    }

}
