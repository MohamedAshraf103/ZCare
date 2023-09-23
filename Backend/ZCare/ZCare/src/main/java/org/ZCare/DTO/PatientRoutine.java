package org.ZCare.DTO;

public class PatientRoutine extends Parent {
	
	String name;
	public PatientRoutine() {}
	public PatientRoutine(int id,int status,int patientId,String name)
	{
		super();
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
