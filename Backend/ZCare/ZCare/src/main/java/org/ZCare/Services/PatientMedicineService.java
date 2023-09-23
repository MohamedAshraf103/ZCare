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
import org.ZCare.DTO.PatientMedicine;

public class PatientMedicineService {
	public static String url = "jdbc:sqlserver://108.181.157.248\\mssql-138844-0.cloudclusters.net:18756;databaseName=ZCareDB;;encrypt=true;trustServerCertificate=true";
	public static String user = "mohamed";
	public static String pass = "01017883280Mm";
	public static String query = "";

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection(url, user, pass);
		return con;
	}
	// add New Patient Medicine
	public AccountResponse addPatientMedicine(PatientMedicine patientMedicine) {
		query = "insert into PatientMedicine values(?,?,?,?)";
		AccountResponse response=new AccountResponse();
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, patientMedicine.getName());
			stmt.setString(2, patientMedicine.getDescription());
			stmt.setString(3, patientMedicine.getImage());
			stmt.setLong(4,patientMedicine.getPatientId());
			stmt.executeUpdate();
			response.setResult(1);
		} catch (Exception e) {
			System.out.println(e + "Failed");
			response.setResult(-1);
		}
		return response;
	}

	
		//get Patient Medicine for Specific Patient By Id
    Map<Long, PatientMedicine>map=new HashMap<Long, PatientMedicine>();
	public Collection<PatientMedicine> getPatientMedicineById(long id) {
		query = "select * from PatientMedicine where patient_id=?";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setLong(1, id);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				PatientMedicine patientMedicine = new PatientMedicine();
				patientMedicine.setId(res.getLong("id"));
				patientMedicine.setName(res.getString("name"));
				patientMedicine.setDescription(res.getString("description"));
				patientMedicine.setImage(res.getString("image"));
				patientMedicine.setPatientId(res.getLong("patient_id"));
				map.put(patientMedicine.getId(), patientMedicine);
			}

		} catch (Exception e) {
			System.out.println(e + "Failed");
			return null;
		}
		return map.values();
	}
		//Delete Patient Medicine
	public AccountResponse deletePatientMedicine(long id) {
		query = "delete from PatientMedicine where id=?";
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
	
}
