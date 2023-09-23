package org.ZCare.DTO;

import java.sql.Date;

public class Patient extends Person {
	private long doctorId;

	public Patient() {
	}

	public Patient(long id, String userName, String password, String name,boolean gender,
			String address, String phone, Date dateOfBirth, String email, long doctorId) {
		super();
		this.doctorId = doctorId;
	}

	

	public long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}

	
}
