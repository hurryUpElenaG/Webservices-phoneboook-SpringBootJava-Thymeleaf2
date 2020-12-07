package com.example.phonebook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.phonebook.entity.Contact;
import com.example.phonebook.repository.ContactRepository;

@Service
public class ContactService {
	
	@Autowired
	ContactRepository contactRepo;
	
	
	// Get all contacts by phone number or person user name 
    	public List<Contact> listAll(String keyword) {
			if (keyword != null) {
				return contactRepo.search(keyword);
			}
			return contactRepo.findAll();
			
		}
    	
    	// Get all contacts by phone number 
    	public List<Contact> listAllPhones(String keyword) {
			if (keyword != null) {
				return contactRepo.searchPhone(keyword);
			}
			return contactRepo.findAll();
		}
		
		
		// paging and sorting by surname
		public Page<Contact> findPaginated1(int pageNo, int pageSize, String sortField, String sortDirection) {
			Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
					: Sort.by(sortField).descending();

			Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
			return this.contactRepo.findAll(pageable);
		}
		
		
		// paging and sorting for filter by number
		public Page<Contact> findPaginated2(int pageNo, int pageSize, String sortField, String sortDirection) {
			Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
					: Sort.by(sortField).descending();

			Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
			return this.contactRepo.findAll(pageable);
		}
		
		
}
