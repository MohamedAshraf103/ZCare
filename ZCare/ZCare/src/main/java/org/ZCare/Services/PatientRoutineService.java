package org.ZCare.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.ZCare.DTO.PatientRoutine;

public class PatientRoutineService {
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
	public int addPatientRoutine(PatientRoutine patientRoutine) {
		int status = 0;
		query = "insert into PatientRoutine values(?,?,?,?,?,?,?,?,?)";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, patientRoutine.getTitle());
			stmt.setString(2, patientRoutine.getDescription());
			stmt.setInt(3, patientRoutine.getType());
			stmt.setBoolean(4, patientRoutine.isStatus());
			stmt.setBoolean(5, patientRoutine.isViberation());
			stmt.setFloat(6, patientRoutine.getDuration());
			stmt.setDate(7, (Date) patientRoutine.getDate());
			stmt.setTime(8,patientRoutine.getTime());
			stmt.setLong(8, patientRoutine.getPatientId());
			status = stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e + "Failed");
			return status = 0;
		}
		return status;

	}
		//get All Patient Routine By Patient ID
    Map<Long, PatientRoutine>map=new HashMap<Long, PatientRoutine>();
	public Collection<PatientRoutine> getAllPatientRoutine(long id) {
		query = "select * from PatientRoutine where patient_id= ?";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setLong(1, id);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				PatientRoutine patientRoutine = new PatientRoutine();
				patientRoutine.setId(res.getLong("id"));
				patientRoutine.setTitle(res.getString("title"));
				patientRoutine.setDescription(res.getString("description"));
				patientRoutine.setType(res.getInt("type"));
				patientRoutine.setStatus(res.getBoolean("status"));
				patientRoutine.setViberation(res.getBoolean("viberation"));
				patientRoutine.setDuration(res.getFloat("duration"));
				patientRoutine.setDate(res.getDate("date"));
				patientRoutine.setTime(res.getTime("time"));
				patientRoutine.setPatientId(res.getLong("patient_id"));
				map.put(patientRoutine.getId(),patientRoutine);

			}

		} catch (Exception e) {
			System.out.println(e + "Failed");
		}
		return map.values();
	}
	
	public int updatePatientRoutineData(PatientRoutine patientRoutine) {
		query = "UPDATE PatientRoutine\r\n"
				+ "SET title =?,description=?,type=?,status=?,viberation=?,duration=?,date=?,time=?,patient_id=? where id=?";
		int res = 0;
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, patientRoutine.getTitle());
			stmt.setString(2,patientRoutine.getDescription());
			stmt.setInt(3, patientRoutine.getType());
			stmt.setBoolean(4,patientRoutine.isStatus());
			stmt.setBoolean(5, patientRoutine.isViberation());
			stmt.setFloat(6, patientRoutine.getDuration());
			stmt.setDate(7, patientRoutine.getDate());
			stmt.setTime(8, patientRoutine.getTime());
			stmt.setLong(9, patientRoutine.getPatientId());
			stmt.setLong(10,patientRoutine.getId());
			res = stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e + "Failed");
			return res=0;
		}
		return res;
	}

	//delete Patient Note By ID
	public int deletePatientRoutine(long id) {
		int status = 0;
		query = "delete from PatientRoutine where id=?";
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
