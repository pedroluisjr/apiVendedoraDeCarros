package br.com.car.api.dto;

import br.com.car.api.model.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColorReturnDto {
    private Long colorId;
    private String colorName;

    public Color toColor() {
        return new ModelMapper().map(this, Color.class);
    }

    public ColorReturnDto(Color color) {
        new ModelMapper().map(color, this);
    }
}
