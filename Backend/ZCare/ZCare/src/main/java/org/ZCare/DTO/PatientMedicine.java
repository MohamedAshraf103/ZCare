package org.ZCare.DTO;

public class PatientMedicine {
	// Attributes

	private long id;
	private String name, description,image;
	private long patientId;

	// constructor

	public PatientMedicine() {
	}

	public PatientMedicine(long id, String name, String description, String image, long patientId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.patientId = patientId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}



}
