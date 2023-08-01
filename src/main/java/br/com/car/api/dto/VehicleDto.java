package br.com.car.api.dto;

import br.com.car.api.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {

    @NotBlank
    private String modelName;

    @NotBlank
    private String fuelType;

    @NotNull
    private int fabricationYear;

    @NotNull
    private int model;

    @NotBlank
    private String plate;

    @NotNull
    private int occupants;

    private Long typeId;

    private Long colorId;

    private Long manufacturerId;

    private static ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.createTypeMap(VehicleDto.class, Vehicle.class)
                .addMapping(VehicleDto::getTypeId, Vehicle::setTypeId)
                .addMapping(VehicleDto::getColorId, Vehicle::setColorId)
                .addMapping(VehicleDto::getManufacturerId, Vehicle::setManufacturerId);

        modelMapper.createTypeMap(Vehicle.class, VehicleDto.class)
                .addMapping(lambda -> lambda.getColorId().getColorId(), VehicleDto::setColorId)
                .addMapping(lambda -> lambda.getTypeId().getTypeId(), VehicleDto::setTypeId)
                .addMapping(lambda -> lambda.getManufacturerId().getId(), VehicleDto::setManufacturerId);
    }
    public Vehicle toVehicle() {
        return modelMapper.map(this, Vehicle.class);
    }

    public VehicleDto(Vehicle vehicle) {
        modelMapper.map(vehicle, this);
    }

}
