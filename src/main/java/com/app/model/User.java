package com.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="userinfo")
public class User {

    @Id
	private int id;
	private String username;
	private String firstname;
	private String lastname;
	private String password;
	private int age;
	
  public User() {
		
	}
	
	public User(String username, String firstname, String lastname, String password, int age) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.age = age;
	}
	
	

}
