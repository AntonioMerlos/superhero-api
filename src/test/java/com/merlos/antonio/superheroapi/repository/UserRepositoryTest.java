package com.merlos.antonio.superheroapi.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.merlos.antonio.superheroapi.model.Superhero;
import com.merlos.antonio.superheroapi.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    private static final Resource USER = new ClassPathResource("user.json");

    private static final String USER_EMAIL = "user@email.com";

    private static final String USER_PASSWORD = "password";


    @Autowired
    private UserRepository repo;

    @Test
    void testFindOneByEmail() throws IOException {

        User user = this.asUserFromJsonFile(USER);
        repo.save(user);

        Optional<User> result = repo.findOneByEmail(USER_EMAIL);

        assertThat(result).isPresent();
        Assertions.assertEquals(USER_EMAIL, result.get().getEmail());
        Assertions.assertEquals(USER_PASSWORD, result.get().getPassword());
    }

    private User asUserFromJsonFile(Resource resource) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(resource.getInputStream().readAllBytes(), User.class);
    }
}
