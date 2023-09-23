package org.ZCare.DTO;

public class LoginResponse {
	
	private long id,patientId,result;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	public long getResult() {
		return result;
	}
	public void setResult(long result) {
		this.result = result;
	}
	public LoginResponse() {}
	public LoginResponse(long id,long patientId,long result)
	{
		this.id=id;
		this.patientId=patientId;
		this.result=result;
	}

}
