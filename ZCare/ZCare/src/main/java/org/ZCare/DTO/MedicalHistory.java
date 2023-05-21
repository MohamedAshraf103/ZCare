package org.ZCare.DTO;
import java.sql.Date;
public class MedicalHistory {
	private long id;
	private Date date;
	private String category,imagePath,genomePath;
	private boolean isValid;
	private long pateint_Id;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public MedicalHistory() {}
	public MedicalHistory(long id, Date date,String category, String imagePath, String genomePath, boolean isValid,long pateint_Id) {
		super();
		this.id = id;
		this.date=date;
		this.category = category;
		this.imagePath = imagePath;
		this.genomePath = genomePath;
		this.isValid = isValid;
		this.pateint_Id=pateint_Id;
	}
	public long getPateint_Id() {
		return pateint_Id;
	}
	public void setPateint_Id(long pateint_Id) {
		this.pateint_Id = pateint_Id;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getGenomePath() {
		return genomePath;
	}
	public void setGenomePath(String genomePath) {
		this.genomePath = genomePath;
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	

}
