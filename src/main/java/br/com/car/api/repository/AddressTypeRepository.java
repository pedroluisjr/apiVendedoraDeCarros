package br.com.car.api.repository;

import br.com.car.api.model.AddressType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressTypeRepository extends JpaRepository<AddressType, Long> {

    Optional<AddressType> findByAddressTypeName(String addressTypeName);
}
