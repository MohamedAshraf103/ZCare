package org.ZCare.DTO;

import java.sql.Date;

public class PatientPlan extends Parent {
	Date date;
	
	public PatientPlan() {}
	public PatientPlan(int id,int status,int patientId,Date date)
	{
		super();
		this.date=date;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
