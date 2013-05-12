package io.core9.collections.models.impl;




import org.hibernate.search.annotations.Field;

public class AccountUser {


	public AccountUser() {
	}
	
	public AccountUser(String i, String name, String surname) {
		this.id = i;
		this.name = name;
		this.surname = surname;
	}
	
	@Field
	String id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurName() {
		return surname;
	}
	public void setSurName(String surname) {
		this.surname = surname;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Field
	String name;
	@Field
	String surname;
	// hashCode() and equals() omitted
	public String getId() {
		return id;
	}

}