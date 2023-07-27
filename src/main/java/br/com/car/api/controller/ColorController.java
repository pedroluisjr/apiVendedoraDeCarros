package br.com.car.api.controller;

import br.com.car.api.dto.ColorDto;
import br.com.car.api.dto.ColorReturnDto;
import br.com.car.api.model.Color;
import br.com.car.api.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cor")
public class ColorController {

    @Autowired
    ColorService colorService;

    @GetMapping
    public Page<ColorReturnDto> getColors(Pageable pageable) {
        return colorService.getColors(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Color> getColorById(@PathVariable("id") Long id) {
        return colorService.getColorById(id);
    }

    @PostMapping
    public ResponseEntity<Color> addColor(@RequestBody ColorDto colorDto) {
        return colorService.addColor(colorDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Color> attColor(@PathVariable("id") Long id, @RequestBody ColorDto colorDto) {
        return colorService.attColor(id, colorDto);
    }

}
