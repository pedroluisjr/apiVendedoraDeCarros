package br.com.car.api.service;

import br.com.car.api.dto.PersonDto;
import br.com.car.api.model.Person;
import br.com.car.api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    //TODO; realizar as implementações dos métodos de pessoas.
//    public ResponseEntity<Person> addPerson(PersonDto personDto) {
//
//    }

}
