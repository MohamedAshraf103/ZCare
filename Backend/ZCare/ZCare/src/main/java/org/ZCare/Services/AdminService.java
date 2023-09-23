package org.ZCare.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.ZCare.DTO.Admin;
import org.ZCare.DTO.MLModel;
public class AdminService {
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
	public int AdminSignUp(Admin admin) {
		int status = 0;
		query = "insert into Admin values(?,?,?)";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, admin.getUserName());
			stmt.setString(2, admin.getPassword());
			stmt.setString(3,admin.getName());
			status = stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e + "Failed");
			return status = 0;
		}
		return status;
	}
	public long checkName(String userName)
	{
		int status=0;
		ArrayList<Admin> list = DBLoad();
		for(Admin d:list)
			if(d.getUserName().equals(userName))
				status=1;
			else 
				status=0;
		return status;
		
		
	}
	
	public int addMLModel(MLModel model) {
		int status = 0;
		query = "insert into MLModel values(?,?)";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, model.getType());
			stmt.setString(2, model.getFolderPath());
			status = stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e + "Failed");
			return status = 0;
		}
		return status;
	}
	public int updateMLModelPath(String path,long id) {
		int status = 0;
		query = "update MLModel set folderPath=? where id=?";
		try {
			Connection con = getConnection();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1,path);
			stmt.setLong(2,id);
			status = stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e + "Failed");
			return status = 0;
		}
		return status;
	}
	
	// Get All Admins
		public ArrayList<Admin> DBLoad() {
			ArrayList<Admin> list = new ArrayList<Admin>();
			query = "select * from Admin";
			try {
				Connection con = getConnection();
				PreparedStatement stmt = con.prepareStatement(query);
				ResultSet res = stmt.executeQuery();
				while (res.next()) {
					Admin admin = new Admin();
					admin.setId(res.getLong("id"));
					admin.setPassword(res.getString("password"));
					admin.setUserName(res.getString("userName"));
					list.add(admin);
				}

			} catch (Exception e) {
				System.out.println(e + "Failed");
			}
			return list;
		}
		
		
		public long AdminLogin(String userName, String password) throws SQLException {
			ArrayList<Admin> list = DBLoad(); // calling function get All Data
			long x = 0;

			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getUserName().equals(userName)) {
					if (list.get(i).getPassword().equals(password)) {
						x+=list.get(i).getId();
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
