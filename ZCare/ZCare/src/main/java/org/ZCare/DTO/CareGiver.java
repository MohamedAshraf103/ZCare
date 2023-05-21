package org.ZCare.DTO;


public class CareGiver extends Person {
	private String address;
	private String email;
	private String phone;
	private long patientId;

	public CareGiver() {
	}

	public CareGiver(long id, String userName, String password, String firstName, String lastName, boolean gender,
			String address,String email,String phone,long patientId) {
		super();
		this.address = address;
		this.email=email;
		this.phone=phone;
		this.patientId=patientId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
