package com.merlos.antonio.superheroapi.controller;

import com.merlos.antonio.superheroapi.annotations.ExecutionTime;
import com.merlos.antonio.superheroapi.dto.SuperheroDTO;
import com.merlos.antonio.superheroapi.mapper.SuperheroMapper;
import com.merlos.antonio.superheroapi.model.Superhero;
import com.merlos.antonio.superheroapi.service.impl.SuperheroServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@Slf4j
public class SuperheroController {

    private final SuperheroServiceImpl service;

    private final SuperheroMapper mapper;

    public SuperheroController(SuperheroServiceImpl service, SuperheroMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/superheroes")
    @Operation(summary = "Get all superheroes")
    @ExecutionTime("getAllSuperheroes()")
    public ResponseEntity<List<SuperheroDTO>> getAllSuperheroes(){

        List<Superhero> superheroes = service.getAllSuperheroes();

        return ResponseEntity.ok().body(mapper.asSuperheroDtoList(superheroes));
    }

    @GetMapping("/superhero")
    @Operation(summary = "Get a superhero by its Id")
    @ExecutionTime("getSuperheroById()")
    public ResponseEntity<SuperheroDTO> getSuperheroById(@RequestParam(name = "id") Long id){

        try{
            Superhero superhero = service.getSuperheroById(id);
            return ResponseEntity.ok().body(mapper.asSuperheroDto(superhero));
        } catch (EntityNotFoundException e){
            log.warn("Superhero with id ({}) doesn't exist in DB", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping("superheroes/{value}")
    @Operation(summary = "Get a list of superheroes containing the search values on its name")
    @ExecutionTime("getSuperheroesByNameContaining()")
    public ResponseEntity<List<SuperheroDTO>> getSuperheroesByNameContaining(@PathVariable String value){

        List<Superhero> superheroList = service.getSuperheroesByNameContainingIgnoreCase(value);

        return ResponseEntity.ok().body(mapper.asSuperheroDtoList(superheroList));
    }

    @PostMapping("/superheroes")
    @Operation(summary = "Create a superhero")
    @ExecutionTime("createSuperhero()")
    public ResponseEntity<SuperheroDTO> createSuperhero(@RequestBody SuperheroDTO dto){

        Superhero superhero = mapper.asSuperhero(dto);
        superhero = service.createSuperhero(superhero);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.asSuperheroDto(superhero));
    }

    @PutMapping("/superheroes")
    @Operation(summary = "Update a superhero name by Id")
    @ExecutionTime("updateSuperhero()")
    public ResponseEntity<SuperheroDTO> updateSuperhero(@RequestParam(name = "id") Long id, @RequestBody SuperheroDTO dto){

        try {
            Superhero superhero = mapper.asSuperhero(dto);
            superhero.setId(id);
            Superhero existingSuperhero = service.updateSuperhero(id, superhero);
            return ResponseEntity.ok().body(mapper.asSuperheroDto(existingSuperhero));
        } catch (Exception e) {
            log.warn("Superhero with id ({}) doesn't exist in DB", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }

    @DeleteMapping("/superheroes")
    @Operation(summary = "Delete a superhero by Id")
    @ExecutionTime("deleteSuperhero()")
    public ResponseEntity<Void> deleteSuperhero(@RequestParam(name = "id") Long id){
        try {
            service.deleteSuperheroById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.warn("Superhero with id ({}) doesn't exist in DB", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
