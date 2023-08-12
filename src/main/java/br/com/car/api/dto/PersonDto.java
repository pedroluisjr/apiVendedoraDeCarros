package br.com.car.api.dto;

import br.com.car.api.model.Address;
import br.com.car.api.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    private String personName;
    private String personSurname;
    private int age;
    private int cnh;
    private Long addressId;
    private Long vehicleId;

    private static ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.createTypeMap(PersonDto.class, Person.class)
                .addMapping(PersonDto::getAddressId, Person::setAddress)
                .addMapping(PersonDto::getVehicleId, Person::setVehicle);

        modelMapper.createTypeMap(Person.class, PersonDto.class)
                .addMapping(lambda -> lambda.getAddress().getAddressId(), PersonDto::setAddressId)
                .addMapping(lambda -> lambda.getVehicle().getId(), PersonDto::setVehicleId);
    }
    public Person toPerson() {
        return modelMapper.map(this, Person.class);
    }

    public PersonDto(Person person) {
        modelMapper.map(person, this);
    }

}
