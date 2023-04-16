package com.merlos.antonio.superheroapi.service.impl;

import com.merlos.antonio.superheroapi.model.Superhero;
import com.merlos.antonio.superheroapi.repository.SuperheroRepository;
import com.merlos.antonio.superheroapi.service.SuperheroService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class SuperheroServiceImpl implements SuperheroService{

    private SuperheroRepository repo;

    public SuperheroServiceImpl(SuperheroRepository repo){
        this.repo = repo;
    }

    public List<Superhero> getAllSuperheroes(){
        return repo.findAll();
    }

    public Superhero getSuperheroById(Long id){
        return repo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Superhero> getSuperheroesByNameContaining(String value){
        return repo.findByNameContaining(value);
    }

    public void createSuperhero(Superhero newSuperhero){

        repo.save(newSuperhero);
    }

    public Superhero updateSuperhero(Long id, Superhero updatedSuperhero){

        Superhero existingHero = getSuperheroById(id);
        existingHero.setName(updatedSuperhero.getName());

        return repo.save(existingHero);
    }

    public void deleteSuperheroById(Long id){

        repo.deleteById(id);
    }
}
