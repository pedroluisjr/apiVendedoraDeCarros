package br.com.car.api.service;

import br.com.car.api.dto.ManufacturerDto;
import br.com.car.api.dto.ManufacturerReturnDto;
import br.com.car.api.model.Manufacturer;
import br.com.car.api.repository.ManufacturerRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManufacturerService {

    @Autowired
    ManufacturerRepository manufacturerRepository;

    public ResponseEntity<Manufacturer> manufacturerAdd(ManufacturerDto manufacturerDto) {
        Manufacturer manufacturer = manufacturerDto.toManufacturer();
        String toUpperCase = manufacturer.getManufacturerName().toUpperCase();
        manufacturer.setManufacturerName(toUpperCase);
        Optional<Manufacturer> existManufacturer = manufacturerRepository.findByManufacturerName(toUpperCase);
        if (existManufacturer.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
            return ResponseEntity.ok(manufacturerRepository.save(manufacturer));
        }

        public ResponseEntity<Manufacturer> getManufacturerById(Long id) {
            Optional<Manufacturer> manufacturer = manufacturerRepository.findById(id);
            if (manufacturer.isPresent()) {
                return ResponseEntity.of(manufacturer);

            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    public Optional<Manufacturer> getManufacturerByIdInt(Long id) {
        return manufacturerRepository.findById(id);
    }
        public Page<ManufacturerReturnDto> getAllManufacturer(Pageable pageable) {
        return manufacturerRepository.findAll(pageable).map(ManufacturerReturnDto::new);
        }

    public ResponseEntity<Manufacturer> attManufacturer(Long id, ManufacturerDto manufacturerDto) {
        Manufacturer manufacturerSave = manufacturerRepository.findById(id).orElseThrow();
        if (manufacturerDto.getCountry() != null) manufacturerSave.setCountry(manufacturerDto.getCountry());

        if (manufacturerDto.getManufacturerName() != null) {
            String toUpperCase = manufacturerDto.getManufacturerName().toUpperCase();
            Optional<Manufacturer> existManufacturer = manufacturerRepository.findByManufacturerName(toUpperCase);
            if (existManufacturer.isPresent()){
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            manufacturerSave.setManufacturerName(toUpperCase);
        }
        return ResponseEntity.ok(manufacturerRepository.save(manufacturerSave));
    }

}
