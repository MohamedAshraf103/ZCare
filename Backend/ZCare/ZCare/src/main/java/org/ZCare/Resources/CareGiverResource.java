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
import org.ZCare.Services.EmailService;
import org.ZCare.Services.EmailServiceImpl;
import org.ZCare.util.OTPCode;
import org.ZCare.DTO.AccountResponse;
import org.ZCare.DTO.CareGiverCheck;
import org.ZCare.DTO.CareGiver;
import org.ZCare.DTO.Login;
import org.ZCare.DTO.Mail;
import org.ZCare.DTO.LoginResponse;
import org.ZCare.DTO.ResetPassword;
import org.ZCare.DTO.UpdatePasswordUserName;
import org.ZCare.DTO.Patient;
@Path("/ZCare")
public class CareGiverResource {

	public CareGiverResource() {
	}
	private PatientResource patientResource=new PatientResource();
	private CareGiverService careGiverService = new CareGiverService();
	private EmailService emailService=new EmailServiceImpl();



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
				if (careGiverService.careGiverSignUp(careGiver) == 1)
					return 1;//added successfully
				else
					return 0; //not added
	}
	
	@Path("/careGiverCheck")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public AccountResponse checkCareGiver(CareGiverCheck careGiverCheck) {
		AccountResponse accountResponse=new AccountResponse();
		long res=careGiverService.checkName(careGiverCheck.getUserName());
		if(res==0)
		{
			boolean x=careGiverService.ifEmailExist(careGiverCheck.getEmail());
			if(!x)
			{
				Patient patient=new Patient();
				patient=patientResource.getPatientById(careGiverCheck.getPatientId());
				if(patient!=null)
				{
					ResetPassword resetPassword=new ResetPassword();
					resetPassword.setEmail(patient.getEmail());
					 accountResponse=patientResource.patientOTP(resetPassword);
				}
				else accountResponse.setResult(0);
				
			}
			else accountResponse.setResult(-2);
		}
		else {
			accountResponse.setResult(-1);
		}
		return accountResponse;
	}
	
	
	@Path("/checkPatientCareGiver/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public AccountResponse checkPatientCareGiver(@PathParam("id")int id)
	{
		AccountResponse accountResponse=new AccountResponse();
		accountResponse=careGiverService.checkPatientCareGiver(id);
		return accountResponse;
	}
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
	
	@Path("/careGiverOTP")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public AccountResponse careGiverOTP(ResetPassword resetPassword)
	{
		boolean found=careGiverService.ifEmailExist(resetPassword.getEmail());
		AccountResponse accountResponse=new AccountResponse();
		
		if(found)
		{
			String code=OTPCode.getCode();
			Mail mail=new Mail(resetPassword.getEmail(),code);
			emailService.sendCodeByEmail(mail);
			accountResponse.setCode(code);
			accountResponse.setResult(1);
		}
		else {
			accountResponse.setResult(0);
		}
		return accountResponse;
	}
	
	
	// update patient Data
		@Path("/updateCareGiverProfile")
		@Consumes(MediaType.APPLICATION_JSON)
		@PUT
		public AccountResponse updateCareGiverProfile(CareGiver careGiver) {
			AccountResponse response=new AccountResponse();
			response =careGiverService.updateCareGiverProfile(careGiver);
			return response;
		}
	//update CareGiver userName
		@Path("/updateCareGiverUserNameByEmail")
		@Consumes(MediaType.APPLICATION_JSON)
		@PUT
		public int updateCaregiverUserName(UpdatePasswordUserName updatePasswordUserName) {
			if (careGiverService.updateCareGiverUserNameByEmail(updatePasswordUserName.getUserName(),updatePasswordUserName.getEmail()) == 1)
				return 1;
			else
				return -1;
		}
	
	@Path("/updateCareGiverPasswordByUserName")
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public int updateCareGiverPassword(UpdatePasswordUserName updatePasswordUserName) {
		if (careGiverService.updateCareGiverPasswordByEmail(updatePasswordUserName.getPassword(),updatePasswordUserName.getEmail()) == 1)
			return 1;
		else
			return -1;
	}
	
	@Path("/updateCareGiverPasswordByPassword")
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public AccountResponse updatePasswordByPassword(UpdatePasswordUserName updatePassword )
	{
		AccountResponse response=new AccountResponse();
		response=careGiverService.updatePasswordByPassword(updatePassword.getPassword(), 
				updatePassword.getNewPassword(),
				 updatePassword.getId());
		return response;
		
		
	}
	
	@Path("/careGiverLogin")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public LoginResponse careGiverLogin(Login login) throws SQLException {
		
		return careGiverService.careGiverLogin(login.getUserName(),login.getPassword());		
	}

	

}
