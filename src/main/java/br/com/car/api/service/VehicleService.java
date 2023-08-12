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

    public Optional<Vehicle> getVehicleByIdInt(Long id) {
        return vehicleRepository.findById(id);
    }

    public ResponseEntity<Vehicle> deleteVehicle(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if (vehicle.isPresent()) {
            vehicleRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Vehicle> attVehicle(Long id, VehicleDto vehicleDto) {
        Vehicle vehicleSave = vehicleRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if (vehicleDto.getModelName() != null) vehicleSave.setModelName(vehicleDto.getModelName());
        if (vehicleDto.getFuelType() != null) vehicleSave.setFuelType(vehicleDto.getFuelType());
        if (vehicleDto.getFabricationYear() != 0) vehicleSave.setFabricationYear(vehicleDto.getFabricationYear());
        if (vehicleDto.getModel() != 0) vehicleSave.setModel(vehicleDto.getModel());
        if (vehicleDto.getPlate() != null) vehicleSave.setPlate(vehicleDto.getPlate());
        if (vehicleDto.getOccupants() != 0) vehicleSave.setOccupants(vehicleDto.getOccupants());

        if (vehicleDto.getManufacturerId() != null) {
            Optional<Manufacturer> manufacturer = manufacturerService.getManufacturerByIdInt(vehicleDto.getManufacturerId());
            manufacturer.ifPresent(vehicleSave::setManufacturerId);
        }

        if (vehicleDto.getColorId() != null) {
            Optional<Color> color = colorService.getColorByIdInt(vehicleDto.getColorId());
            color.ifPresent(vehicleSave::setColorId);
        }

        if (vehicleDto.getTypeId() != null) {
            Optional<Type> type = typeService.getTypeByIdInt(vehicleDto.getTypeId());
            type.ifPresent(vehicleSave::setTypeId);
        }
        vehicleRepository.save(vehicleSave);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
