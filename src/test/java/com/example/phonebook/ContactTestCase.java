package com.example.phonebook;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.phonebook.entity.Contact;
import com.example.phonebook.repository.ContactRepository;


@ExtendWith(MockitoExtension.class)
public class ContactTestCase {
	
	@Mock
	private ContactRepository contactRepo;
	
	
	@Test
	void nameIsCreatedWhenContactIsSaved() {
		
		
		Contact contact = new Contact();
		contact.setId(101);
		contact.setName("Jontravolta");
		contact.setSurname("Misterbin");
		contact.setPhoneNumber("0038975855200");
		
		
		contactRepo.save(contact);
		assertThat(contact.getName()).isNotNull();
	}

}
