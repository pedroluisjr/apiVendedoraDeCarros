package br.com.car.api.service;

import br.com.car.api.dto.PersonDto;
import br.com.car.api.model.Address;
import br.com.car.api.model.Person;
import br.com.car.api.model.Vehicle;
import br.com.car.api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    AddressService addressService;

    @Autowired
    VehicleService vehicleService;

    //TODO; realizar as implementações dos métodos de pessoas.
    public ResponseEntity<Person> addPerson(PersonDto personDto) {
        Person person = personDto.toPerson();
        Optional<Person> findCnh = personRepository.findByCnh(personDto.getCnh());

        if (findCnh.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            Address findAddress = addressService.getAddressByIdInt(personDto.getAddressId()).orElseThrow(NoSuchElementException::new);
            if (findAddress != null) {
                person.setAddress(findAddress);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            Vehicle findVehicle = vehicleService.getVehicleByIdInt(personDto.getVehicleId()).orElseThrow(NoSuchElementException::new);
            if (findVehicle != null) {
                person.setVehicle(findVehicle);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        personRepository.save(person);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
