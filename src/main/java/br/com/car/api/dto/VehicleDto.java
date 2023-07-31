package br.com.car.api.dto;

import br.com.car.api.model.Color;
import br.com.car.api.model.Manufacturer;
import br.com.car.api.model.Type;
import br.com.car.api.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {

    private String modelName;
    private String fuelType;
    private int fabricationYear;
    private int model;
    private String plate;
    private int occupants;
    private Long typeId;
    private Long colorId;
    private Long manufacturerId;

    public Vehicle toVehicle() {
        return new ModelMapper().map(this, Vehicle.class);
    }

    public VehicleDto(Vehicle vehicle) {
        new ModelMapper().map(vehicle, this);
    }

}
