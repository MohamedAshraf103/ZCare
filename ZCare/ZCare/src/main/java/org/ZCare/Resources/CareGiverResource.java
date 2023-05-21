package org.ZCare.Resources;

import java.sql.SQLException;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.ZCare.Services.CareGiverService;
import org.ZCare.DTO.CareGiver;

@Path("/ZCare")
public class CareGiverResource {

	public CareGiverResource() {
	}

	private CareGiverService careGiverService = new CareGiverService();

	@Path("/getAllCareGivers")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Collection<CareGiver> getAllCareGivers() {
		Collection<CareGiver> careGivers=careGiverService.getAllCareGivers();
		if(!careGivers.isEmpty())
			return careGivers;
		else return null;
	}

	@Path("/getCareGiverById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public CareGiver getCareGiverById(@PathParam("id") long id) {
		CareGiver careGiver=careGiverService.getCareGiverById(id);
		if(careGiver!=null)
			return careGiver;
		else return null;
	}

	////////////////////////////////
	// add new CareGiver return i if success else return -1
	@Path("/careGiverSignUp")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public int addCareGiver(CareGiver careGiver) {
		long res=careGiverService.checkName(careGiver.getUserName());
		if(res==0)
		{
			if (careGiverService.careGiverSignUp(careGiver)== 1)
				return 1; // added Successfully
			else
				return -1; //Not Added
		}
		else 
			return 0; // duplicated Name
	}
	
	

//	@Path("/addCareGiverPhone")
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	public int addCareGiverPhone(CareGiverPhone careGiverPhone) {
//		 int res=careGiverService.savePhone(careGiverPhone);
//		 return res;
//	}

	// deleteByID return 1 if successe else -1
	@Path("/deleteCareGiverById/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@DELETE
	public int deleteCareGiver(@PathParam("id") long id) {
		if (careGiverService.deleteCareGiver(id) == 1)
			return 1;
		else
			return -1;

	}
	//update CareGiver userName
	@Path("/updateCareGiverUserNameByEmail/{userName}/{email}")
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public int updateCaregiverUserName(@PathParam("userName")String userName,
			@PathParam("email")String email) {
		if (careGiverService.updateCareGiverUserNameByEmail(userName,email) == 1)
			return 1;
		else
			return -1;
	}
	
	@Path("/updateCareGiverPasswordByUserName/{password}/{userName}")
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public int updateCareGiverPassword(@PathParam("password")String password,
			@PathParam("userName")String userName) {
		if (careGiverService.updateCareGiverPasswordByUserName(password,userName) == 1)
			return 1;
		else
			return -1;
	}
	
	
	@Path("/careGiverLogin/{userName}/{password}")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public String careGiverLogin(@PathParam("userName")String userName,
			@PathParam("password")String password) throws SQLException {
		return careGiverService.careGiverLogin(userName, password);		
	}

	

}
