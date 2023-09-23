package org.ZCare.DTO;

public class PatientContact {
	int id;
	String title;
	String name;
	String phone;
	String image;
	int patientId;
	public PatientContact() {}
	
	public PatientContact(int id, String title, String name, String phone,String image, int patientId) {
		super();
		this.id = id;
		this.title = title;
		this.name = name;
		this.phone = phone;
		this.image=image;
		this.patientId = patientId;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
	

}
