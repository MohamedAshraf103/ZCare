package org.ZCare.DTO;

public class Admin {
	private long id;
	private String userName,password,name;
	public Admin() {}
	public Admin(long id, String userName, String password, String name) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.name = name;
	}
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
	
}
