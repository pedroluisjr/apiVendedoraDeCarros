package br.com.car.api.dto;

import br.com.car.api.model.Address;
import br.com.car.api.model.AddressType;
import br.com.car.api.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private String street;
    private int number;
    private int cep;
    private String city;
    private String state;
    private String country;
    private Long addressTypeId;

    private static ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.createTypeMap(AddressDto.class, Address.class).addMapping(AddressDto::getAddressTypeId, Address::setAddressType);

        modelMapper.createTypeMap(Address.class, AddressDto.class).addMapping(lambda -> lambda.getAddressType().getAddressTypeId(), AddressDto::setAddressTypeId);
    }
    public Address toAddress() {
        return modelMapper.map(this, Address.class);
    }

    public AddressDto(Address address) {
        modelMapper.map(address, this);
    }

}
