package com.gerardo.curso.springboot.jpa;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.gerardo.curso.springboot.jpa.dto.PersonDto;
import com.gerardo.curso.springboot.jpa.entities.Person;
import com.gerardo.curso.springboot.jpa.repositories.PersonRepositorJPQL;
import com.gerardo.curso.springboot.jpa.repositories.PersonRepository;

//Como no vamos a usar la parte web vamos a implementar una interface CommandLineRunner
@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

	@Autowired
	private PersonRepositorJPQL repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// aqui vamos a trabajar con nuestra persistancia
		// findOne();
		// create();
		// update();
		// delete();
		// personalizedQueries();
		// personalizedQueries2();
		// personalizedQueriesDistinct();
		// personalizedQueriesConcatUpperAndLower();
		// personalizedQueriesBetwwen();
		// queriesFunction();
		// subqueries();
		wherein();
	}

	@Transactional(readOnly = true)
	public void personalizedQueries(){

		// System.out.println("========== Consulta solo el nombre por el id ==========");
		// Scanner scanner = new Scanner(System.in);
		// System.out.println("Ingrese el id: ");
		// Long id = scanner.nextLong();
		// String name = repository.getNameById(id);
		// System.out.println(name);


		System.out.println("========== Mostrando solo el nombre completo con concat  ==========");
		// Scanner scanner = new Scanner(System.in);
		// System.out.println("Ingrese el id: ");
		// Long id = scanner.nextLong();
		// String fullName = repository.getFullNameById(id);
		// System.out.println(fullName);

		System.out.println("========== Obtener el arreglo de parametros del objeto por el id ==========");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id: ");
		Long id = scanner.nextLong();

		// Sacamos a la persona de la caja fuerte
		Person persona = repository.obtenerPersonaDataById(id).orElse(null);

		if (persona != null) {
			System.out.println("id=" + persona.getId() + ", nombre=" + persona.getName() + ", apellido=" + persona.getLastName());
		} else {
			System.out.println("Persona no encontrada.");
		}


		System.out.println("========== consulta por campos personalizados lista ==========");
		List<Object[]> regs = repository.obtenerPersonaDataList();
		regs.forEach(p -> {
			System.out.println("id=" + p[0] + ", nombre=" + p[1] + ", apellido=" + p[2]);
		});
	}

	public void personalizedQueries2(){
		System.out.println("========== Consulta por objeto persona y lenguaje de programacion ==========");
		List<Object[]> personsRegs = repository.findAllMixPerson();

		personsRegs.forEach(reg -> {
			System.out.println("programmingLanguage=" + reg[1] + ", person=" + reg[0]);
		});

		System.out.println("========== Consulta que puebla y devuleve objeto entity de una instancia personalizada ==========");
		List<Person> persons = repository.findAllObjectPersonPersonalized();
		persons.forEach(p -> {
			System.out.println(p);
		});

		System.out.println("========== Consulta que puebla y devuleve objeto DTO de una clase personalizada ==========");
		List<PersonDto> personDto = repository.findAllPersonDTO();
		personDto.forEach(dto -> {
			System.out.println(dto);
		});
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesDistinct(){
		System.out.println("========== Consultas con nombres de personas ==========");
		List<String> names = repository.findAllNames();
		names.forEach(System.out::println);


		System.out.println("========== Consultas con nombres de personas unicos ==========");
		List<String> namesDistict = repository.findAllNamesDistinct();
		namesDistict.forEach(System.out::println);

		System.out.println("========== Consulta con lenguage de programacion unicas ==========");
		List<String> languages = repository.findAllProgrammingLanguageDistinct();
		languages.forEach(System.out::println);

		System.out.println("========== Consulta con total de lenguajes de programacion unicas ==========");
		Long totalLanguages = repository.findAllProgrammingLanguageDistinctCount();
		System.out.println("Total de lenguajes: " + totalLanguages);
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesConcatUpperAndLower(){
		System.out.println("========== Consultas nombres y apellidos de personas ==========");
		List<String> names = repository.findFullNameConcat();
		names.forEach(System.out::println);

		System.out.println("========== Consultas nombres y apellidos de personas en Mayusculas ==========");
		List<String> namesUpper = repository.findFullNameConcatUpper();
		namesUpper.forEach(System.out::println);

		System.out.println("========== Consultas nombres y apellidos de personas en Minusculas ==========");
		List<String> namesLower = repository.findFullNameConcatLower();
		namesLower.forEach(System.out::println);

		System.out.println("========== Consultas nombres y apellidos de personas en Minusculas y Mayusculas ==========");
		List<Object[]> regs = repository.findAllPersonDataListCase();
		regs.forEach(reg -> {
			System.out.println("id=" + reg[0] + ", name=" + reg[1] + ", lastname=" + reg[2] + ", lenguaje="+ reg[3]);
		});
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesBetwwen(){
		System.out.println("========== Consultas por rango ==========");
		List<Person> persons = repository.findByIdBetweenOrderByIdDesc(2L,5L);
		persons.forEach(System.out::println);

		System.out.println("========== Consultas por rango de nombre ==========");
		persons = repository.findByNameBetweenOrderByNameDescLastnameAsc("J","Q");
		persons.forEach(System.out::println);

		System.out.println("========== Consultas de usuarios ordenados ==========");
		persons = repository.findAllByOrderByNameDesc();
		persons.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void queriesFunction(){

		Long count = repository.getTotalPerson();
		Long min = repository.getMinId();
		Long max = repository.getMaxId();

		System.out.println("El total de usaurios es: " + count);
		System.out.println("El id min es: " + min);
		System.out.println("El id max es: " + max);

		System.out.println("========== Consulta con el nombre y su largo  ==========");
		List<Object[]> regs = repository.getPersonNameLength();
		regs.forEach(reg -> {
			String name = (String)reg[0];
			Integer length = (Integer)reg[1];
			System.out.println("name=" + name + ", length=" + length);
		});

		System.out.println("========== Consulta con el nombre mas corto  ==========");
		Integer minLengthName = repository.getMinLengthName();
		System.out.println(minLengthName);

		System.out.println("========== Consulta con el nombre mas largo  ==========");
		Integer maxLengthName = repository.getMaxLengthName();
		System.out.println(maxLengthName);

		System.out.println("========== Consultas resumen de funciones de agregacion min, max, sum, avg, count  ==========");
		Object[] resumeReg = (Object[]) repository.getResumeAggregationFunction();
		System.out.println("min=" + resumeReg[0] + ", max=" + resumeReg[1] + ", sum= " + resumeReg[2] + ", avg=" + resumeReg[3] + ", count=" + resumeReg[4]);
	}

	@Transactional(readOnly = true)
	public void subqueries(){
		System.out.println("====== consulta por el nombre mas corto y su largo ======");
		List<Object[]> registers = repository.getShorterName();
		registers.forEach(reg -> {
			String name = (String)reg[0];
			Integer length = (Integer)reg[1];
			System.out.println("name=" + name + ", length=" + length);
		});


		System.out.println("====== consulta para obtener el ultimo registro de persona ======");
		Optional<Person> optionalPerson = repository.getLastRegistration();
		optionalPerson.ifPresent(System.out::println);

	}

	@Transactional(readOnly = true)
	public void wherein(){
		System.out.println("====== consulta where in ======");
		List<Person> persons = repository.getPersonsByIds(Arrays.asList(1L,2L,5L,7L));
		persons.forEach(System.out::println);


	}

	// @Transactional(readOnly = true)
	// public void findOne() {
	// 	Person person = null;
	// 	Optional<Person> optionalPerson = repository.findById(1L);
	// 	if (optionalPerson.isPresent()) {
	// 		person = optionalPerson.get();
	// 	}
	// 	System.out.println(person);

	// 	// o usamos una abreviacion
	// 	// repository.findById(1L).ifPresent(person -> System.out.println(person));

	// 	repository.findOne(6L).ifPresent(p -> System.out.println(p));

	// 	repository.obtenerPersonData("Lalo");
	// }

	// @Transactional(readOnly = true)
	// public void list() {
	// 	// List<Person> persons = (List<Person>)repository.findAll();
	// 	List<Person> persons = (List<Person>) repository.findByProgrammingLanguageAndName("Java", "Gerardo");
	// 	persons.stream().forEach(person -> {
	// 		System.out.println(person);
	// 	});

	// 	List<Object[]> personsValues = repository.obtenerPersonData("Python", "Pepe");
	// 	personsValues.stream().forEach(person -> {
	// 		System.out.println(person[0] + " es experto en " + person[1]);
	// 	});
	// }

	@Transactional
	public void create() {
		// Scanner scanner = new Scanner(System.in);
		// String name = scanner.next();
		// String lastname = scanner.next();
		// String programmingLanguage = scanner.next();
		// scanner.close();

		// Person person = new Person(null, name, lastname, programmingLanguage);

		// Person personNew = repository.save(person);
		// System.out.println(personNew);
	}

	@Transactional
	public void update() {
		// Scanner scanner = new Scanner(System.in);
		// System.out.println("Ingrese el id de la persona: ");
		// Long id = scanner.nextLong();

		// Optional<Person> optionalPerson = repository.findById(id);

		// if (optionalPerson.isPresent()) {
		// 	Person p = optionalPerson.orElseThrow();

		// 	System.out.println(p);
		// 	System.out.println("Ingrese el lenguage de programacion: ");
		// 	String programmingLanguage = scanner.next();
		// 	p.setProgrammingLanguage(programmingLanguage);
		// 	Person personDb = repository.save(p);
		// 	System.out.println(personDb);

		// } else {
		// 	System.out.println("El usuario no existe");
		// }

		// scanner.close();

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
		// Scanner scanner = new Scanner(System.in);
		// System.out.println("Ingrese el id a eliminar");
		// Long id = scanner.nextLong();

		// Optional<Person> optionalPerson = repository.findById(id);
		// optionalPerson.ifPresentOrElse(person -> repository.delete(person),() -> System.out.println("Lo sentimos no existe!"));
		// // buscar todos
		// repository.findAll().forEach(System.out::println);
		
		// scanner.close();
	}
}
