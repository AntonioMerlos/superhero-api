package com.merlos.antonio.superheroapi.service;

import com.merlos.antonio.superheroapi.model.Superhero;

import java.util.List;

public interface SuperheroService {

    Superhero createSuperhero(Superhero superhero);

    Superhero getSuperheroById(Long id);

    List<Superhero> getAllSuperheroes();

    List<Superhero> getSuperheroesByNameContaining(String name);

    Superhero updateSuperhero(Long id, Superhero superhero);

    void deleteSuperheroById(Long id);
}
