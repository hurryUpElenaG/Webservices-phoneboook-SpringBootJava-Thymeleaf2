package com.example.phonebook;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.phonebook.entity.Address;
import com.example.phonebook.entity.Contact;
import com.example.phonebook.repository.ContactRepository;



@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=update",
        "spring.flyway.enabled=false"
})



public class ContactRepositoryTest {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private ContactRepository contactRepo;

	
	
	@Test
	void whenSaved_thenFindByNameORPhoneNumber() {
		Address address = new Address();
		address.setId(1);
		address.setCity("Delchevo");
		address.setStreet("Bregalnica");
		address.setZip("2320");
		
		Contact contact = new Contact();
		contact.setId(1);
		contact.setName("Elena");
		contact.setSurname("Georgievska");
		contact.setPhoneNumber("0038975855200");
		contact.setAddress(address);
		contactRepo.save(contact);
		
		assertThat(contactRepo.search("Elena")).isNotNull();
		//assertThat(contactRepo.search("0038975855200")).isNotNull();
	}
	
	@Test
	void whenSaved_thenFindByPhoneNumber() {
		Address address = new Address();
		address.setId(1);
		address.setCity("Delchevo");
		address.setStreet("Bregalnica");
		address.setZip("2320");
		
		Contact contact = new Contact();
		contact.setId(1);
		contact.setName("Elena");
		contact.setSurname("Georgievska");
		contact.setPhoneNumber("0038975855200");
		contact.setAddress(address);
		contactRepo.save(contact);
		
		assertThat(contactRepo.searchPhone("00389")).isNotNull();

	}
	
	@Test
	void injectedComponentsAreNotNull() {
		assertThat(dataSource).isNotNull();
		assertThat(jdbcTemplate).isNotNull();
		assertThat(entityManager).isNotNull();
		assertThat(contactRepo).isNotNull();
	}
	

}

