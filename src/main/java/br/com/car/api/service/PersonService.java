package br.com.car.api.service;

import br.com.car.api.dto.PersonDto;
import br.com.car.api.dto.PersonReturnDto;
import br.com.car.api.model.Address;
import br.com.car.api.model.Person;
import br.com.car.api.model.Vehicle;
import br.com.car.api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<PersonDto> getPerson(Pageable pageable) {
        return personRepository.findAll(pageable).map(PersonDto::new);
    }

    public ResponseEntity<PersonReturnDto> getPersonById(Long id) {
        Optional<Person> existId = personRepository.findById(id);
        if (existId.isPresent()) {
            return ResponseEntity.of(existId.map(PersonReturnDto::new));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //TODO: Falta realizar os testes de deleção.
    public ResponseEntity<Person> deleteById(Long id) {
        Optional<Person> existId = personRepository.findById(id);

        if (existId.isPresent()) {
            personRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
