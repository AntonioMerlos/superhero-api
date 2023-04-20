package com.merlos.antonio.superheroapi.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.merlos.antonio.superheroapi.model.Superhero;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class SuperheroRepositoryTest {

    private static final Resource SUPERHERO_1 = new ClassPathResource("superhero1.json");

    private static final Resource SUPERHERO_2 = new ClassPathResource("superhero2.json");

    private static final Resource SUPERHERO_3 = new ClassPathResource("superhero3.json");

    @Autowired
    private SuperheroRepository repo;

    @Test
    void findByNameContainingIgnoreCaseTest() throws IOException {

        Superhero superhero1 = asSuperheroFromJsonFile(SUPERHERO_1);
        Superhero superhero2 = asSuperheroFromJsonFile(SUPERHERO_2);
        Superhero superhero3 = asSuperheroFromJsonFile(SUPERHERO_3);
        repo.saveAll(Arrays.asList(superhero1, superhero2, superhero3));

        List<Superhero> foundSuperheroes = repo.findByNameContainingIgnoreCase("man");

        assertThat(foundSuperheroes).hasSize(2);
        assertThat(foundSuperheroes).containsExactlyInAnyOrder(superhero1, superhero2);
    }

    private Superhero asSuperheroFromJsonFile(Resource resource) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(resource.getInputStream().readAllBytes(), Superhero.class);
    }
}
