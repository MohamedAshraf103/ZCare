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
import org.ZCare.DTO.Patient;
public class PatientService {
	public static String url = "jdbc:sqlserver://localhost\\DESKTOP-1KMPQD6:1433;databaseName=ZCare;;encrypt=true;trustServerCertificate=true";
	public static String user = "sa";
	public static String pass = "sa";
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
			stmt.setString(3, patient.getFirstName());
			stmt.setString(4, patient.getLastName());
			stmt.setBoolean(5, patient.isGender());
			stmt.setString(6, patient.getAddress());
			stmt.setString(7, patient.getPhone());
			stmt.setString(8, patient.getEmail());
			stmt.setDate(9, (Date) patient.getDateOfBirth());
			stmt.setString(10,null);
			status = stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e + "Failed");
			return status=0;
		}
		return status;
	}
	public long checkName(String userName)
	{
		int status=0;
		ArrayList<Patient> list = DBLoad();
		for(Patient p:list)
			if(p.getUserName().equals(userName))
				status=1;
			else 
				status=0;
		return status;
		
		
	}

    Map<Long, Patient>map=new HashMap<Long, Patient>();
	public  Collection<Patient> getAllPatients() {
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
				patient.setFirstName(res.getString("firstName"));
				patient.setLastName(res.getString("lastName"));
				patient.setGender(res.getBoolean("gender"));
				patient.setAddress(res.getString("address"));
				patient.setPhone(res.getString("phone"));
				patient.setDateOfBirth(res.getDate("dateOfBirth"));
				patient.setEmail(res.getString("email"));
				patient.setDoctorId(res.getLong("doctor_id"));
				map.put(patient.getId(), patient);
			}

		} catch (Exception e) {
			System.out.println(e + "Failed");
		}
		return map.values();
	}

	//return All Patients
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
				patient.setFirstName(res.getString("firstName"));
				patient.setLastName(res.getString("lastName"));
				patient.setGender(res.getBoolean("gender"));
				patient.setAddress(res.getString("address"));
				patient.setPhone(res.getString("phone"));
				patient.setDateOfBirth(res.getDate("dateOfBirth"));
				patient.setEmail(res.getString("email"));
				patient.setDoctorId(res.getLong("doctor_id"));
				list.add(patient);
			}

		} catch (Exception e) {
			System.out.println(e + "Failed");
		}
		return list;
	}
	//getPatientByID
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
				patient.setFirstName(res.getString("firstName"));
				patient.setLastName(res.getString("lastName"));
				patient.setGender(res.getBoolean("gender"));
				patient.setAddress(res.getString("address"));
				patient.setPhone(res.getString("phone"));
				patient.setDateOfBirth(res.getDate("dateOfBirth"));
				patient.setEmail(res.getString("email"));
				patient.setDoctorId(res.getLong("doctor_id"));
			}

		} catch (Exception e) {
			System.out.println(e + "Failed");
		}
		return patient;
	}
	//delete Patient By ID
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
		
			return status=0;
		}
		return status;
	}
		//Update Patient Data
	public int updatePatientData(String address,String phone,long careGiver_id,
			long doctor_id,long id) {
		query = "UPDATE Patient\r\n"
				+ "SET address =?, phone=?, careGiver_id =?, doctor_id =? where id=?";
		int status = 0;
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, address);
			stmt.setString(2,phone);
			stmt.setLong(3, careGiver_id);
			stmt.setLong(4, doctor_id);
			stmt.setLong(5, id);
			status = stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e + "Failed");
			return status=0;
		}
		return status;
	}
		//Update Patient Password By UserName
	public int updatePatientPasswordByUserName(String password,String userName) {
		query = "update  Patient set password= ? where userName= ?";
		int status = 0;
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, password);
			stmt.setString(2,userName);
			status = stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e + "Failed");
			return status=0;
		}
		return status;
	}
	
	//Update Patient UserName By Email
	public int updatePatientUserNameByEmail(String userName,String email) {
		query = "update  Patient set userName= ? where email= ?";
		int status = 0;
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, userName);
			stmt.setString(2,email);
			status = stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e + "Failed");
			return status=0;
		}
		return status;
	}
	
	
	// check Patient found in DB or not
	public  long PatientLogin(String userName,String password ) throws SQLException {
		ArrayList<Patient> list = DBLoad(); // calling function get All Data
		long x = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUserName().equals(userName)) {
				if (list.get(i).getPassword().equals(password)) {
					x = list.get(i).getId(); // user found
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
