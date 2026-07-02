package com.gerardo.curso.springboot.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gerardo.curso.springboot.jpa.entities.Person;
import com.gerardo.curso.springboot.jpa.repositories.PersonRepository;

//Como no vamos a usar la parte web vamos a implementar una interface CommandLineRunner
@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner{

	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// aqui vamos a trabajar con nuestra persistancia

		// List<Person> persons = (List<Person>)repository.findAll();
		List<Person> persons = (List<Person>)repository.findByProgrammingLanguageAndName("Java","Gerardo");
		persons.stream().forEach(person -> {
			System.out.println(person);
		});
	}
}
