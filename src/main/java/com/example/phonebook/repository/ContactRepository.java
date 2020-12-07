package com.example.phonebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.example.phonebook.entity.Contact;



@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer>{
	
	// get all phone numbers or names
	//@Query("SELECT c FROM Contact c WHERE c.phoneNumber LIKE %?1%")
	@Query("SELECT c FROM Contact c WHERE CONCAT(c.name, ' ', c.phoneNumber, ' ') LIKE %?1%" )
	public List<Contact> search(String keyword);
	
	
	//get all phone numbers
	 @Query("SELECT c FROM Contact c WHERE c.phoneNumber LIKE %?1%")
     public List<Contact> searchPhone(String keyword1);
	
	

}
