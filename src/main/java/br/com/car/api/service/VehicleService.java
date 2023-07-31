package br.com.car.api.service;

import br.com.car.api.dto.VehicleDto;
import br.com.car.api.model.Color;
import br.com.car.api.model.Type;
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

    @Autowired
    ColorService colorService; //fazer isso para os outros.

    public ResponseEntity<Vehicle> addVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleDto.toVehicle();
        Color color = colorService.getColorByIdInt(vehicleDto.getColorId()); //Fazer isso para tipo e fabricante.
        return null;
    }

}
