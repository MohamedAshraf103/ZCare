package org.ZCare.Services;

import java.util.Collection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.sql.Date;

import org.ZCare.DTO.AccountResponse;
import org.ZCare.DTO.LoginResponse;
import org.ZCare.DTO.Patient;

public class PatientService {

	public static String url = "jdbc:sqlserver://108.181.157.248\\mssql-138844-0.cloudclusters.net:18756;databaseName=ZCareDB;;encrypt=true;trustServerCertificate=true";
	public static String user = "mohamed";
	public static String pass = "01017883280Mm";
	public static String query = "";

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection(url, user, pass);
		return con;
	}

// add patient object
	public int PatientSignUp(Patient patient) {
		int status = 0;
		query = "insert into Patient values(?,?,?,?,?,?,?,?,?,?)";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, patient.getUserName());
			stmt.setString(2, patient.getPassword());
			stmt.setString(3, patient.getName());
			stmt.setBoolean(4, patient.isGender());
			stmt.setString(5, patient.getAddress());
			stmt.setString(6, patient.getPhone());
			stmt.setString(7, patient.getEmail());
			stmt.setDate(8, (Date) patient.getDateOfBirth());
			stmt.setString(9, patient.getImage());
			stmt.setString(10, null);
			status = stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e + "Failed");
			return status = 0;
		}
		return status;
	}

	Map<Long, Patient> map = new HashMap<Long, Patient>();

	public Collection<Patient> getAllPatients() {
		query = "select * from Patient";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				Patient patient = new Patient();
				patient.setId(res.getLong("id"));
				patient.setPassword(res.getString("password"));
				patient.setUserName(res.getString("userName"));
				patient.setName(res.getString("name"));
				patient.setGender(res.getBoolean("gender"));
				patient.setAddress(res.getString("address"));
				patient.setPhone(res.getString("phone"));
				patient.setDateOfBirth(res.getDate("dateOfBirth"));
				patient.setEmail(res.getString("email"));
				patient.setImage(res.getString("image"));
				patient.setDoctorId(res.getLong("doctor_id"));
				map.put(patient.getId(), patient);
			}

		} catch (Exception e) {
			System.out.println(e + "Failed");
		}
		return map.values();
	}

	// return All Patients
	public static ArrayList<Patient> DBLoad() {
		ArrayList<Patient> list = new ArrayList<Patient>();
		query = "select * from Patient";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				Patient patient = new Patient();
				patient.setId(res.getLong("id"));
				patient.setPassword(res.getString("password"));
				patient.setUserName(res.getString("userName"));
				patient.setName(res.getString("name"));
				patient.setGender(res.getBoolean("gender"));
				patient.setAddress(res.getString("address"));
				patient.setPhone(res.getString("phone"));
				patient.setDateOfBirth(res.getDate("dateOfBirth"));
				patient.setEmail(res.getString("email"));
				patient.setImage(res.getString("image"));
				patient.setDoctorId(res.getLong("doctor_id"));
				list.add(patient);
			}

		} catch (Exception e) {
			System.out.println(e + "Failed");
		}
		return list;
	}

	public long checkName(String userName) {
		int status = 0;
		ArrayList<Patient> list = DBLoad();
		for (Patient p : list)
			if (p.getUserName().equals(userName)) {
				status = 1;
				break;
			} else
				status = 0;
		return status;

	}

	/*
	 * public long checkEmail(String email) { int status = 0; ArrayList<Patient>
	 * list = DBLoad(); for (Patient p : list) if (p.getEmail().equals(email)) {
	 * status = 1; break; } else status = 0; return status;
	 * 
	 * }
	 */

	public boolean ifEmailExist(String email) {
		ArrayList<Patient> patients = DBLoad(); // calling function get All Data
		for (Patient p : patients) {
			if (p.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}

	public static boolean ifPasswordExist(String password) {
		ArrayList<Patient> patients = DBLoad(); // calling function get All Data
		for (Patient p : patients) {
			if (p.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	// getPatientByID
	public Patient getPatientById(long id) {
		Patient patient = new Patient();
		query = "select * from Patient where id=?";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setLong(1, id);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				patient.setId(res.getLong("id"));
				patient.setPassword(res.getString("password"));
				patient.setUserName(res.getString("userName"));
				patient.setName(res.getString("name"));
				patient.setGender(res.getBoolean("gender"));
				patient.setAddress(res.getString("address"));
				patient.setPhone(res.getString("phone"));
				patient.setDateOfBirth(res.getDate("dateOfBirth"));
				patient.setEmail(res.getString("email"));
				patient.setImage(res.getString("image"));
				patient.setDoctorId(res.getLong("doctor_id"));
			}

		} catch (Exception e) {
			System.out.println(e + "Failed");
		}
		return patient;
	}

	

	// delete Patient By ID
	public int deletePatient(long id) {
		int status = 0;
		query = "delete from Patient where id=?";
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

	// Update Patient Data
	public AccountResponse updatePatientProfile(Patient patient) {
		AccountResponse response = new AccountResponse();
		query = "UPDATE Patient\r\n"
				+ "SET userName=?,name=?,gender=?,address=?,phone=?,email=?,dateOfBirth=?,image=?,doctor_id=? where id=?";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, patient.getUserName());
			stmt.setString(2, patient.getName());
			stmt.setBoolean(3, patient.isGender());
			stmt.setString(4, patient.getAddress());
			stmt.setString(5, patient.getPhone());
			stmt.setString(6, patient.getEmail());
			stmt.setDate(7, patient.getDateOfBirth());
			stmt.setString(8, patient.getImage());
			stmt.setString(9, null);
			stmt.setLong(10, patient.getId());
			stmt.executeUpdate();
			response.setResult(1);

		} catch (Exception e) {
			System.out.println(e + "Failed");
			response.setResult(-1);
		}
		return response;
	}

	public AccountResponse updatePasswordByPassword(String oldPass, String newPass, long id) {
		AccountResponse response = new AccountResponse();
		boolean f = ifPasswordExist(oldPass);
		if (f) {
			query = "update  Patient set password= ? where id= ?";
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

	// Update Patient Password By UserName
	public int updatePatientPasswordByEmail(String password, String email) {
		query = "update  Patient set password= ? where email= ?";
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

	// Update Patient UserName By Email
	public int updatePatientUserNameByEmail(String userName, String email) {
		query = "update  Patient set userName= ? where email= ?";
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

	// check Patient found in DB or not
	public LoginResponse PatientLogin(String userName, String password) throws SQLException {
		ArrayList<Patient> patients = DBLoad(); // calling function get All Data
		LoginResponse loginResponse = new LoginResponse();
		for (Patient p : patients) {
			if (p.getUserName().equals(userName)) {
				if (p.getPassword().equals(password)) {
					loginResponse.setId(p.getId());
					loginResponse.setResult(1);
					break;
				} else {
					loginResponse.setResult(-1);// password wrong
					break;
				}
			} else {
				loginResponse.setResult(-2);
				; // user not found
			}
		}
		return loginResponse;
	}

}
