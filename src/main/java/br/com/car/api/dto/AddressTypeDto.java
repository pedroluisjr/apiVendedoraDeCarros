package br.com.car.api.dto;

import br.com.car.api.model.AddressType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressTypeDto {

    private String addressTypeName;

    public AddressType toAddressType() {
        return new ModelMapper().map(this, AddressType.class);
    }

    public AddressTypeDto(AddressType addressType) {
        new ModelMapper().map(addressType, this);
    }

}
