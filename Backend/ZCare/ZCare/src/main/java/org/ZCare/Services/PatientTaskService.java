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
import java.util.List;
import java.util.Map;

import org.ZCare.DTO.AccountResponse;
import org.ZCare.DTO.PatientTask;

public class PatientTaskService {

	public static String url = "jdbc:sqlserver://108.181.157.248\\mssql-138844-0.cloudclusters.net:18756;databaseName=ZCareDB;;encrypt=true;trustServerCertificate=true";
	public static String user = "mohamed";
	public static String pass = "01017883280Mm";
	public static String query = "";

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection(url, user, pass);
		return con;
	}
		//add Patient Task
	public AccountResponse addPatientTask(PatientTask patientTask) {
		AccountResponse response =new AccountResponse();
		query = "insert into PatientTask values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			//stmt.setLong(1, patientTask.getId());
			stmt.setString(1, patientTask.getTitle());
			stmt.setString(2, patientTask.getDescription());
			stmt.setInt(3, patientTask.getType());
			stmt.setBoolean(4, patientTask.isStatus());
			stmt.setBoolean(5, patientTask.isViberation());
			stmt.setFloat(6, patientTask.getDuration());
			stmt.setDate(7, (Date) patientTask.getDate());
			stmt.setTime(8, (Time) patientTask.getTime());
			stmt.setBoolean(9,patientTask.isPatient());
			stmt.setBoolean(10,patientTask.isCareGiver());
			stmt.setLong(11, patientTask.getRoutineId());
			stmt.setLong(12, patientTask.getPlanId());
			stmt.executeUpdate();
			response.setResult(1);
		} catch (Exception e) {
			System.out.println(e + "Failed");
			response.setResult(-1);
		}
		return response;

	}
	
	public AccountResponse addPatientRoutineTaskList(List<PatientTask> list)
	{
		AccountResponse response =new AccountResponse();
		query = "insert into PatientTaskRoutine values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			
			for(PatientTask n:list)
			{
				stmt.setString(1, n.getTitle());
				stmt.setString(2, n.getDescription());
				stmt.setInt(3, n.getType());
				stmt.setBoolean(4, n.isStatus());
				stmt.setBoolean(5, n.isViberation());
				stmt.setFloat(6, n.getDuration());
				stmt.setDate(7, (Date) n.getDate());
				stmt.setTime(8, (Time) n.getTime());
				stmt.setDate(9,n.getCurrentDate());
				stmt.setBoolean(10,n.isPatient());
				stmt.setBoolean(11,n.isCareGiver());
				stmt.setString(12, null);
				stmt.setString(13, null);
				stmt.setLong(14, n.getRoutineId());
				stmt.executeUpdate();	
				response.setResult(1);
			}
		} catch (Exception e) {
			System.out.println(e + "Failed");
			response.setResult(-1);
			return response; 
		}
		return response;
	}
	

	public AccountResponse addPatientPlanTaskList(List<PatientTask> list)
	{
		AccountResponse response =new AccountResponse();
		query = "insert into PatientTaskPlan values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			
			for(PatientTask n:list)
			{
				//stmt.setLong(1, n.getId());
				stmt.setString(1, n.getTitle());
				stmt.setString(2, n.getDescription());
				stmt.setInt(3, n.getType());
				stmt.setBoolean(4, n.isStatus());
				stmt.setBoolean(5, n.isViberation());
				stmt.setFloat(6, n.getDuration());
				stmt.setDate(7, (Date) n.getDate());
				stmt.setTime(8, (Time) n.getTime());
				stmt.setDate(9,n.getCurrentDate());
				stmt.setBoolean(10,n.isPatient());
				stmt.setBoolean(11,n.isCareGiver());
				stmt.setString(12, null);
				stmt.setString(13, null);
				stmt.setLong(14, n.getPlanId());

				stmt.executeUpdate();	
				response.setResult(1);
			}
		} catch (Exception e) {
			System.out.println(e + "Failed");
			response.setResult(-1);
			return response; 
		}
		return response;
	}
		//get All Patient Note By Patient ID
    Map<Long, PatientTask>map=new HashMap<Long, PatientTask>();
	public Collection<PatientTask> getAllPatientTaskRoutine(long id) {
		query = "select * from PatientTaskRoutine where routine_id= ?";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setLong(1, id);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				PatientTask PatientTask = new PatientTask();
				PatientTask.setId(res.getLong("id"));
				PatientTask.setTitle(res.getString("title"));
				PatientTask.setDescription(res.getString("description"));
				PatientTask.setType(res.getInt("type"));
				PatientTask.setStatus(res.getBoolean("status"));
				PatientTask.setViberation(res.getBoolean("viberation"));
				PatientTask.setDuration(res.getFloat("duration"));
				PatientTask.setDate(res.getDate("date"));
				PatientTask.setTime(res.getTime("time"));
				PatientTask.setPatient(res.getBoolean("isPatient"));
				PatientTask.setCareGiver(res.getBoolean("isCareGiver"));
				PatientTask.setCurrentDate(res.getDate("currentDate"));
				PatientTask.setPatientDeleted(res.getBoolean("patientDeleted"));
				PatientTask.setCareGiverDeleted(res.getBoolean("careGiverDeleted"));
				PatientTask.setRoutineId((int) res.getLong("routine_id"));
				//PatientTask.setPlanId((int) res.getLong("plan_id"));
				map.put(PatientTask.getId(),PatientTask);

			}

		} catch (Exception e) {
			System.out.println(e + "Failed");
		}
		return map.values();
	}
	
	 Map<Long, PatientTask>map2=new HashMap<Long, PatientTask>();
		public Collection<PatientTask> getAllPatientTaskPlans(long id) {
			query = "select * from PatientTaskPlan where plan_id= ?";
			try {
				Connection con = getConnection();
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setLong(1, id);
				ResultSet res = stmt.executeQuery();
				while (res.next()) {
					PatientTask PatientTask = new PatientTask();
					PatientTask.setId(res.getLong("id"));
					PatientTask.setTitle(res.getString("title"));
					PatientTask.setDescription(res.getString("description"));
					PatientTask.setType(res.getInt("type"));
					PatientTask.setStatus(res.getBoolean("status"));
					PatientTask.setViberation(res.getBoolean("viberation"));
					PatientTask.setDuration(res.getFloat("duration"));
					PatientTask.setDate(res.getDate("date"));
					PatientTask.setTime(res.getTime("time"));
					PatientTask.setPatient(res.getBoolean("isPatient"));
					PatientTask.setCareGiver(res.getBoolean("isCareGiver"));
					PatientTask.setCurrentDate(res.getDate("currentDate"));
					PatientTask.setPatientDeleted(res.getBoolean("patientDeleted"));
					PatientTask.setCareGiverDeleted(res.getBoolean("careGiverDeleted"));
					//PatientTask.setRoutineId((int) res.getLong("routine_id"));
					PatientTask.setPlanId((int) res.getLong("plan_id"));
					map2.put(PatientTask.getId(),PatientTask);

				}

			} catch (Exception e) {
				System.out.println(e + "Failed");
			}
			return map2.values();
		}
	
	public AccountResponse updatePatientTaskRoutine(PatientTask patientTask) {
		AccountResponse response=new AccountResponse();
		
		query = "UPDATE PatientTaskRoutine SET title =?,description=?,type=?,status=?,viberation=?,duration=?,date=?,time=?,isPatient=?,isCareGiver=?,routine_id=?,currentDate=?,patientDeleted=?,careGiverDeleted=? where id=?";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, patientTask.getTitle());
			stmt.setString(2,patientTask.getDescription());
			stmt.setInt(3, patientTask.getType());
			stmt.setBoolean(4,patientTask.isStatus());
			stmt.setBoolean(5, patientTask.isViberation());
			stmt.setFloat(6, patientTask.getDuration());
			stmt.setDate(7, patientTask.getDate());
			stmt.setTime(8, patientTask.getTime());
			stmt.setBoolean(9, patientTask.isPatient());
			stmt.setBoolean(10, patientTask.isCareGiver());
			stmt.setLong(11,patientTask.getRoutineId());
			stmt.setDate(12, patientTask.getCurrentDate());
			stmt.setBoolean(13, patientTask.isPatientDeleted());
			stmt.setBoolean(14, patientTask.isCareGiverDeleted());
			stmt.setLong(15,patientTask.getId());
			stmt.executeUpdate();
			response.setResult(1);
		} catch (Exception e) {
			System.out.println(e + "Failed");
			response.setResult(-1);
			
		}
		return response;
	}

	public AccountResponse updatePatientTaskPlan(PatientTask patientTask) {
		AccountResponse response=new AccountResponse();
		
		query = "UPDATE PatientTaskPlan SET title =?,description=?,type=?,status=?,viberation=?,duration=?,date=?,time=?,isPatient=?,isCareGiver=?,plan_id=?,currentDate=?,patientDeleted=?,careGiverDeleted=? where id=?";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, patientTask.getTitle());
			stmt.setString(2,patientTask.getDescription());
			stmt.setInt(3, patientTask.getType());
			stmt.setBoolean(4,patientTask.isStatus());
			stmt.setBoolean(5, patientTask.isViberation());
			stmt.setFloat(6, patientTask.getDuration());
			stmt.setDate(7, patientTask.getDate());
			stmt.setTime(8, patientTask.getTime());
			stmt.setBoolean(9, patientTask.isPatient());
			stmt.setBoolean(10, patientTask.isCareGiver());
			stmt.setLong(11,patientTask.getPlanId());
			stmt.setDate(12, patientTask.getCurrentDate());
			stmt.setBoolean(13, patientTask.isPatientDeleted());
			stmt.setBoolean(14, patientTask.isCareGiverDeleted());
			stmt.setLong(15,patientTask.getId());
			stmt.executeUpdate();
			response.setResult(1);
			
		} catch (Exception e) {
			System.out.println(e + "Failed");
			response.setResult(-1);
		}
		return response;
	}

	//delete Patient Note By ID
	public AccountResponse deletePatientTaskRoutine(long id) {
		AccountResponse response=new AccountResponse();
		query = "delete from PatientTaskRoutine where id=?";
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
	
	
	public AccountResponse deletePatientTaskPlan(long id) {
		AccountResponse response=new AccountResponse();
		query = "delete from PatientTaskPlan where id=?";
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
