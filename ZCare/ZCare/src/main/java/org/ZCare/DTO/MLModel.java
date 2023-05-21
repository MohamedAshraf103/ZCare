package org.ZCare.DTO;

public class MLModel {
	private long id;
	private String type;
	private String folderPath;
	
	public MLModel() {}
	
	public MLModel(long id, String type, String folderPath) {
		super();
		this.id = id;
		this.type = type;
		this.folderPath = folderPath;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFolderPath() {
		return folderPath;
	}

	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}

	
	

}
