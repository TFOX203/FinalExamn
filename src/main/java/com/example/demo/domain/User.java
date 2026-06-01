package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/*
 *
 * @author Valenciano
 * 8 abr 2026
 */

//Annotation @Entity associates a class with a table in the database
@Entity
//@Data annotation indicates Lombok library that generates all the getters and setters for this class
@Data
@Table(name = "Users")
@AllArgsConstructor
public class User {
	
	//fields
	@Column
	private String name;

	private String password;
	
	//The money that the user has in his account
	private float balance;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private long dni;
	
	private String email;
	
	private int age;
	
	private long tlf;
	
	private String adress;
	
	
	//Constructors
	public User(long id) {
		this.id = id;
	}

	public User() {
	}
	
}
