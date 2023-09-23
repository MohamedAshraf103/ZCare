package org.ZCare.Resources;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.ZCare.DTO.Admin;
import org.ZCare.DTO.MLModel;
import org.ZCare.Services.AdminService;
public class AdminResource {
	AdminService adminService=new AdminService();
	
	@Path("/AdminSignUp")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public int AdminSignUp(Admin admin) {
		long res=adminService.checkName(admin.getUserName());
		if(res==0)
		{
			if (adminService.AdminSignUp(admin) == 1)
				return 1;//added successfully
			else
				return -1; //not added
		}
		else 
			return 0; // Duplicated Name
	}
	
	@Path("/addModel")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public int addModel(MLModel mlModel) {
			if (adminService.addMLModel(mlModel) == 1)
				return 1;//added successfully
			else
				return -1; //not add
	}
	// update Model Path
		@Path("/updateModelPath/{folderPath}/{id}")
		@Consumes(MediaType.APPLICATION_JSON)
		@PUT
		public int updateModelPath(@PathParam("folderPath") String folderPath, @PathParam("id") long id) {
			if (adminService.updateMLModelPath(folderPath, id) == 1)
				return 1;
			else
				return -1;
		}
	@Path("/AdminLogin/{userName}/{password}")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public long AdminLogin(@PathParam("userName") String userName, @PathParam("password") String password)
			throws SQLException {
		return adminService.AdminLogin(userName, password);
	}
	

}
