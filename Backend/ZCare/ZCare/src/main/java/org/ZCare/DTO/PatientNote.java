package org.ZCare.DTO;

public class PatientNote {
	private int id;
	private String title;
	private String content;
	private int patientId;
	public PatientNote() {
		
	}
	public PatientNote(int id, String title, String content, int patientId) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	

}
