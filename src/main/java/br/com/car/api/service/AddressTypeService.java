package br.com.car.api.service;

import br.com.car.api.dto.AddressTypeDto;
import br.com.car.api.model.AddressType;
import br.com.car.api.repository.AddressTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressTypeService {

    @Autowired
    AddressTypeRepository addressTypeRepository;

    public ResponseEntity<AddressType> addAddressType(AddressTypeDto addressTypeDto) {
        AddressType addressType = addressTypeDto.toAddressType();
        String toUpperCase = addressType.getAddressTypeName().toUpperCase();
        Optional<AddressType> existAddressType = addressTypeRepository.findByAddressTypeName(toUpperCase);
        if (existAddressType.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            addressType.setAddressTypeName(toUpperCase);
            addressTypeRepository.save(addressType);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    public Page<AddressTypeDto> getAddressType(Pageable pageable) {
        return addressTypeRepository.findAll(pageable).map(AddressTypeDto::new);
    }

    public ResponseEntity<AddressType> getAddressTypeId(Long id) {
        Optional<AddressType> existId = addressTypeRepository.findById(id);
        if (existId.isPresent()) {
            return ResponseEntity.of(existId);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<AddressType> deleteAddressType(Long id) {
        Optional<AddressType> existId = addressTypeRepository.findById(id);
        if (existId.isPresent()) {
            addressTypeRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<AddressType> attAddressType(Long id, AddressTypeDto addressTypeDto) {
        AddressType addressType = addressTypeDto.toAddressType();
        String toUpperCase = addressType.getAddressTypeName().toUpperCase();
        Optional<AddressType> existType = addressTypeRepository.findByAddressTypeName(toUpperCase);
        if (existType.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Optional<AddressType> existId = addressTypeRepository.findById(id);
        if (existId.isPresent()) {
            addressType.setAddressTypeName(addressTypeDto.getAddressTypeName());
            addressTypeRepository.save(addressType);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
