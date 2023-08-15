package br.com.car.api.dto;

import br.com.car.api.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonReturnDto {

    private Long personId;
    private String personName;
    private String personSurname;
    private int age;
    private int cnh;
    private Long addressId;
    private Long vehicleId;
    private Date createdAt;
    private Date updatedAt;

    private static ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.createTypeMap(PersonReturnDto.class, Person.class)
                .addMapping(PersonReturnDto::getAddressId, Person::setAddress)
                .addMapping(PersonReturnDto::getVehicleId, Person::setVehicle);

        modelMapper.createTypeMap(Person.class, PersonReturnDto.class)
                .addMapping(lambda -> lambda.getAddress().getAddressId(), PersonReturnDto::setAddressId)
                .addMapping(lambda -> lambda.getVehicle().getId(), PersonReturnDto::setVehicleId);
    }
    public Person toPerson() {
        return modelMapper.map(this, Person.class);
    }

    public PersonReturnDto(Person person) {
        modelMapper.map(person, this);
    }

}
