package com.gerardo.curso.springboot.jpa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gerardo.curso.springboot.jpa.models.Client;
import com.gerardo.curso.springboot.jpa.models.Invoice;
import com.gerardo.curso.springboot.jpa.repositories.ClientRepository;
import com.gerardo.curso.springboot.jpa.repositories.InvoiceRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner{

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		manyToOneFindByIdClient();
	}

	public void manyToOne(){

		Client client = new Client("John", "Doe");
		clientRepository.save(client);

		Invoice invoice = new Invoice("Compras de oficina", 2000L);
		invoice.setClient(client);
		Invoice invoiceDb = invoiceRepository.save(invoice);
		System.out.println(invoiceDb);

	}

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

}
