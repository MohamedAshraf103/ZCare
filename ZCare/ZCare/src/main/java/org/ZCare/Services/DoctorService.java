package org.ZCare.Services;

import org.ZCare.DTO.Doctor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

public class DoctorService {

	public static String url = "jdbc:sqlserver://localhost\\DESKTOP-1KMPQD6:1433;databaseName=ZCare;;encrypt=true;trustServerCertificate=true";
	public static String user = "sa";
	public static String pass = "sa";
	public static String query = "";

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection(url, user, pass);
		return con;
	}

	// add Doctor
	public int DoctorSignUp(Doctor doctor) {
		query = "insert into Doctor values(?,?,?,?,?,?,?,?)";
		int status = 0;
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, doctor.getUserName());
			stmt.setString(2, doctor.getPassword());
			stmt.setString(3, doctor.getFirstName());
			stmt.setString(4, doctor.getLastName());
			stmt.setBoolean(5, doctor.isGender());
			stmt.setString(6, doctor.getDescription());
			stmt.setString(7, doctor.getRate());
			stmt.setString(8, doctor.getEmail());
			status = stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e + "Failed");
			status = 0;
			return status;

		}
		return status;
	}

	public long checkName(String userName) {
		int status = 0;
		ArrayList<Doctor> list = DBLoad();
		for (Doctor d : list)
			if (d.getUserName().equals(userName))
				status = 1;
			else
				status = 0;
		return status;

	}

	// get All Doctors
	Map<Long, Doctor> map = new HashMap<Long, Doctor>();

	public Collection<Doctor> getAllDoctors() {
		query = "select * from Doctor";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				Doctor doctor = new Doctor();
				doctor.setId(res.getLong("id"));
				doctor.setPassword(res.getString("password"));
				doctor.setUserName(res.getString("userName"));
				doctor.setFirstName(res.getString("firstName"));
				doctor.setLastName(res.getString("lastName"));
				doctor.setGender(res.getBoolean("gender"));
				doctor.setDescription(res.getString("description"));
				doctor.setRate(res.getString("rate"));
				doctor.setEmail(res.getString("email"));
				map.put(doctor.getId(), doctor);
			}

		} catch (Exception e) {
			System.out.println(e + "Failed");
		}
		return map.values();
	}

	public ArrayList<Doctor> DBLoad() {
		ArrayList<Doctor> list = new ArrayList<Doctor>();
		query = "select * from Doctor";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				Doctor doctor = new Doctor();
				doctor.setId(res.getLong("id"));
				doctor.setPassword(res.getString("password"));
				doctor.setUserName(res.getString("userName"));
				doctor.setFirstName(res.getString("firstName"));
				doctor.setLastName(res.getString("lastName"));
				doctor.setGender(res.getBoolean("gender"));
				doctor.setDescription(res.getString("description"));
				doctor.setRate(res.getString("rate"));
				doctor.setEmail(res.getString("email"));
				list.add(doctor);
			}

		} catch (Exception e) {
			System.out.println(e + "Failed");
		}
		return list;
	}

	// get Doctor By ID
	public Doctor getDoctorById(long id) {
		Doctor doctor = new Doctor();
		query = "select * from Doctor where id=?";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setLong(1, id);
			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				doctor.setId(res.getLong("id"));
				doctor.setPassword(res.getString("password"));
				doctor.setUserName(res.getString("userName"));
				doctor.setFirstName(res.getString("firstName"));
				doctor.setLastName(res.getString("lastName"));
				doctor.setGender(res.getBoolean("gender"));
				doctor.setDescription(res.getString("description"));
				doctor.setRate(res.getString("rate"));
				doctor.setEmail(res.getString("email"));

			}

		} catch (Exception e) {
			System.out.println(e + "Failed");
		}
		return doctor;
	}

	// delete Specific Doctor
	public int deleteDoctor(long id) {
		query = "delete from Doctor where id=?";
		int status = 0;
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

	// update Doctor Data By ID
	public int updateDoctorDataById(String description, String rate, long id) {
		query = "update  Doctor set description= ? , rate= ? where id=?";
		int status = 0;
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, description);
			stmt.setString(2, rate);
			stmt.setLong(3, id);
			status = stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e + "Failed");
			return status = 0;
		}
		return status;
	}

	// update Doctor UserName By Email
	public int updateDoctorUserNameByEmail(String userName, String email) {
		query = "update  Doctor set userName= ? where email= ?";
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

	// Update Doctor Password By UserName
	public int updateDoctorPasswordByUserName(String userName, String password) {
		query = "update  Doctor set password= ? where userName= ?";
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

	// check Doctor found in DB or not
	public long DoctorLogin(String userName, String password) throws SQLException {
		ArrayList<Doctor> list = DBLoad(); // calling function get All Data
		long x = 0;
		for (Doctor d : list) {
			if (d.getUserName().equals(userName)) {
				if (d.getPassword().equals(password)) {
					x = d.getId(); // user found
					break;
				} else {
					x = -1; // password wrong
					break;
				}
			} else {
				x = -2; // user not found
			}
		}
		return x;
	}

}
