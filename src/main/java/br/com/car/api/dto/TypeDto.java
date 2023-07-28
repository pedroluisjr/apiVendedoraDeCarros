package br.com.car.api.dto;

import br.com.car.api.model.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeDto {

    private String typeName;

    public Type toType(){
        return new ModelMapper().map(this, Type.class);
    }

    public TypeDto(Type type) {
        new ModelMapper().map(type, this);
    }

}
