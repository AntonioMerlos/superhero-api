package com.merlos.antonio.superheroapi.security;

import lombok.Data;

@Data
public class AuthCredentials {

    private String email;

    private String password;
}
