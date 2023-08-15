package br.com.car.api.repository;

import br.com.car.api.dto.PersonReturnDto;
import br.com.car.api.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByCnh(int cnh);

}
