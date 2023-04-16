package com.merlos.antonio.superheroapi.repository;

import com.merlos.antonio.superheroapi.model.Superhero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuperheroRepository extends JpaRepository<Superhero, Long> {

    List<Superhero> findByNameContainingIgnoreCase(String value);
}
