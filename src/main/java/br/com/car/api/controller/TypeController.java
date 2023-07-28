package br.com.car.api.controller;

import br.com.car.api.dto.TypeDto;
import br.com.car.api.model.Type;
import br.com.car.api.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tipo")
public class TypeController {

    @Autowired
    TypeService typeService;

    @PostMapping
    public ResponseEntity<Type> addType(@RequestBody TypeDto typeDto) {
        return typeService.addType(typeDto);
    }

}
