package org.ZCare.Services;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.ZCare.DTO.AccountResponse;
import org.ZCare.DTO.MedicalHistory;
public class MedicalHistoryService {
	public static String url = "jdbc:sqlserver://108.181.157.248\\mssql-138844-0.cloudclusters.net:18756;databaseName=ZCareDB;;encrypt=true;trustServerCertificate=true";
	public static String user = "mohamed";
	public static String pass = "01017883280Mm";
	public static String query = "";

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection(url, user, pass);
		return con;
	}
	//add Medical History
	public AccountResponse addMedicalHistory(MedicalHistory medicalHistory) {
		AccountResponse response =new AccountResponse();
		query = "insert into MedicalHistory(date,imagePath,genomePath,patient_id)values(?,?,?,?)";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setDate(1, (Date) medicalHistory.getDate());
			stmt.setString(2, medicalHistory.getImagePath());
			stmt.setString(3, medicalHistory.getGenomePath());
			stmt.setLong(4, medicalHistory.getPateint_Id());
			
			stmt.executeUpdate();
			response.setResult(1);
		} catch (Exception e) {
			System.out.println(e + "Failed");
			response.setResult(-1);
			return response;
		}
		return response;
	}

////////////////////////////

	
	//get Medical History By Patient ID
    Map<Long, MedicalHistory>map=new HashMap<Long, MedicalHistory>();
	public Collection<MedicalHistory> getPatientMedicalHistoryByPatientId(long id) {
		query = "select * from MedicalHistory where patient_id=?";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setLong(1, id);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				MedicalHistory medicalHistory = new MedicalHistory();
				medicalHistory.setId(res.getLong("id"));
				medicalHistory.setDate(res.getDate("date"));
				medicalHistory.setImagePath(res.getString("imagePath"));
				medicalHistory.setGenomePath(res.getString("genomePath"));
				medicalHistory.setCN(res.getFloat("CN"));
				medicalHistory.setAD(res.getFloat("AD"));
				medicalHistory.setMCI(res.getFloat("MCI"));
				medicalHistory.setPateint_Id(res.getLong("patient_id"));
				map.put(medicalHistory.getId(), medicalHistory);
			}

		} catch (Exception e) {
			System.out.println(e + "Failed");
		}
		return map.values();
	}
	//update Medical History By Patient ID
	public AccountResponse updateMedicalHistoryById(String imagePath,String genomePath,long id) {
		query = " UPDATE MedicalHistory SET imagePath=? , genomePath=? where id=?";
		AccountResponse response=new AccountResponse();
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, imagePath);
			stmt.setString(2,genomePath);
			stmt.setLong(3, id);
			stmt.executeUpdate();
			response.setResult(1);

		} catch (Exception e) {
			System.out.println(e + "Failed");
			response.setResult(-1);
		}
		return response;
	}
	
	//delete Patient Medical History
	public AccountResponse deletePatientMedicalHistory(long id) {
		AccountResponse response =new AccountResponse();
		query = "delete from MedicalHistory where id=?";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setLong(1, id);
			stmt.executeUpdate();
			response.setResult(1);
		} catch (Exception e) {
			System.out.println(e + "Failed");
			response.setResult(-1);
		}
		return response;
	}

}
