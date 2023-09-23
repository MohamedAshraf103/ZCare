package org.ZCare.Services;

import org.ZCare.DTO.AccountResponse;
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
import org.ZCare.DTO.LoginResponse;
public class DoctorService {

	public static String url = "jdbc:sqlserver://108.181.157.248\\mssql-138844-0.cloudclusters.net:18756;databaseName=ZCareDB;;encrypt=true;trustServerCertificate=true";
	public static String user = "mohamed";
	public static String pass = "01017883280Mm";
	public static String query = "";

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection con = DriverManager.getConnection(url, user, pass);
		return con;
	}

	// add Doctor
	public int DoctorSignUp(Doctor doctor) {
		query = "insert into Doctor values(?,?,?,?,?,?,?,?,?)";
		int status = 0;
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, doctor.getUserName());
			stmt.setString(2, doctor.getPassword());
			stmt.setString(3, doctor.getName());
			stmt.setBoolean(4, doctor.isGender());
			stmt.setString(5, doctor.getAddress());
			stmt.setString(6, doctor.getPhone());
			stmt.setString(7, doctor.getEmail());
			stmt.setDate(8, doctor.getDateOfBirth());
			stmt.setString(9,doctor.getImage());
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
			{
				status = 1;
				break;

			}
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
				doctor.setName(res.getString("name"));
				doctor.setGender(res.getBoolean("gender"));
				doctor.setAddress(res.getString("address"));
				doctor.setPhone(res.getString("phone"));
				doctor.setEmail(res.getString("email"));
				doctor.setDateOfBirth(res.getDate("dateOfBirth"));
				doctor.setImage(res.getString("image"));
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
				doctor.setName(res.getString("name"));
				doctor.setGender(res.getBoolean("gender"));
				doctor.setAddress(res.getString("address"));
				doctor.setPhone(res.getString("phone"));
				doctor.setEmail(res.getString("email"));
				doctor.setDateOfBirth(res.getDate("dateOfBirth"));
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
				doctor.setName(res.getString("name"));
				doctor.setGender(res.getBoolean("gender"));
				doctor.setAddress(res.getString("address"));
				doctor.setPhone(res.getString("phone"));
				doctor.setEmail(res.getString("email"));
				doctor.setDateOfBirth(res.getDate("dateOfBirth"));
				doctor.setImage(res.getString("image"));

			}

		} catch (Exception e) {
			System.out.println(e + "Failed");
		}
		return doctor;
	}
	public  boolean ifEmailExist(String email)
	{
		ArrayList<Doctor> doctors = DBLoad(); // calling function get All Data
		for(Doctor d:doctors)
		{
			if(d.getEmail().equals(email))
			{
				return true;
			}
		}
		return false;
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

	// Update Patient Data
		public AccountResponse updateDoctorProfile(Doctor doctor) {
			AccountResponse response=new AccountResponse();
			query = "UPDATE Doctor\r\n" + "SET userName=?,image=?,name=?,gender=?,address=?,phone=?,email=?,dateOfBirth=?,where id=?";
			try {
				Connection con = getConnection();
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setString(1, doctor.getUserName());
				stmt.setString(2, doctor.getImage());
				stmt.setString(3, doctor.getName());
				stmt.setBoolean(4, doctor.isGender());
				stmt.setString(5, doctor.getAddress());
				stmt.setString(6, doctor.getPhone());
				stmt.setString(7, doctor.getEmail());
				stmt.setDate(8, doctor.getDateOfBirth());
				stmt.setLong(9, doctor.getId());
				stmt.executeUpdate();
				response.setResult(1);
			} catch (Exception e) {
				System.out.println(e + "Failed");
				response.setResult(-1);
			}
			return response;
		}


		public  boolean ifPasswordExist(String password) {
			ArrayList<Doctor> doctors = DBLoad(); // calling function get All Data
			for (Doctor d : doctors) {
				if (d.getPassword().equals(password)) {
					return true;
				}
			}
			return false;
		}
		public AccountResponse updatePasswordByPassword(String oldPass,String newPass,long id )
		{
			AccountResponse response=new AccountResponse();
			boolean f=ifPasswordExist(oldPass);
			if(f) {
				query = "update  Doctor set password= ? where id= ?";
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
			
			else  response.setResult(-2);
			
			return response;
			
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
	public int updateDoctorPasswordByEmail(String password, String email) {
		query = "update  Doctor set password= ? where email= ?";
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

	// check Doctor found in DB or not
	public LoginResponse DoctorLogin(String userName, String password) throws SQLException {
		ArrayList<Doctor> doctors = DBLoad(); // calling function get All Data
		LoginResponse loginResponse=new LoginResponse();
		for (Doctor d : doctors) {
			if (d.getUserName().equals(userName)) {
				if (d.getPassword().equals(password)) {
					loginResponse.setId(d.getId()); // user found
					loginResponse.setResult(1);
					break;
				} else {
					loginResponse.setResult(-1);// password wrong
					break;
				}
			} else {
				loginResponse.setResult(-2); //user Not Found
			}
		}
		return loginResponse;
	}

}
