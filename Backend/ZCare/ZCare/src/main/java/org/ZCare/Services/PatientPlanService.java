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
import org.ZCare.DTO.PatientPlan;

public class PatientPlanService {
	
	public static String url = "jdbc:sqlserver://108.181.157.248\\mssql-138844-0.cloudclusters.net:18756;databaseName=ZCareDB;;encrypt=true;trustServerCertificate=true";
	public static String user = "mohamed";
	public static String pass = "01017883280Mm";
	public static String query = "";

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection(url, user, pass);
		return con;
	}
		//add Patient Plan
	public AccountResponse addPatientPlan(PatientPlan patientPlan) {
		query = "insert into PatientPlan values(?,?,?)";
		AccountResponse response=new AccountResponse();
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			//stmt.setInt(1, patientPlan.getId());
			stmt.setDate(1, patientPlan.getDate());
			stmt.setInt(2, patientPlan.getStatus());
			stmt.setInt(3, patientPlan.getPatientId());
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

	
		//get All Patient Plans By Patient ID
    Map<Long, PatientPlan>map=new HashMap<Long, PatientPlan>();
	public Collection<PatientPlan> getAllPatientPlan(long id) {
		query = "select * from PatientPlan where patient_id= ?";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setLong(1, id);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				PatientPlan patientPlan = new PatientPlan();
				patientPlan.setId(res.getInt("id"));
				patientPlan.setDate(res.getDate("date"));
				patientPlan.setStatus(res.getInt("status"));
				patientPlan.setPatientId(res.getInt("patient_id"));
				map.put((long) patientPlan.getId(),patientPlan);
			}

		} catch (Exception e) {
			System.out.println(e + "Failed");
		}
		return map.values();
	}

	//delete Patient Plan By ID
		public AccountResponse deletePatientPlan(int id) {
			//updatePatientPlanTasks(id);
			AccountResponse accountResponse=new AccountResponse();
			query = "delete from PatientPlan where id=?";
			try {
				Connection con = getConnection();
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setInt(1, id);
				 stmt.executeUpdate();
				 accountResponse.setResult(1);
				// deletePatientPlanNulls();
			} catch (Exception e) {
				System.out.println(e + "Failed");
				accountResponse.setResult(0);
			}
			return accountResponse;
		}
		
		public AccountResponse deletePatientPlanNulls() {
			AccountResponse accountResponse=new AccountResponse();
			query = "delete from PatientTask where routine_id=? and plan_id=? ";
			try {
				Connection con = getConnection();
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setString(1,null);
				stmt.setString(2,null);

				 stmt.executeUpdate();
				 accountResponse.setResult(1);
			} catch (Exception e) {
				System.out.println(e + "Failed");
				accountResponse.setResult(0);
			}
			return accountResponse;
		}
		
		public AccountResponse updatePatientPlanTasks(int id)
		{
			AccountResponse accountResponse=new AccountResponse();
			query = "update PatientTask set plan_id=? where plan_id=?";
			try {
				Connection con = getConnection();
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setString(1,null);
				stmt.setInt(2, id);
				 stmt.executeUpdate();
				 accountResponse.setResult(1);
			} catch (Exception e) {
				System.out.println(e + "Failed");
				accountResponse.setResult(0);
			}
			return accountResponse;
		}
}
