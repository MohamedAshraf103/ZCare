package org.ZCare.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.ZCare.DTO.CareGiver;

public class CareGiverService {
	public static String url = "jdbc:sqlserver://localhost\\DESKTOP-1KMPQD6:1433;databaseName=ZCare;;encrypt=true;trustServerCertificate=true";
	public static String user = "sa";
	public static String pass = "sa";
	public static String query = "";

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection(url, user, pass);
		return con;
	}

	// add Care Giver
	public int careGiverSignUp(CareGiver careGiver) {
		int status = 0;
		query = "insert into CareGiver values(?,?,?,?,?,?,?,?,?)";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, careGiver.getUserName());
			stmt.setString(2, careGiver.getPassword());
			stmt.setString(3, careGiver.getFirstName());
			stmt.setString(4, careGiver.getLastName());
			stmt.setBoolean(5, careGiver.isGender());
			stmt.setString(6, careGiver.getAddress());
			stmt.setString(7, careGiver.getPhone());
			stmt.setString(8, careGiver.getEmail());
			stmt.setLong(9, careGiver.getPatientId());
			status = stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e + "Failed");
			return status = 0;
		}
		return status;
	}

	public long checkName(String userName) {
		int status = 0;
		ArrayList<CareGiver> list = DBLoad();
		for (CareGiver c : list)
			if (c.getUserName().equals(userName))
				status = 1;
			else
				status = 0;
		return status;

	}

	// add Care Giver Phones
//	public int savePhone(CareGiverPhone careGiverPhone) {
//		int status=0;
//		query ="insert into CareGiverPhone values(?,?)";
//		try {
//			Connection con = getConnection();
//			PreparedStatement stmt = con.prepareStatement(query);
//			stmt.setString(1, careGiverPhone.getPhone());
//			stmt.setLong(2,careGiverPhone.getCareGiverId());
//			status=stmt.executeUpdate();
//		} catch (Exception e) {
//			System.out.println(e.getMessage() + "Failed");
//			return status=0;
//		}
//		return status;
//	}
	Map<Long, CareGiver> map = new HashMap<Long, CareGiver>();

	public Collection<CareGiver> getAllCareGivers() {
		query = "select * from CareGiver";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				CareGiver careGiver = new CareGiver();
				careGiver.setId(res.getLong("id"));
				careGiver.setPassword(res.getString("password"));
				careGiver.setUserName(res.getString("userName"));
				careGiver.setFirstName(res.getString("firstName"));
				careGiver.setLastName(res.getString("lastName"));
				careGiver.setGender(res.getBoolean("gender"));
				careGiver.setAddress(res.getString("address"));
				careGiver.setEmail(res.getString("email"));
				careGiver.setPhone(res.getString("phone"));
				careGiver.setPatientId(res.getLong("patient_id"));
				map.put(careGiver.getId(), careGiver);
			}

		} catch (Exception e) {
			System.out.println(e + "Failed");
		}
		return map.values();
	}

	/////////////////////////
	// Get All CareGivers
	public ArrayList<CareGiver> DBLoad() {
		ArrayList<CareGiver> list = new ArrayList<CareGiver>();
		query = "select * from CareGiver";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				CareGiver careGiver = new CareGiver();
				careGiver.setId(res.getLong("id"));
				careGiver.setPassword(res.getString("password"));
				careGiver.setUserName(res.getString("userName"));
				careGiver.setFirstName(res.getString("firstName"));
				careGiver.setLastName(res.getString("lastName"));
				careGiver.setGender(res.getBoolean("gender"));
				careGiver.setAddress(res.getString("address"));
				careGiver.setEmail(res.getString("email"));
				careGiver.setPhone(res.getString("phone"));
				careGiver.setPatientId(res.getLong("patient_id"));
				list.add(careGiver);
			}

		} catch (Exception e) {
			System.out.println(e + "Failed");
		}
		return list;
	}

	// get Specific Care Giver
	public CareGiver getCareGiverById(long id) {
		CareGiver careGiver = new CareGiver();
		query = "select * from CareGiver where id=?";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setLong(1, id);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				careGiver.setId(res.getLong("id"));
				careGiver.setPassword(res.getString("password"));
				careGiver.setUserName(res.getString("userName"));
				careGiver.setFirstName(res.getString("firstName"));
				careGiver.setLastName(res.getString("lastName"));
				careGiver.setGender(res.getBoolean("gender"));
				careGiver.setAddress(res.getString("address"));
				careGiver.setEmail(res.getString("email"));
				careGiver.setPhone(res.getString("phone"));
				careGiver.setPatientId(res.getLong("patient_id"));
			}

		} catch (Exception e) {
			System.out.println(e + "Failed");
		}
		return careGiver;
	}

	// delete Care Giver By ID
	public int deleteCareGiver(long id) {
		int status = 0;
		query = "delete from CareGiver where id=?";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setLong(1, id);
			status = stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e + "Failed");
			return status = 0;
		}
		return status;
	}

	// Update Care Giver UserName By Email
	public int updateCareGiverUserNameByEmail(String userName, String email) {
		query = "update  CareGiver set userName= ? where email= ?";
		int status = 0;
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, userName);
			stmt.setString(2, email);
			status = stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e + "Failed");
			return status = 0;
		}
		return status;
	}

	// Update Care Giver Password By UserName
	public int updateCareGiverPasswordByUserName(String password, String userName) {
		query = "update  CareGiver set password= ? where userName= ?";
		int status = 0;
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, password);
			stmt.setString(2, userName);
			status = stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e + "Failed");
			return status = 0;
		}
		return status;
	}

	// check CareGiver found in DB or not
	public String careGiverLogin(String userName, String password) throws SQLException {
		ArrayList<CareGiver> list = DBLoad(); // calling function get All Data
		String x ="";

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUserName().equals(userName)) {
				if (list.get(i).getPassword().equals(password)) {
					x+=list.get(i).getId();
					x+=",";
					x+=list.get(i).getPatientId();					
					break;
				} else {
					x+=-1; //wrong Password
					break;
				}
			} else {
				x+=-2; //not found
			}
		}
		return x;
	}
}
