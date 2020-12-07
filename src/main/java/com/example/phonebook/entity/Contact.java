package com.example.phonebook.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//Person: (Id, name, surname, address, phone number)

@Entity
@Table(name = "contact")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;
	private String surname;

	@ManyToOne (fetch=FetchType.EAGER, cascade = CascadeType.ALL )            
	@JoinColumn(referencedColumnName = "id")
	private Address address;
	
	private String phoneNumber;

}