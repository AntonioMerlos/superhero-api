package com.merlos.antonio.superheroapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.merlos.antonio.superheroapi.model.Superhero;
import com.merlos.antonio.superheroapi.repository.SuperheroRepository;
import com.merlos.antonio.superheroapi.service.impl.SuperheroServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class SuperheroServiceImplTest {

    private static final Resource SUPERHERO_1 = new ClassPathResource("superhero1.json");

    private static final Resource SUPERHERO_2 = new ClassPathResource("superhero2.json");

    private static final Resource SUPERHERO_3 = new ClassPathResource("superhero3.json");

    private static final String SEARCH_VALUE = "man";

    @InjectMocks
    private SuperheroServiceImpl service;

    @Mock
    private SuperheroRepository repo;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllSuperheroesTest() throws IOException {

        List<Superhero> superheroes = new ArrayList<>();
        superheroes.add(this.asSuperheroFromJsonFile(SUPERHERO_1));
        superheroes.add(this.asSuperheroFromJsonFile(SUPERHERO_2));
        superheroes.add(this.asSuperheroFromJsonFile(SUPERHERO_3));

        Mockito.when(repo.findAll()).thenReturn(superheroes);

        List<Superhero> result = service.getAllSuperheroes();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(superheroes, result);
    }

    @Test
    void getSuperheroByIdTest() throws IOException {

        final Long id = 1L;
        Superhero superhero = this.asSuperheroFromJsonFile(SUPERHERO_1);

        Mockito.when(repo.findById(id)).thenReturn(Optional.of(superhero));

        Superhero result = service.getSuperheroById(id);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(superhero, result);
    }

    @Test
    void getSuperheroesByNameContainingTest() throws IOException {

        List<Superhero> superheroes = new ArrayList<>();
        superheroes.add(this.asSuperheroFromJsonFile(SUPERHERO_1));
        superheroes.add(this.asSuperheroFromJsonFile(SUPERHERO_2));
        superheroes.add(this.asSuperheroFromJsonFile(SUPERHERO_3));

        Mockito.when(repo.findByNameContainingIgnoreCase(SEARCH_VALUE)).thenReturn(superheroes);

        List<Superhero> result = service.getSuperheroesByNameContainingIgnoreCase(SEARCH_VALUE);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(superheroes, result);
    }

    @Test
    void createSuperheroTest() throws IOException {

        Superhero superhero = this.asSuperheroFromJsonFile(SUPERHERO_1);

        service.createSuperhero(superhero);

        Mockito.verify(repo, Mockito.times(1)).save(superhero);
    }

    @Test
    void updateSuperheroTest() throws IOException {

        final Long id = 1L;
        Superhero existingSuperhero = this.asSuperheroFromJsonFile(SUPERHERO_1);
        Superhero updatedSuperhero = this.asSuperheroFromJsonFile(SUPERHERO_3);

        Mockito.when(repo.findById(id)).thenReturn(Optional.of(existingSuperhero));
        Mockito.when(repo.save(existingSuperhero)).thenReturn(updatedSuperhero);

        Superhero result = service.updateSuperhero(id, updatedSuperhero);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(updatedSuperhero, result);
    }

    @Test
    void deleteSuperheroTest() throws IOException {

        Long id = 1L;
        List<Superhero> superheroList = new ArrayList<>();
        superheroList.add(this.asSuperheroFromJsonFile(SUPERHERO_1));

        service.deleteSuperheroById(id);

        Mockito.verify(repo, Mockito.times(1)).deleteById(id);
    }

    private Superhero asSuperheroFromJsonFile(Resource resource) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(resource.getInputStream().readAllBytes(), Superhero.class);
    }
}
