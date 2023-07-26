package br.com.car.api.dto;

import br.com.car.api.model.Manufacturer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManufacturerReturnDto {

    private Long id;
    private String manufacturerName;
    private String country;

    public Manufacturer toManufacturer () {
        return new ModelMapper().map(this, Manufacturer.class);
    }

    public ManufacturerReturnDto(Manufacturer manufacturer) {
        new ModelMapper().map(manufacturer, this);
    }

}
