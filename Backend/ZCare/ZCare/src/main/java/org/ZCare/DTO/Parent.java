package org.ZCare.DTO;


public class Parent {
	public int id,status,patientId;

	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Parent() {}
	public Parent(int id,int status,int patientId)
	{
		this.id=id;
		this.status=status;
		this.patientId=patientId;
	}
}
