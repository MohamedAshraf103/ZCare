package org.ZCare.DTO;

import java.sql.Date;

public class Person {
	
	//Attributes
	private long id;
	private String userName,password,name;
	private boolean gender;
	private String address;
	private String phone;
	private Date dateOfBirth;
	private String email;	
	private String image;
	//Constructor
	public Person() {}
	public Person(long id, String userName, String password, String name, boolean gender,String address
			,String phone,Date dateOfBirth,String email,String image) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.address=address;
		this.phone=phone;
		this.dateOfBirth=dateOfBirth;
		this.email=email;
		this.image=image;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Person(String userName, String password, String name,boolean gender) {
		super();
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.gender = gender;
	}
	//Functions
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

}
