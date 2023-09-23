package org.ZCare.DTO;

import java.sql.Date;

public class CareGiver extends Person {
	private long patientId;

	public CareGiver() {
	}
	public CareGiver(long id, String userName, String password, String name,boolean gender,
			String address, String phone, Date dateOfBirth, String email,long patientId) {
		super();
		this.patientId=patientId;
	}
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

}
