package org.ZCare.DTO;
import java.sql.Date;
public class MedicalHistory {
	private long id;
	private Date date;
	private String imagePath,genomePath;
	private Float AD,CN,MCI;
	private long pateint_Id;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public MedicalHistory() {}
	public MedicalHistory(long id, Date date,String imagePath, String genomePath,long pateint_Id) {
		super();
		this.id = id;
		this.date=date;
		this.imagePath = imagePath;
		this.genomePath = genomePath;
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
	public Float getAD() {
		return AD;
	}
	public void setAD(Float aD) {
		AD = aD;
	}
	public Float getCN() {
		return CN;
	}
	public void setCN(Float cN) {
		CN = cN;
	}
	public Float getMCI() {
		return MCI;
	}
	public void setMCI(Float mCI) {
		MCI = mCI;
	}
	
	

}
