package com.acme.agrodigitalbackend.animals.infrastructure.persistence.jpa.repositories;

import com.acme.agrodigitalbackend.animals.domain.model.aggregates.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByCreatedBy(Long createdBy);
    List<Animal> findByEspecie(String especie);
    List<Animal> findBySexo(String sexo);
    List<Animal> findByEnfermedad(String enfermedad);
}