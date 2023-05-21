package org.ZCare.DTO;

public class Doctor extends Person {
	private String description,rate,email;
	
	public Doctor() {}
	public Doctor(long id, String userName, String password, String firstName,
			String lastName, boolean gender,String description,String rate,String email) {
		super();
		this.description=description;
		this.rate=rate;
		this.email=email;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	

}
