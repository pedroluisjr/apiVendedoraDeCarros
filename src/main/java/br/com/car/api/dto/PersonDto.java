package br.com.car.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
