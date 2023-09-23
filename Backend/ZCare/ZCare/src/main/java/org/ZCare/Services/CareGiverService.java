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

import org.ZCare.DTO.AccountResponse;
import org.ZCare.DTO.CareGiver;
import org.ZCare.DTO.LoginResponse;

public class CareGiverService {
	public static String url = "jdbc:sqlserver://108.181.157.248\\mssql-138844-0.cloudclusters.net:18756;databaseName=ZCareDB;;encrypt=true;trustServerCertificate=true";
	public static String user = "mohamed";
	public static String pass = "01017883280Mm";
	public static String query = "";

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection(url, user, pass);
		return con;
	}
	// add Care Giver
	public int careGiverSignUp(CareGiver careGiver) {
		int status = 0;
		query = "insert into CareGiver values(?,?,?,?,?,?,?,?,?,?)";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, careGiver.getUserName());
			stmt.setString(2, careGiver.getPassword());
			stmt.setString(3, careGiver.getName());
			stmt.setBoolean(4, careGiver.isGender());
			stmt.setString(5, careGiver.getAddress());
			stmt.setString(6, careGiver.getPhone());
			stmt.setString(7, careGiver.getEmail());
			stmt.setDate(8, careGiver.getDateOfBirth());
			stmt.setString(9, careGiver.getImage());
			stmt.setLong(10, careGiver.getPatientId());
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
			if (c.getUserName().equals(userName)) {
				status = 1;
				break;

			} else
				status = 0;
		return status;

	}
	public AccountResponse checkPatientCareGiver(int id) {
		AccountResponse response=new AccountResponse();
		ArrayList<CareGiver> list = DBLoad();
		for (CareGiver c : list)
			if (c.getPatientId()==id) {
				response.setResult(1);
				break;

			} else
				response.setResult(-1);
		return response;

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
				careGiver.setName(res.getString("name"));
				careGiver.setGender(res.getBoolean("gender"));
				careGiver.setAddress(res.getString("address"));
				careGiver.setEmail(res.getString("email"));
				careGiver.setPhone(res.getString("phone"));
				careGiver.setDateOfBirth(res.getDate("dateOfBirth"));
				careGiver.setImage(res.getString("image"));
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
				careGiver.setName(res.getString("name"));
				careGiver.setGender(res.getBoolean("gender"));
				careGiver.setAddress(res.getString("address"));
				careGiver.setEmail(res.getString("email"));
				careGiver.setPhone(res.getString("phone"));
				careGiver.setDateOfBirth(res.getDate("dateOfBirth"));
				careGiver.setPatientId(res.getLong("patient_id"));
				list.add(careGiver);
			}

		} catch (Exception e) {
			System.out.println(e + "Failed");
		}
		return list;
	}

	public boolean ifEmailExist(String email) {
		ArrayList<CareGiver> careGivers = DBLoad(); // calling function get All Data
		for (CareGiver c : careGivers) {
			if (c.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
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
				careGiver.setName(res.getString("name"));
				careGiver.setGender(res.getBoolean("gender"));
				careGiver.setAddress(res.getString("address"));
				careGiver.setEmail(res.getString("email"));
				careGiver.setPhone(res.getString("phone"));
				careGiver.setDateOfBirth(res.getDate("dateOfBirth"));
				careGiver.setImage(res.getString("image"));
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

//Update Care Giver Profile
	public AccountResponse updateCareGiverProfile(CareGiver careGiver) {
		AccountResponse response = new AccountResponse();
		query = "UPDATE CareGiver\r\n"
				+ "SET userName=?,name=?,gender=?,address=?,phone=?,email=?,dateOfBirth=?,image=?,patient_id=? where id=?";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, careGiver.getUserName());
			stmt.setString(2, careGiver.getName());
			stmt.setBoolean(3, careGiver.isGender());
			stmt.setString(4, careGiver.getAddress());
			stmt.setString(5, careGiver.getPhone());
			stmt.setString(6, careGiver.getEmail());
			stmt.setDate(7, careGiver.getDateOfBirth());
			stmt.setString(8, careGiver.getImage());
			stmt.setLong(9, careGiver.getPatientId());
			stmt.setLong(10, careGiver.getId());
			stmt.executeUpdate();
			response.setResult(1);

		} catch (Exception e) {
			System.out.println(e + "Failed");
			response.setResult(-1);
		}
		return response;
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

	public boolean ifPasswordExist(String password) {
		ArrayList<CareGiver> careGivers = DBLoad(); // calling function get All Data
		for (CareGiver c : careGivers) {
			if (c.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	public AccountResponse updatePasswordByPassword(String oldPass, String newPass, long id) {
		AccountResponse response = new AccountResponse();
		boolean f = ifPasswordExist(oldPass);
		if (f) {
			query = "update  CareGiver set password= ? where id= ?";
			try {
				Connection con = getConnection();
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setString(1, newPass);
				stmt.setLong(2, id);
				stmt.executeUpdate();
				response.setResult(1);
			} catch (Exception e) {
				System.out.println(e + "Failed");
				response.setResult(-1);
			}

		}

		else
			response.setResult(-2);

		return response;

	}

	// Update Care Giver Password By UserName
	public int updateCareGiverPasswordByEmail(String password, String email) {
		query = "update  CareGiver set password= ? where email= ?";
		int status = 0;
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, password);
			stmt.setString(2, email);
			status = stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e + "Failed");
			return status = 0;
		}
		return status;
	}

	// check CareGiver found in DB or not
	public LoginResponse careGiverLogin(String userName, String password) throws SQLException {
		ArrayList<CareGiver> careGivers = DBLoad(); // calling function get All Data
		LoginResponse loginResponse = new LoginResponse();
		for (CareGiver c : careGivers) {
			if (c.getUserName().equals(userName)) {
				if (c.getPassword().equals(password)) {
					loginResponse.setId(c.getId());
					loginResponse.setPatientId(c.getPatientId());
					loginResponse.setResult(1);
					break;
				} else {
					loginResponse.setResult(-1);// Wrong Password
					break;
				}
			} else {
				loginResponse.setResult(-2); // not found
			}
		}
		return loginResponse;
	}
}
