package br.com.car.api.controller;

import br.com.car.api.dto.TypeDto;
import br.com.car.api.dto.TypeReturnDto;
import br.com.car.api.model.Type;
import br.com.car.api.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tipo")
public class TypeController {

    @Autowired
    TypeService typeService;

    @PostMapping
    public ResponseEntity<Type> addType(@RequestBody TypeDto typeDto) {
        return typeService.addType(typeDto);
    }

    @GetMapping
    public Page<TypeReturnDto> getType(Pageable pageable) {
        return typeService.getType(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Type> getTypeById(@PathVariable("id") Long id) {
        return typeService.getTypeById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Type> attType(@PathVariable("id") Long id, @RequestBody TypeDto typeDto) {
        return typeService.attType(id, typeDto);
    }

}
