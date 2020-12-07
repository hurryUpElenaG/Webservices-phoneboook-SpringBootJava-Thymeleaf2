package com.example.phonebook.entity.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.phonebook.entity.Address;
import com.example.phonebook.entity.Contact;
import com.example.phonebook.repository.ContactRepository;
import com.example.phonebook.services.ContactService;



@Controller
public class ContactController {
	
	
	@Autowired
	ContactRepository contactRepo;
	
	@Autowired
	ContactService contactService;
	
	
	
	
	@GetMapping("/")
	public String viewContactHome(Model model, @Param("keyword") String keyword) {
		
		
		//List<Contact> listContacts = contactRepo.findAll();
		List<Contact> listContacts = contactService.listAll(keyword);
		model.addAttribute("listContacts", listContacts);
		model.addAttribute("keyword", keyword);
		
		return "index";
	
	}
	
	@GetMapping("/bySurname")
	public String viewBySurname(Model model) {
		
		List<Contact> listContacts = contactRepo.findAll();
		model.addAttribute("listContacts", listContacts);
		
		//return "orderedBySurname";
		
		return findPaginated1(1, "surname", "asc", model);
	
	}
	
	@GetMapping("/filteredByPhone")
	public String viewFilterByPhone(Model model, @Param("keyword") String keyword) {
		
		// findPaginated2(1, "surname", "asc", model);
		
		
	    List<Contact> listContacts = contactService.listAllPhones(keyword);
		model.addAttribute("listContacts", listContacts);
		model.addAttribute("keyword", keyword);
			
	   return "filterByPhoneNumber";
	
	
	}
	
		
	// create contact
	@PostMapping("/createContact")
	public String createContact(@ModelAttribute("contact")Contact contact) {
		 
		contactRepo.save(contact);

		//return "redirect:/";
		return "contactDetails";
		
	}
	
	
	// new contact form
	@GetMapping("/showNewContactForm")                   
	public String showNewContactForm(Model model) {
		Contact contact = new Contact();
		model.addAttribute("contact", contact);           
		
		return "new_contact";
	}
	
	
	//show form for update contact
	@GetMapping("/showUpdateContactForm/{id}")
	public String showUpdateContactForm(@PathVariable( value = "id")Integer id, Model model) {
		Contact contact = contactRepo.findById(id).get();
		
		model.addAttribute("contact", contact);
		
		return "update_contact";
	}
	
	
	//  update contact by id
		@PutMapping("/update/{contactId}")
		public Contact update(@PathVariable("contactId") Integer contactId, @RequestBody Contact contactNew) {

			Contact contact = contactRepo.findById(contactId).get();

			contact.setName(contactNew.getName());
			contact.setSurname(contactNew.getSurname());
			contact.setAddress(contactNew.getAddress());
			contact.setPhoneNumber(contactNew.getPhoneNumber());
		
	        return contactRepo.save(contact);

		}
	
	
	// delete customer
	@GetMapping("/deleteContact/{id}")
	public String deleteContact(@PathVariable("id")Integer id) {
		contactRepo.deleteById(id);
		return "redirect:/";
	}
	
		
		
		//paging and sorting  for order by surname
				@GetMapping("/page/{pageNo}")
				public String findPaginated1(@PathVariable (value = "pageNo") Integer pageNo, 
						@RequestParam("sortField") String sortField,
						@RequestParam("sortDir") String sortDir,
						Model model) {
					Integer pageSize = 5;
					
					Page<Contact> page = contactService.findPaginated1(pageNo, pageSize, sortField, sortDir);
					List<Contact> listContacts = page.getContent();
					
					model.addAttribute("currentPage", pageNo);
					model.addAttribute("totalPages", page.getTotalPages());
					model.addAttribute("totalItems", page.getTotalElements());
					
					model.addAttribute("sortField", sortField);
					model.addAttribute("sortDir", sortDir);
					model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
					
					model.addAttribute("listContacts", listContacts);
					
					
					return "orderedBySurname";
				}
				
				
				//paging and sorting  for filter by phone number
				@GetMapping("/page/{pageNo}/filterbyPhoneNumber")
				public String findPaginated2(@PathVariable (value = "pageNo") Integer pageNo, 
						@RequestParam("sortField") String sortField,
						@RequestParam("sortDir") String sortDir,
						Model model) {
					Integer pageSize = 5;
					
					Page<Contact> page = contactService.findPaginated2(pageNo, pageSize, sortField, sortDir);
					List<Contact> listContacts = page.getContent();
					
					model.addAttribute("currentPage", pageNo);
					model.addAttribute("totalPages", page.getTotalPages());
					model.addAttribute("totalItems", page.getTotalElements());
					
					model.addAttribute("sortField", sortField);
					model.addAttribute("sortDir", sortDir);
					model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
					
					model.addAttribute("listContacts", listContacts);
					
					
					return "filterByPhoneNumber";
				}
	
	
	

}
