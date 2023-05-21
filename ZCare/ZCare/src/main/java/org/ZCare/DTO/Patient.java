package org.ZCare.DTO;

import java.sql.Date;

public class Patient extends Person {
	private String address;
	private String phone;
	private Date dateOfBirth;
	private String email;
	private long doctorId;

	public Patient() {
	}

	public Patient(long id, String userName, String password, String firstName, String lastName, boolean gender,
			String address, String phone, Date dateOfBirth, String email, long doctorId) {
		super();
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.doctorId = doctorId;
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
