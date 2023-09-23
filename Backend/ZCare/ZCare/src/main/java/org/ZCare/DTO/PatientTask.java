package org.ZCare.DTO;
import java.sql.Date;
import java.sql.Time;
public class PatientTask {
	private long id;
	private String title;
	private String description;
	private int type;
	private boolean status;
	private boolean viberation;
	private float duration;
	private Date date,currentDate;
	private Time time;
	private int routineId;
	private int planId;
	private boolean isPatient;
	private boolean isCareGiver;
	private boolean patientDeleted,careGiverDeleted;

	
	
	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public boolean isPatientDeleted() {
		return patientDeleted;
	}

	public void setPatientDeleted(boolean patientDeleted) {
		this.patientDeleted = patientDeleted;
	}

	public boolean isCareGiverDeleted() {
		return careGiverDeleted;
	}

	public void setCareGiverDeleted(boolean careGiverDeleted) {
		this.careGiverDeleted = careGiverDeleted;
	}

	public PatientTask() {}
	
	public PatientTask(long id, String title, String description, int type, boolean status, boolean viberation,
			float duration, Date date,Time time,int routineId,int planId) {
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
		this.routineId=routineId;
		this.planId=planId;
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

	public int getRoutineId() {
		return routineId;
	}

	public void setRoutineId(int routineId) {
		this.routineId = routineId;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public boolean isPatient() {
		return isPatient;
	}

	public void setPatient(boolean isPatient) {
		this.isPatient = isPatient;
	}

	public boolean isCareGiver() {
		return isCareGiver;
	}

	public void setCareGiver(boolean isCareGiver) {
		this.isCareGiver = isCareGiver;
	}


}
