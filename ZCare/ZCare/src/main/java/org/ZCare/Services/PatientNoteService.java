package org.ZCare.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.ZCare.DTO.PatientNote;

public class PatientNoteService {

	public static String url = "jdbc:sqlserver://localhost\\DESKTOP-1KMPQD6:1433;databaseName=ZCare;;encrypt=true;trustServerCertificate=true";
	public static String user = "sa";
	public static String pass = "sa";
	public static String query = "";

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection(url, user, pass);
		return con;
	}
		//add Patient note
	public int addPatientNote(PatientNote patientNote) {
		int status = 0;
		query = "insert into PatientNote values(?,?,?,?,?,?,?,?,?)";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, patientNote.getTitle());
			stmt.setString(2, patientNote.getDescription());
			stmt.setInt(3, patientNote.getType());
			stmt.setBoolean(4, patientNote.isStatus());
			stmt.setBoolean(5, patientNote.isViberation());
			stmt.setFloat(6, patientNote.getDuration());
			stmt.setDate(7, (Date) patientNote.getDate());
			stmt.setTime(8, (Time) patientNote.getTime());
			stmt.setLong(9, patientNote.getPatientId());
			status = stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e + "Failed");
			return status = 0;
		}
		return status;

	}
		//get All Patient Note By Patient ID
    Map<Long, PatientNote>map=new HashMap<Long, PatientNote>();
	public Collection<PatientNote> getAllPatientNote(long id) {
		query = "select * from PatientNote where patient_id= ?";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setLong(1, id);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				PatientNote patientNote = new PatientNote();
				patientNote.setId(res.getLong("id"));
				patientNote.setTitle(res.getString("title"));
				patientNote.setDescription(res.getString("description"));
				patientNote.setType(res.getInt("type"));
				patientNote.setStatus(res.getBoolean("status"));
				patientNote.setViberation(res.getBoolean("viberation"));
				patientNote.setDuration(res.getFloat("duration"));
				patientNote.setDate(res.getDate("date"));
				patientNote.setTime(res.getTime("time"));
				patientNote.setPatientId(res.getLong("patient_id"));
				map.put(patientNote.getId(),patientNote);

			}

		} catch (Exception e) {
			System.out.println(e + "Failed");
		}
		return map.values();
	}
	
	public int updatePatientNoteData(PatientNote patientNote) {
		query = "UPDATE PatientNote\r\n"
				+ "SET title =?,description=?,type=?,status=?,viberation=?,duration=?,date=?,time=?,patient_id=? where id=?";
		int res = 0;
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, patientNote.getTitle());
			stmt.setString(2,patientNote.getDescription());
			stmt.setInt(3, patientNote.getType());
			stmt.setBoolean(4,patientNote.isStatus());
			stmt.setBoolean(5, patientNote.isViberation());
			stmt.setFloat(6, patientNote.getDuration());
			stmt.setDate(7, patientNote.getDate());
			stmt.setTime(8, patientNote.getTime());
			stmt.setLong(9, patientNote.getPatientId());
			stmt.setLong(10,patientNote.getId());
			res = stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e + "Failed");
			return res=0;
		}
		return res;
	}

	//delete Patient Note By ID
	public int deletePatientNote(long id) {
		int status = 0;
		query = "delete from PatientNote where id=?";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setLong(1, id);
			status = stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e + "Failed");
			status = 0;
			return status;
		}
		return status;
	}
	

}
