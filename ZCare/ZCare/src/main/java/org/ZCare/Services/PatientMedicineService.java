package org.ZCare.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.ZCare.DTO.PatientMedicine;

public class PatientMedicineService {
	public static String url = "jdbc:sqlserver://localhost\\DESKTOP-1KMPQD6:1433;databaseName=ZCare;;encrypt=true;trustServerCertificate=true";
	public static String user = "sa";
	public static String pass = "sa";
	public static String query = "";

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection(url, user, pass);
		return con;
	}
	// add New Patient Medicine
	public int addPatientMedicine(PatientMedicine patientMedicine) {
		int status = 0;
		query = "insert into PatientMedicine values(?,?,?)";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, patientMedicine.getName());
			stmt.setString(2, patientMedicine.getImagePath());
			stmt.setLong(3,patientMedicine.getPatientId());
			status=stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e + "Failed");
			status=0;
			return status;
		}
		return status;
	}

	
		//get Patient Medicine for Specific Patient By Id
    Map<Long, PatientMedicine>map=new HashMap<Long, PatientMedicine>();
	public Collection<PatientMedicine> getPatientMedicineById(long id) {
		PatientMedicine patientMedicine = new PatientMedicine();
		query = "select * from PatientMedicine where patient_id=?";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setLong(1, id);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				patientMedicine.setId(res.getLong("id"));
				patientMedicine.setName(res.getString("name"));
				patientMedicine.setImagePath(res.getString("imagePath"));
				patientMedicine.setPatientId(res.getLong("patientId"));
				map.put(patientMedicine.getId(), patientMedicine);

			}

		} catch (Exception e) {
			System.out.println(e + "Failed");
			return null;
		}
		return map.values();
	}
		//Delete Patient Medicine
	public int deletePatientMedicine(long id) {
		int status = 0;
		query = "delete from PatientMedicine where id=?";
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
	
	//Update Patient Medicine Time By ID
	public int updatePatientMedicineTimeById(String imagePath,long id) {
		query = "update  PatientMedicine set imagePath= ? where id= ?";
		int status = 0;
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, imagePath);
			stmt.setLong(2,id);
			status = stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e + "Failed");
			return status=0;
		}
		return status;
	}

}
