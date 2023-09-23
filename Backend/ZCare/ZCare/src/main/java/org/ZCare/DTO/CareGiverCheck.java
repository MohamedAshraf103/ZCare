package org.ZCare.DTO;

public class CareGiverCheck {
	
	private String userName, email;
	private long patientId;
	
	public CareGiverCheck() {}
	
	public CareGiverCheck(String userName, String email, long patientId) {
		super();
		this.userName = userName;
		this.email = email;
		this.patientId = patientId;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

}
