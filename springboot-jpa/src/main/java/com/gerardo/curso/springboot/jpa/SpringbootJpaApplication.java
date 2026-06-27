package com.gerardo.curso.springboot.jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Como no vamos a usar la parte web vamos a implementar una interface CommandLineRunner
@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// aqui vamos a trabajar con nuestra persistancia
	}

	

}
