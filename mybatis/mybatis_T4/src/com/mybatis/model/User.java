package com.mybatis.model;

public class User {
	int id;
	String name;
	String password;
	String email;
	String birthday;
	
	public User(String name, String password, String email,
			String birthday) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.birthday = birthday;
	}
	
	
	
	public User() {
		super();
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	
}
