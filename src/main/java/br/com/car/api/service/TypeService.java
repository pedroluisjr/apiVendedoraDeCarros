package br.com.car.api.service;

import br.com.car.api.dto.TypeDto;
import br.com.car.api.model.Type;
import br.com.car.api.repository.TypeRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeService {

    @Autowired
    TypeRepository typeRepository;

    public ResponseEntity<Type> addType(TypeDto typeDto) {
        Type type = typeDto.toType();
        String toUpperCase = typeDto.getTypeName().toUpperCase();
        type.setTypeName(toUpperCase);
        Optional<Type> existType = typeRepository.findByTypeName(toUpperCase);
        if (existType.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(typeRepository.save(type));
    }

}
