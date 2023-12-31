package br.com.car.api.dto;

import br.com.car.api.model.Manufacturer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManufacturerDto {

    @NotBlank
    private String manufacturerName;

    @NotBlank
    private String country;

    public Manufacturer toManufacturer() {
        return new ModelMapper().map(this, Manufacturer.class);
    }

    public ManufacturerDto(Manufacturer manufacturer) {
        new ModelMapper().map(manufacturer, this);
    }

}
