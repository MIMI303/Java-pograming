package com.iktpreobuka.restexamples.entities;

import java.time.LocalDate;

public class BankClientBean {
	
	protected Integer id;
	protected String name;
	protected String surname;
	protected String email;
	protected LocalDate age;
	protected String bonitet;
	protected String grad;
	
	public BankClientBean() {
		super();
	}
	
	public BankClientBean(Integer id, String name, String surname, String email, LocalDate age, String bonitet, String grad) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.age = age;
		this.bonitet = bonitet;
		this.grad = grad;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getLocalDate() {
		return age;
	}
	public void setLocalDate (LocalDate age) {
		this.age = age;
	}
	public String getBonitet() {
		return bonitet;
	}
	public void setBonitet (String bonitet) {
		this.bonitet = bonitet;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

}
