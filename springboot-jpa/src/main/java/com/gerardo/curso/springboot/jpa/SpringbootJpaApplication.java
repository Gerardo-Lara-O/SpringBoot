package com.gerardo.curso.springboot.jpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.gerardo.curso.springboot.jpa.entities.Person;
import com.gerardo.curso.springboot.jpa.repositories.PersonRepository;

//Como no vamos a usar la parte web vamos a implementar una interface CommandLineRunner
@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// aqui vamos a trabajar con nuestra persistancia
		// findOne();
		// create();
		// update();
		delete();
	}

	@Transactional(readOnly = true)
	public void findOne() {
		Person person = null;
		Optional<Person> optionalPerson = repository.findById(1L);
		if (optionalPerson.isPresent()) {
			person = optionalPerson.get();
		}
		System.out.println(person);

		// o usamos una abreviacion
		// repository.findById(1L).ifPresent(person -> System.out.println(person));

		repository.findOne(6L).ifPresent(p -> System.out.println(p));
	}

	@Transactional(readOnly = true)
	public void list() {
		// List<Person> persons = (List<Person>)repository.findAll();
		List<Person> persons = (List<Person>) repository.findByProgrammingLanguageAndName("Java", "Gerardo");
		persons.stream().forEach(person -> {
			System.out.println(person);
		});

		List<Object[]> personsValues = repository.obtenerPersonData("Python", "Pepe");
		personsValues.stream().forEach(person -> {
			System.out.println(person[0] + " es experto en " + person[1]);
		});
	}

	@Transactional
	public void create() {
		Scanner scanner = new Scanner(System.in);
		String name = scanner.next();
		String lastname = scanner.next();
		String programmingLanguage = scanner.next();
		scanner.close();

		Person person = new Person(null, name, lastname, programmingLanguage);

		Person personNew = repository.save(person);
		System.out.println(personNew);
	}

	@Transactional
	public void update() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id de la persona: ");
		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = repository.findById(id);

		if (optionalPerson.isPresent()) {
			Person p = optionalPerson.orElseThrow();

			System.out.println(p);
			System.out.println("Ingrese el lenguage de programacion: ");
			String programmingLanguage = scanner.next();
			p.setProgrammingLanguage(programmingLanguage);
			Person personDb = repository.save(p);
			System.out.println(personDb);

		} else {
			System.out.println("El usuario no existe");
		}

		scanner.close();

	}

	@Transactional
	public void delete(){
		// primera forma
		// Scanner scanner = new Scanner(System.in);
		// System.out.println("Ingrese el id a eliminar");
		// Long id = scanner.nextLong();

		// repository.deleteById(id);
		// repository.findAll().forEach(System.out::println);

		// Segunda forma
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id a eliminar");
		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = repository.findById(id);
		optionalPerson.ifPresentOrElse(person -> repository.delete(person),() -> System.out.println("Lo sentimos no existe!"));
		// buscar todos
		repository.findAll().forEach(System.out::println);
		
		scanner.close();
	}
}
