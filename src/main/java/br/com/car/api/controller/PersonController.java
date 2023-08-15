package br.com.car.api.controller;

import br.com.car.api.dto.PersonDto;
import br.com.car.api.dto.PersonReturnDto;
import br.com.car.api.model.Person;
import br.com.car.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pessoa")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody PersonDto personDto) {
        return personService.addPerson(personDto);
    }

    @GetMapping
    public Page<PersonDto> getPerson(Pageable pageable) {
        return personService.getPerson(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonReturnDto> getPersonById(@PathVariable("id") Long id) {
        return personService.getPersonById(id);
    }

    //TODO: Falta realizar os testes de deleção.
    @DeleteMapping("/{id}")
    public ResponseEntity<Person> deletePersonById(@PathVariable("id") Long id) {
        return personService.deleteById(id);
    }

}
