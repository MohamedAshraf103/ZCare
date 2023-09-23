package org.ZCare.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.ZCare.DTO.AccountResponse;
import org.ZCare.DTO.PatientNote;

public class PatientNoteService {
	public static String url = "jdbc:sqlserver://108.181.157.248\\mssql-138844-0.cloudclusters.net:18756;databaseName=ZCareDB;;encrypt=true;trustServerCertificate=true";
	public static String user = "mohamed";
	public static String pass = "01017883280Mm";
	public static String query = "";

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection(url, user, pass);
		return con;
	}}
	// add New Patient Note
	public AccountResponse addPatientNote(PatientNote patientNote) {
		query = "insert into PatientNote values(?,?,?)";
		AccountResponse response=new AccountResponse();
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, patientNote.getTitle());
			stmt.setString(2, patientNote.getContent());
			stmt.setInt(3, patientNote.getPatientId());
			stmt.executeUpdate();
			response.setResult(1);
		} catch (Exception e) {
			System.out.println(e + "Failed");
			response.setResult(-1);
		}
		return response;
	}

	
		//get Patient Medicine for Specific Patient By Id
    Map<Long, PatientNote>map=new HashMap<Long, PatientNote>();
	public Collection<PatientNote> getPatientNoteById(long id) {
		query = "select * from PatientNote where patient_id=?";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setLong(1, id);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				PatientNote patientNote = new PatientNote();
				patientNote.setId(res.getInt("id"));
				patientNote.setTitle(res.getString("title"));
				patientNote.setContent(res.getString("content"));
				patientNote.setPatientId(res.getInt("patient_id"));
				map.put((long) patientNote.getId(), patientNote);
			}

		} catch (Exception e) {
			System.out.println(e + "Failed");
			return null;
		}
		return map.values();
	}
		//Delete Patient Medicine
	public AccountResponse deletePatientNote(int id) {
		query = "delete from PatientNote where id=?";
		AccountResponse response=new AccountResponse();
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
	
	public AccountResponse updatePatientNote(PatientNote patientNote) {
		query = "update PatientNote set title=?,content=?,patient_id=? where id=?";
		AccountResponse response=new AccountResponse();
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, patientNote.getTitle());
			stmt.setString(2, patientNote.getContent());
			stmt.setInt(3,patientNote.getPatientId());
			stmt.setInt(4,patientNote.getId());
			 stmt.executeUpdate();
			 response.setResult(1);

		} catch (Exception e) {
			System.out.println(e + "Failed");
			response.setResult(-1);
			}
		return response;
	}
	
	

}
