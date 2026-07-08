package com.gerardo.curso.springboot.jpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.gerardo.curso.springboot.jpa.models.Address;
import com.gerardo.curso.springboot.jpa.models.Client;
import com.gerardo.curso.springboot.jpa.models.ClientDetail;
import com.gerardo.curso.springboot.jpa.models.Course;
import com.gerardo.curso.springboot.jpa.models.Invoice;
import com.gerardo.curso.springboot.jpa.models.Student;
import com.gerardo.curso.springboot.jpa.repositories.ClientDetailRepository;
import com.gerardo.curso.springboot.jpa.repositories.ClientRepository;
import com.gerardo.curso.springboot.jpa.repositories.CourseRepository;
import com.gerardo.curso.springboot.jpa.repositories.InvoiceRepository;
import com.gerardo.curso.springboot.jpa.repositories.StudentRepository;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner{

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private ClientDetailRepository clientDetailRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		manyToManyRemove();
	}

	@Transactional
	public void manyToOne(){

		Client client = new Client("John", "Doe");
		clientRepository.save(client);

		Invoice invoice = new Invoice("Compras de oficina", 2000L);
		invoice.setClient(client);
		Invoice invoiceDb = invoiceRepository.save(invoice);
		System.out.println(invoiceDb);

	}

	@Transactional
	public void manyToOneFindByIdClient(){

		Optional<Client> optionalClient = clientRepository.findById(2L);

		if (optionalClient.isPresent()) {
			Client client = optionalClient.orElseThrow();
			Invoice invoice = new Invoice("Compras de oficina", 2000L);
			invoice.setClient(client);
			Invoice invoiceDb = invoiceRepository.save(invoice);
			System.out.println(invoiceDb);
		}
	}

	@Transactional
	public void oneToMany(){
		Client client = new Client("Fran","Moras");

		Address address1 = new Address("El Vergel", 12345);
		Address address2 = new Address("Vasco de Gama", 9875);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);

		System.out.println(client);
	}

	@Transactional
	public void oneToManyFindById(){
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Address address1 = new Address("El Vergel", 12345);
			Address address2 = new Address("Vasco de Gama", 9875);

			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);

			clientRepository.save(client);

			System.out.println(client);
		});

	}

	@Transactional
	public void removeAddress(){
		Client client = new Client("Fran","Moras");

		Address address1 = new Address("El Vergel", 12345);
		Address address2 = new Address("Vasco de Gama", 9875);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);

		System.out.println(client);

		Optional<Client> optionalClient = clientRepository.findById(3L);
		optionalClient.ifPresent(c -> {
			c.getAddresses().remove(address1);
			clientRepository.save(c);
			System.out.println(c);
		});
	}

	@Transactional
	public void removeAddressFindById(){
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Address address1 = new Address("El Vergel", 12345);
			Address address2 = new Address("Vasco de Gama", 9875);

			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);

			clientRepository.save(client);

			System.out.println(client);

			Optional<Client> optionalClient2 = clientRepository.findOne(2L);
			optionalClient2.ifPresent(c -> {
				c.getAddresses().remove(address2);
				clientRepository.save(c);
				System.out.println(c);
			});
		});

	}


	@Transactional
	public void oneToManyInvoiceBidireccional(){
		Client client = new Client("Fran","Moras");

		Invoice invoice1 = new Invoice("Compras de la casa",5000L);
		Invoice invoice2 = new Invoice("Compras de oficina",8000L);

		client.addInvoice(invoice1);
		client.addInvoice(invoice2);

		clientRepository.save(client);
		System.out.println(client);
	}

	@Transactional
	public void oneToManyInvoiceBidireccionalFindById(){

		Optional<Client> optionalClient = clientRepository.findOne(1L);

		optionalClient.ifPresent(client -> {

			Invoice invoice1 = new Invoice("Compras de la casa",5000L);
			Invoice invoice2 = new Invoice("Compras de oficina",8000L);

			client.addInvoice(invoice1).addInvoice(invoice2);

			clientRepository.save(client);
			System.out.println(client);
		});

		
	}

	@Transactional
	public void removeInvoiceBidireccionalFindById(){

		Optional<Client> optionalClient = clientRepository.findOne(1L);

		optionalClient.ifPresent(client -> {

			Invoice invoice1 = new Invoice("Compras de la casa",5000L);
			Invoice invoice2 = new Invoice("Compras de oficina",8000L);

			client.addInvoice(invoice1).addInvoice(invoice2);

			clientRepository.save(client);
			System.out.println(client);
		});

		Optional<Client> optionalClientBd = clientRepository.findOne(1L);
		optionalClientBd.ifPresent(client -> {
			Invoice invoice3 = new Invoice("compras de la casa", 5000L);
			invoice3.setId(1L);

			Optional<Invoice> invoiceOptional = Optional.of(invoice3); // invoiceRepository.findById(2L);
			invoiceOptional.ifPresent(invoice -> {
				client.removeInvoice(invoice);

				clientRepository.save(client);
				System.out.println(client);
			});
		});
		
	}

	@Transactional
	public void removeInvoiceBidireccional(){

		Client client = new Client("Fran","Moras");

		Invoice invoice1 = new Invoice("Compras de la casa",5000L);
		Invoice invoice2 = new Invoice("Compras de oficina",8000L);

		client.addInvoice(invoice1).addInvoice(invoice2);

		clientRepository.save(client);
		System.out.println(client);
		
		Optional<Client> optionalClient = clientRepository.findOne(1L);

		optionalClient.ifPresent(clientDb -> {
			Invoice invoice3 = new Invoice("compras de la casa",5000L);
			invoice3.setId(1L);

			Optional<Invoice> invoiceOptional = Optional.of(invoice3); // invoiceRepository.findById(2L);
			invoiceOptional.ifPresent(invoice -> {
				client.removeInvoice(invoice);

				clientRepository.save(clientDb);
				System.out.println(clientDb);
			});

		});
		
	}

	@Transactional
	public void OneToOne(){
		ClientDetail clientDetails = new ClientDetail(true, 5000);
		clientDetailRepository.save(clientDetails);

		Client client = new Client("Fran","Moras");
		client.setClientDetail(clientDetails);
		clientRepository.save(client);
		System.out.println(client);
		
	}

		@Transactional
		public void OneToOneFindById(){
		ClientDetail clientDetails = new ClientDetail(true, 5000);
		clientDetailRepository.save(clientDetails);

		Optional<Client> clientOptional = clientRepository.findOne(2L);
		clientOptional.ifPresent(client -> {
			client.setClientDetail(clientDetails);
			clientRepository.save(client);
			System.out.println(client);
		});

		
		
	}

	@Transactional
	public void OneToOneBidireccional(){
		Client client = new Client("Erba","Pura");
		
		ClientDetail clientDetails = new ClientDetail(true, 5000);
		
		client.setClientDetail(clientDetails);
		
		clientRepository.save(client);
		
		System.out.println(client);
		
	}

	@Transactional
	public void OneToOneBidireccionalFindById(){
		Optional<Client> clientOptional = clientRepository.findOne(2L);

		clientOptional.ifPresent(client -> {
			ClientDetail clientDetails = new ClientDetail(true, 5000);
		
			client.setClientDetail(clientDetails);
		
			clientRepository.save(client);
		
			System.out.println(client);
		});
		
		
		
	}
	// EJEMPLO MANY TO MANY Unidireccional

	@Transactional
	public void manyTomany(){
		Student student1 = new Student("Jano","Pura");
		Student student2 = new Student("Erba","Doe");

		Course course1 = new Course("Curso de java master", "Andres");
		Course course2 = new Course("Curso de Spring", "Andres");

		// relacion
		student1.setCourses(Set.of(course1,course2)); // esto es como el Arrays.aslist pero del Set
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1,student2));

		System.out.println(student1);
		System.out.println(student2);


	}

	@Transactional
	public void manyTomanyFind(){
		Optional<Student> studentOptional1 = studentRepository.findById(1L);
		Optional<Student> studentOptional2 = studentRepository.findById(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Course course1 = courseRepository.findById(1L).get();
		Course course2 = courseRepository.findById(2L).get();

		// relacion
		student1.setCourses(Set.of(course1,course2)); // esto es como el Arrays.aslist pero del Set
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1,student2));

		System.out.println(student1);
		System.out.println(student2);


	}

	@Transactional
	public void manyTomanyRemoveFind(){
		Optional<Student> studentOptional1 = studentRepository.findById(1L);
		Optional<Student> studentOptional2 = studentRepository.findById(2L);

		Student student1 = studentOptional1.get();
		Student student2 = studentOptional2.get();

		Course course1 = courseRepository.findById(1L).get();
		Course course2 = courseRepository.findById(2L).get();

		// relacion
		student1.setCourses(Set.of(course1,course2)); // esto es como el Arrays.aslist pero del Set
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1,student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(1L);
		if (studentOptionalDb.isPresent()) {
			
			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findById(2L);

			if (courseOptionalDb.isPresent()) {
				Course courseDb = courseOptionalDb.get();
				studentDb.getCourses().remove(courseDb);

				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}
		}


	}

	@Transactional
	public void manyToManyRemove(){
		Student student1 = new Student("Jano","Pura");
		Student student2 = new Student("Erba","Doe");

		Course course1 = new Course("Curso de java master", "Andres");
		Course course2 = new Course("Curso de Spring", "Andres");

		// relacion
		student1.setCourses(Set.of(course1,course2)); // esto es como el Arrays.aslist pero del Set
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1,student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studentOptionalDb = studentRepository.findOneWithCourses(3L);
		if (studentOptionalDb.isPresent()) {
			
			Student studentDb = studentOptionalDb.get();
			Optional<Course> courseOptionalDb = courseRepository.findById(3L);

			if (courseOptionalDb.isPresent()) {
				Course courseDb = courseOptionalDb.get();
				studentDb.getCourses().remove(courseDb);

				studentRepository.save(studentDb);
				System.out.println(studentDb);
			}
		}


	}


}
