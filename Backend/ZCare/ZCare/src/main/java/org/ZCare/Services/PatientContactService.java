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
import org.ZCare.DTO.PatientContact;

public class PatientContactService {
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
	public AccountResponse addPatientContact(PatientContact patientContact) {
		query = "insert into PatientContact values(?,?,?,?,?)";
		AccountResponse response=new AccountResponse();
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			//stmt.setInt(1, patientRoutine.getId());
			stmt.setString(1,patientContact.getTitle());
			stmt.setString(2, patientContact.getName());
			stmt.setString(3, patientContact.getPhone());
			stmt.setString(4, patientContact.getImage());
			stmt.setInt(5, patientContact.getPatientId());
			stmt.executeUpdate();
			response.setResult(1);
			//response.setCode(id);
		} catch (Exception e) {
			System.out.println(e + "Failed");
			response.setResult(-1);
		}
		return response;

	}
	
	 Map<Long, PatientContact>map=new HashMap<Long, PatientContact>();
		public Collection<PatientContact> getAllPatientContact(long id) {
			query = "select * from PatientContact where patient_id=?";
			try {
				Connection con = getConnection();
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setLong(1, id);
				ResultSet res = stmt.executeQuery();
				while (res.next()) {
					PatientContact patientContact = new PatientContact();
					patientContact.setId(res.getInt("id"));
					patientContact.setTitle(res.getString("title"));
					patientContact.setName(res.getString("name"));
					patientContact.setPhone(res.getString("phone"));
					patientContact.setImage(res.getString("image"));
					patientContact.setPatientId(res.getInt("patient_id"));
					map.put((long) patientContact.getId(),patientContact);
				}

			} catch (Exception e) {
				System.out.println(e + "Failed");
			}
			return map.values();
		}
	
		//delete Patient Contact By ID
		public AccountResponse deletePatientContact(long id) {
			AccountResponse response=new AccountResponse();
			query = "delete from PatientContact where id=?";
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
