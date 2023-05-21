package org.ZCare.DTO;

public class PatientMedicine {
	// Attributes

	private long id;
	private String name, imagePath;
	private long patientId;

	// constructor

	public PatientMedicine() {
	}

	public PatientMedicine(long id, String name, String imagePath, long patientId) {
		super();
		this.id = id;
		this.name = name;
		this.imagePath = imagePath;
		this.patientId = patientId;
	}

	// functions

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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

}
