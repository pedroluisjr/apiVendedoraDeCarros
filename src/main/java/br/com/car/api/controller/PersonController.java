package br.com.car.api.controller;

import br.com.car.api.dto.PersonDto;
import br.com.car.api.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pessoa")
public class PersonController {

    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody PersonDto personDto) {
        return null;
    }

}
