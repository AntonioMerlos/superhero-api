package com.merlos.antonio.superheroapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SuperheroApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperheroApiApplication.class, args);

		// System.out.println(new BCryptPasswordEncoder().encode("password")); // Only for first time setting up a user's password
	}

}
