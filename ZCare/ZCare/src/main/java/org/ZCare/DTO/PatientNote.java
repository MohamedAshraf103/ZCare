package org.ZCare.DTO;
import java.sql.Date;
import java.sql.Time;
public class PatientNote {
	private long id;
	private String title;
	private String description;
	private int type;
	private boolean status;
	private boolean viberation;
	private float duration;
	private Date date;
	private Time time;
	private long patientId;
	
	public PatientNote() {}
	
	public PatientNote(long id, String title, String description, int type, boolean status, boolean viberation,
			float duration, Date date,Time time,long patientId) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.type = type;
		this.status = status;
		this.viberation = viberation;
		this.duration = duration;
		this.date=date;
		this.time = time;
		this.patientId = patientId;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public boolean isViberation() {
		return viberation;
	}
	public void setViberation(boolean viberation) {
		this.viberation = viberation;
	}
	public float getDuration() {
		return duration;
	}
	public void setDuration(float duration) {
		this.duration = duration;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	

}
