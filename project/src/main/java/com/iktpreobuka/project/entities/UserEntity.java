package com.iktpreobuka.project.entities;

import com.iktpreobuka.project.controllers.Roles;

public class UserEntity {
	protected Integer id;
	protected String firstName;
	protected String lastName;
	protected String email;
    protected String userName;
	protected String password;
	protected Roles eUserRole;
    
	public UserEntity() {
		super();
	}

	public UserEntity(Integer id, String firstName, String lastName, String email, String userName, String password, Roles eUserRole) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.eUserRole = eUserRole;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Roles getEUserRole() {
		return eUserRole;
	}

	public void setEUserRole(Roles eUserRole) {
		this.eUserRole = eUserRole;
	}
	
	
    
    
    
}
