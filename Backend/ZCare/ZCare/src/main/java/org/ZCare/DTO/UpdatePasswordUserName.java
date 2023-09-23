package org.ZCare.DTO;

public class UpdatePasswordUserName {
	String password;
	String email;
	String userName;
	String newPassword;
	long id;
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public UpdatePasswordUserName() {}
	public UpdatePasswordUserName(String password,String email,String userName)
	{
		this.password=password;
		this.userName=userName;
		this.email=email;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
