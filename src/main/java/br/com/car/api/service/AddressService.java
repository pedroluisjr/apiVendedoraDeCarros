package br.com.car.api.service;

import br.com.car.api.dto.AddressDto;
import br.com.car.api.model.Address;
import br.com.car.api.model.AddressType;
import br.com.car.api.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.naming.directory.NoSuchAttributeException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AddressTypeService addressTypeService;

    public ResponseEntity<Address> addAddress(AddressDto addressDto) {
        Address address = addressDto.toAddress();
        AddressType existAddressType = addressTypeService.getAddressTypeIdInt(addressDto.getAddressTypeId()).orElse(null);
        if (existAddressType != null) {
            address.setAddressType(existAddressType);
            addressRepository.save(address);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    public Page<AddressDto> getAddress(Pageable pageable) {
        return addressRepository.findAll(pageable).map(AddressDto::new);
    }

    public ResponseEntity<Address> getAddressById(Long id) {
        Optional<Address> existAddress = addressRepository.findById(id);
        if (existAddress.isPresent()) {
            return ResponseEntity.of(existAddress);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public Optional<Address> getAddressByIdInt(Long id) {
        return addressRepository.findById(id);
    }

    public ResponseEntity<Address> deleteAddress(Long id) {
        Optional<Address> existAddress = addressRepository.findById(id);
        if (existAddress.isPresent()) {
            addressRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<Address> attAddress(Long id, AddressDto addressDto) {
        Optional<Address> existAddress = addressRepository.findById(id);
        if (existAddress.isPresent()) {
            if (addressDto.getCep() != 0) existAddress.get().setCep(addressDto.getCep());
            if (addressDto.getCity() != null) existAddress.get().setCity(addressDto.getCity());
            if (addressDto.getState() != null) existAddress.get().setState(addressDto.getState());
            if (addressDto.getNumber() != 0) existAddress.get().setNumber(addressDto.getNumber());
            if (addressDto.getCountry() != null) existAddress.get().setCountry(addressDto.getCountry());
            if (addressDto.getStreet() != null) existAddress.get().setStreet(addressDto.getStreet());

            if (addressDto.getAddressTypeId() != null) {
                Optional<AddressType> existType = addressTypeService.getAddressTypeIdInt(addressDto.getAddressTypeId());
                existType.ifPresent(addressType -> existAddress.get().setAddressType(addressType));
            }
            addressRepository.save(existAddress.get());

            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
