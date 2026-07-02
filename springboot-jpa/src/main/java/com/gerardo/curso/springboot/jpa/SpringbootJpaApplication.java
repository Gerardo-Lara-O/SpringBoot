package com.gerardo.curso.springboot.jpa;

import java.util.List;
import java.util.Optional;

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
		findOne();
		
	}

	public void findOne(){
		Person person = null;
		Optional<Person> optionalPerson = repository.findById(1L);
		if (optionalPerson.isPresent()) {
			person = optionalPerson.get();
		}
		System.out.println(person);

		// o usamos una abreviacion
		// repository.findById(1L).ifPresent(person -> System.out.println(person));
	}

	public void list(){
		// List<Person> persons = (List<Person>)repository.findAll();
		List<Person> persons = (List<Person>)repository.findByProgrammingLanguageAndName("Java","Gerardo");
		persons.stream().forEach(person -> {
			System.out.println(person);
		});

		List<Object[]> personsValues = repository.obtenerPersonData("Python","Pepe");
		personsValues.stream().forEach(person -> {
			System.out.println(person[0] + " es experto en " + person[1]);
		});
	}
}
