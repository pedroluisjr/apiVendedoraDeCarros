package br.com.car.api.repository;

import br.com.car.api.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

    Optional<Color> findByColorName(String colorName);

}
