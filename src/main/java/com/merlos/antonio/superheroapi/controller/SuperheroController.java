package com.merlos.antonio.superheroapi.controller;

import com.merlos.antonio.superheroapi.dto.SuperheroDTO;
import com.merlos.antonio.superheroapi.mapper.SuperheroMapper;
import com.merlos.antonio.superheroapi.model.Superhero;
import com.merlos.antonio.superheroapi.service.impl.SuperheroServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SuperheroController {

    private final SuperheroServiceImpl service;

    private final SuperheroMapper mapper;

    public SuperheroController(SuperheroServiceImpl service, SuperheroMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/superheroes")
    @Operation(summary = "Get all superheroes")
    public ResponseEntity<List<SuperheroDTO>> getAllSuperheroes(){

        List<Superhero> superheroes = service.getAllSuperheroes();

        return ResponseEntity.ok().body(mapper.asSuperheroDtoList(superheroes));
    }

    @GetMapping("/superhero")
    @Operation(summary = "Get a superhero by its Id")
    public ResponseEntity<SuperheroDTO> getSuperheroById(@RequestParam(name = "id") Long id){

        Superhero superhero = service.getSuperheroById(id);

        if (superhero == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(mapper.asSuperheroDto(superhero));
    }

    @GetMapping("superheroes/{value}")
    @Operation(summary = "Get a list of superheroes containing the search values on its name")
    public ResponseEntity<List<SuperheroDTO>> getSuperheroByNameContaining(@PathVariable String value){

        List<Superhero> superheroList = service.getSuperheroesByNameContainingIgnoreCase(value);

        return ResponseEntity.ok().body(mapper.asSuperheroDtoList(superheroList));
    }

    @PostMapping("/superheroes")
    @Operation(summary = "Create a superhero")
    public ResponseEntity<SuperheroDTO> createSuperhero(@RequestBody SuperheroDTO dto){

        Superhero superhero = mapper.asSuperhero(dto);
        superhero = service.createSuperhero(superhero);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.asSuperheroDto(superhero));
    }

    //TODO FIX
    @PutMapping("/superheroes")
    @Operation(summary = "Update a superhero name by Id")
    public ResponseEntity<SuperheroDTO> updateSuperhero(@RequestParam(name = "id") Long id, @RequestBody SuperheroDTO dto){

        Superhero existingSuperhero = service.getSuperheroById(id);

        if (existingSuperhero == null){
            return ResponseEntity.notFound().build();
        }

        Superhero superhero = mapper.asSuperhero(dto);
        superhero.setId(id);
        return ResponseEntity.ok().body(mapper.asSuperheroDto(superhero));
    }

    @DeleteMapping("/superheroes")
    @Operation(summary = "Delete a superhero by Id")
    public ResponseEntity<?> deleteSuperhero(@RequestParam(name = "id") Long id){
        Superhero superhero = service.getSuperheroById(id);
        if (superhero == null){
            return ResponseEntity.notFound().build();
        }

        service.deleteSuperheroById(id);
        return ResponseEntity.noContent().build();
    }
}
