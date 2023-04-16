package com.merlos.antonio.superheroapi.mapper;

import com.merlos.antonio.superheroapi.dto.SuperheroDTO;
import com.merlos.antonio.superheroapi.model.Superhero;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SuperheroMapper {

    public SuperheroDTO asSuperheroDto(Superhero superhero){
        return SuperheroDTO.builder().name(superhero.getName()).build();
    }

    public List<SuperheroDTO> asSuperheroDtoList(List<Superhero> superheroList){

        return superheroList.stream().map(this::asSuperheroDto).collect(Collectors.toList());
    }

    public Superhero asSuperhero(SuperheroDTO dto){

        return Superhero.builder().name(dto.getName()).build();
    }

}
