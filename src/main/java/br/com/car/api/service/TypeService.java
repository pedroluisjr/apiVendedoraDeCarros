package br.com.car.api.service;

import br.com.car.api.dto.TypeDto;
import br.com.car.api.dto.TypeReturnDto;
import br.com.car.api.model.Type;
import br.com.car.api.repository.TypeRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
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

    public Page<TypeReturnDto> getType(Pageable pageable) {
        return typeRepository.findAll(pageable).map(TypeReturnDto::new);
    }

    public ResponseEntity<Type> getTypeById(Long id) {
        Optional<Type> existId = typeRepository.findById(id);
        if (existId.isPresent()) {
            return ResponseEntity.of(typeRepository.findById(id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public Optional<Type> getTypeByIdInt(Long id) {
        return typeRepository.findById(id);
    }

    public ResponseEntity<Type> attType(Long id, TypeDto typeDto) {
        Type existType = typeRepository.findById(id).orElseThrow();
        String toUpperCase = typeDto.getTypeName().toUpperCase();
        Optional<Type> findType = typeRepository.findByTypeName(toUpperCase);
        if (findType.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        existType.setTypeName(toUpperCase);
        return ResponseEntity.ok(typeRepository.save(existType));
    }

}
