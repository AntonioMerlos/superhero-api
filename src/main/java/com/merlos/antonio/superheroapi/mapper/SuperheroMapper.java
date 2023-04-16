package com.merlos.antonio.superheroapi.mapper;

import com.merlos.antonio.superheroapi.dto.SuperheroDTO;
import com.merlos.antonio.superheroapi.model.Superhero;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SuperheroMapper {

    SuperheroDTO asSuperheroDto(Superhero value);

    List<SuperheroDTO> asSuperheroDtoList(List<Superhero> values);

    Superhero asSuperhero(SuperheroDTO value);

    List<Superhero> asSuperheroList(List<SuperheroDTO> values);
}
