package br.com.car.api.service;

import br.com.car.api.dto.ColorDto;
import br.com.car.api.dto.ColorReturnDto;
import br.com.car.api.model.Color;
import br.com.car.api.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ColorService {

    @Autowired
    ColorRepository colorRepository;

    public ResponseEntity<Color> addColor(ColorDto colorDto) {
        Color color = colorDto.toColor();
        String toUpperCase = colorDto.getColorName().toUpperCase();
        color.setColorName(toUpperCase);
        Optional<Color> existColor = colorRepository.findByColorName(toUpperCase);
        if (existColor.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(colorRepository.save(color));
    }

    public Page<ColorReturnDto> getColors(Pageable pageable){
        return colorRepository.findAll(pageable).map(ColorReturnDto::new);
    }

    public ResponseEntity<Color> getColorById(Long id) {
        Optional<Color> existColor = colorRepository.findById(id);
        if (existColor.isPresent()){
            return ResponseEntity.of(existColor);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public Color getColorByIdInt(Long id) {
        Optional<Color> existColor = colorRepository.findById(id);
        return existColor.orElse(null);
    }

    public ResponseEntity<Color> attColor(Long id, ColorDto colorDto) {
        Color colorSave = colorRepository.findById(id).orElseThrow();
        String toUpperCase = colorDto.getColorName().toUpperCase();
        colorSave.setColorName(toUpperCase);
        Optional<Color> existColor = colorRepository.findByColorName(toUpperCase);
        if (existColor.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(colorRepository.save(colorSave));
    }

}
