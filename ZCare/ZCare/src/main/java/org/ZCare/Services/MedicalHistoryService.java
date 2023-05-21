package org.ZCare.Services;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.ZCare.DTO.MedicalHistory;
public class MedicalHistoryService {
	public static String url = "jdbc:sqlserver://localhost\\DESKTOP-1KMPQD6:1433;databaseName=ZCare;;encrypt=true;trustServerCertificate=true";
	public static String user = "sa";
	public static String pass = "sa";
	public static String query = "";

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection(url, user, pass);
		return con;
	}
	//add Medical History
	public int addMedicalHistory(MedicalHistory medicalHistory) {
		int status = 0;
		query = "insert into MedicalHistory(date,imagePath,genomePath,patient_id)values(?,?,?,?)";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setDate(1, (Date) medicalHistory.getDate());
			stmt.setString(2, medicalHistory.getImagePath());
			stmt.setString(3, medicalHistory.getGenomePath());
			stmt.setLong(4, medicalHistory.getPateint_Id());
			status = stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e + "Failed");
			status=0;
			return status;
		}
		return status;
	}

////////////////////////////

	
	//get Medical History By Patient ID
    Map<Long, MedicalHistory>map=new HashMap<Long, MedicalHistory>();
	public Collection<MedicalHistory> getPatientMedicalHistoryByPatientId(long id) {
		MedicalHistory medicalHistory = new MedicalHistory();
		query = "select * from MedicalHistory where patient_id=?";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setLong(1, id);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				medicalHistory.setId(res.getLong("id"));
				medicalHistory.setDate(res.getDate("date"));
				medicalHistory.setImagePath(res.getString("imagePath"));
				medicalHistory.setGenomePath(res.getString("genomePath"));
				medicalHistory.setValid(res.getBoolean("isValid"));
				medicalHistory.setCategory(res.getString("class"));
				medicalHistory.setPateint_Id(res.getLong("patient_id"));
				map.put(medicalHistory.getId(), medicalHistory);
			}

		} catch (Exception e) {
			System.out.println(e + "Failed");
		}
		return map.values();
	}
	//update Medical History By Patient ID
	public int updateMedicalHistoryById(String imagePath,String genomePath,long id) {
		query = " UPDATE MedicalHistory SET imagePath=? , genomePath=? where id=?";
		int status = 0;
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, imagePath);
			stmt.setString(2,genomePath);
			stmt.setLong(3, id);
			status = stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e + "Failed");
			return status=0;
		}
		return status;
	}
	
	//delete Patient Medical History
	public int deletePatientMedicalHistory(long id) {
		int status = 0;
		query = "delete from MedicalHistory where id=?";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setLong(1, id);
			status = stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e + "Failed");
			status=0;
			return status;
		}
		return status;
	}

}
