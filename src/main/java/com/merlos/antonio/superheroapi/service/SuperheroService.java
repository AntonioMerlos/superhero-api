package com.merlos.antonio.superheroapi.service;

import com.merlos.antonio.superheroapi.model.Superhero;
import com.merlos.antonio.superheroapi.repository.SuperheroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperheroService {

    private SuperheroRepository repo;

    public SuperheroService(SuperheroRepository repo){
        this.repo = repo;
    }

    public List<Superhero> getAllSuperheroes(){
        return null;
    }

    public Superhero getSuperheroById(Long id){
        return null;
    }

    public List<Superhero> getSuperheroesByNameContaining(String value){
        return null;
    }

    public void createSuperhero(Superhero newSuperhero){

    }

    public Superhero updateSuperhero(Long id, Superhero updatedSuperhero){
        return null;
    }

    public void deleteSuperheroById(Long id){

    }
}
