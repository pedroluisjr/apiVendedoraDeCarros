package br.com.car.api.service;

import br.com.car.api.dto.VehicleDto;
import br.com.car.api.model.Color;
import br.com.car.api.model.Manufacturer;
import br.com.car.api.model.Type;
import br.com.car.api.model.Vehicle;
import br.com.car.api.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    TypeService typeService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    ColorService colorService;

    public ResponseEntity<Vehicle> addVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleDto.toVehicle();
        Manufacturer manufacturer = manufacturerService.getManufacturerByIdInt(vehicleDto.getManufacturerId()).orElseThrow(NoSuchElementException::new);
        if (manufacturer.getId() != null) {
            vehicle.setManufacturerId(manufacturer);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Color color = colorService.getColorByIdInt(vehicleDto.getColorId()).orElseThrow(NoSuchElementException::new);
        if (color.getColorId() != null) {
            vehicle.setColorId(color);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Type type = typeService.getTypeByIdInt(vehicleDto.getTypeId()).orElseThrow(NoSuchElementException::new);
        if (type.getTypeId() != null) {
            vehicle.setTypeId(type);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        ResponseEntity.ok(vehicleRepository.save(vehicle));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public Page<VehicleDto> getVehicle(Pageable pageable) {
        return vehicleRepository.findAll(pageable).map(VehicleDto::new);
    }

    public ResponseEntity<Vehicle> getVehicleById(Long id) {
        Optional<Vehicle> existVehicle = vehicleRepository.findById(id);
        if (existVehicle.isPresent()) {
            return ResponseEntity.of(existVehicle);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
