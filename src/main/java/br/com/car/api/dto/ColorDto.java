package br.com.car.api.dto;

import br.com.car.api.model.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColorDto {

    private String colorName;

    public Color toColor() {
        return new ModelMapper().map(this, Color.class);
    }

    public ColorDto(Color color) {
        new ModelMapper().map(color, this);
    }

}
