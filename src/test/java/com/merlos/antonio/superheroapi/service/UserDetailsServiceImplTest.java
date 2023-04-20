package com.merlos.antonio.superheroapi.service;

import com.merlos.antonio.superheroapi.model.User;
import com.merlos.antonio.superheroapi.repository.UserRepository;
import com.merlos.antonio.superheroapi.service.impl.UserDetailServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailServiceImpl service;

    @Mock
    private UserRepository repo;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsernameSuccess(){

        String email = "user@email.com";
        User user = new User();
        user.setEmail(email);
        user.setPassword("password");

        Mockito.when(repo.findOneByEmail(email)).thenReturn(Optional.of(user));

        UserDetails result = service.loadUserByUsername(email);

        Assertions.assertEquals(email, result.getUsername());
        Assertions.assertEquals(user.getPassword(), result.getPassword());
    }

    @Test
    void loadUserByUsernameNotFound(){

        String email = "user@email.com";

        Mockito.when(repo.findOneByEmail(email)).thenReturn(Optional.empty());

        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            service.loadUserByUsername(email);
        });
    }
}
