package org.ZCare.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.ZCare.DTO.AccountResponse;
import org.ZCare.DTO.PatientRoutine;

public class PatientRoutineService {
	public static String url = "jdbc:sqlserver://108.181.157.248\\mssql-138844-0.cloudclusters.net:18756;databaseName=ZCareDB;;encrypt=true;trustServerCertificate=true";
	public static String user = "mohamed";
	public static String pass = "01017883280Mm";
	public static String query = "";

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection(url, user, pass);
		return con;
	}
		//add Patient Routine
	public AccountResponse addPatientRoutine(PatientRoutine patientRoutine) {
		query = "insert into PatientRoutine values(?,?,?)";
		AccountResponse response=new AccountResponse();
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			//stmt.setInt(1, patientRoutine.getId());
			stmt.setString(1, patientRoutine.getName());
			stmt.setInt(2, patientRoutine.getStatus());
			stmt.setInt(3, patientRoutine.getPatientId());
			stmt.executeUpdate();
			int id=0;
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
			    id = rs.getInt(1);
			}
			response.setResult(id);
		} catch (Exception e) {
			System.out.println(e + "Failed");
			response.setResult(-1);

		}
		return response;
   
	}
	
	//public  List <PatientRoutine> list  = new ArrayList<PatientRoutine> ();

	
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
				patientRoutine.setId(res.getInt("id"));
				patientRoutine.setName(res.getString("name"));
				patientRoutine.setPatientId(res.getInt("patient_id"));
				patientRoutine.setStatus(res.getInt("status"));
				map.put((long) patientRoutine.getId(),patientRoutine);
			}

		} catch (Exception e) {
			System.out.println(e + "Failed");
		}
		return map.values();
	}
	
//	public int updatePatientRoutineData(PatientRoutine patientRoutine) {
//		query = "UPDATE PatientRoutine\r\n"
//				+ "SET title =?,description=?,type=?,status=?,viberation=?,duration=?,date=?,time=?,patient_id=? where id=?";
//		int res = 0;
//		try {
//			Connection con = getConnection();
//			PreparedStatement stmt = con.prepareStatement(query);
//			stmt.setString(1, patientRoutine.getTitle());
//			stmt.setString(2,patientRoutine.getDescription());
//			stmt.setInt(3, patientRoutine.getType());
//			stmt.setBoolean(4,patientRoutine.isStatus());
//			stmt.setBoolean(5, patientRoutine.isViberation());
//			stmt.setFloat(6, patientRoutine.getDuration());
//			stmt.setDate(7, patientRoutine.getDate());
//			stmt.setTime(8, patientRoutine.getTime());
//			stmt.setLong(9, patientRoutine.getPatientId());
//			stmt.setLong(10,patientRoutine.getId());
//			res = stmt.executeUpdate();
//		} catch (Exception e) {
//			System.out.println(e + "Failed");
//			return res=0;
//		}
//		return res;
//	}

	//delete Patient Note By ID
	public AccountResponse deletePatientRoutine(long id) {
		AccountResponse response=new AccountResponse();
		query = "delete from PatientRoutine where id=?";
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
