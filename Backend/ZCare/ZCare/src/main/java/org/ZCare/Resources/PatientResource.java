package org.ZCare.Resources;

import java.util.Collection;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ZCare.DTO.AccountResponse;
import org.ZCare.DTO.Login;
import org.ZCare.DTO.LoginResponse;
import org.ZCare.DTO.Mail;
import org.ZCare.DTO.Patient;
import org.ZCare.DTO.ResetPassword;
import org.ZCare.DTO.UpdatePasswordUserName;
import org.ZCare.Services.EmailService;
import org.ZCare.Services.EmailServiceImpl;
import org.ZCare.Services.EmailServiceImplReminder;
import org.ZCare.Services.PatientService;
import org.ZCare.util.OTPCode;

@Path("/ZCare")
public class PatientResource {
	PatientService patientService = new PatientService();
	private EmailService emailService=new EmailServiceImpl();
	private EmailService emailService2=new EmailServiceImplReminder();


	// get All Patients
	@Path("/getAllPatients")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Collection<Patient> getAllPatients() {
		Collection<Patient> patients = patientService.getAllPatients();
		if (!patients.isEmpty())
			return patients;
		else
			return null;
	}

	// get Patient By ID
	@Path("/getPatientById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Patient getPatientById(@PathParam("id") long id) {
		Patient patient=patientService.getPatientById(id);
		return patient;
		//if(patient!=null)
			//return patient;
	
		//else return null;
		
	}
	@Path("/patientSignUpp")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public int patientSignUpp(Patient patient) {
		long res=patientService.checkName(patient.getUserName());
		if(res==0)
		{
			boolean x=patientService.ifEmailExist(patient.getEmail());
			if(!x)
			{
				if (patientService.PatientSignUp(patient) == 1)
					return 1;//added successfully
				else
					return 0; //not added
			}
			else return -2; // duplicate mail
		}
		
		else 
			return -1; // Duplicated Name
		

	}

	// SignUp for New Patient
	// return 1 if added successfully and -1 if failed
	@Path("/patientSignUp")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public int patientSignUp(Patient patient) {
		long res=patientService.checkName(patient.getUserName());
		if(res==0)
		{
			if (patientService.PatientSignUp(patient) == 1)
				return 1;//added successfully
			else
				return 0; //not added
		}
		else 
			return -1; // Duplicated Name
		
		

	}

	// delete specific Patient By Id
	// return 1 if deleted successfully and -1 if failed
	@Path("/deletePatientById/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@DELETE
	public int deletePatient(@PathParam("id") long id) {
		if (patientService.deletePatient(id) == 1)
			return 1;
		else
			return -1;
	}

	// update patient Data
	@Path("/updatePatientProfile")
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public AccountResponse updatePatientProfile(Patient patient) {
		AccountResponse response=new AccountResponse();
		response=patientService.updatePatientProfile(patient);
		return response;
	}
	
	@Path("/patientOTP")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public AccountResponse patientOTP(ResetPassword resetPassword)
	{
		boolean found=patientService.ifEmailExist(resetPassword.getEmail());
		AccountResponse accountResponse=new AccountResponse();
		if(found)
		{
			String code=OTPCode.getCode();
			Mail mail=new Mail(resetPassword.getEmail(),code);
			emailService.sendCodeByEmail(mail);
			accountResponse.setResult(1);
			accountResponse.setCode(code);
		}
		else {
			accountResponse.setResult(0);
		}
		return accountResponse;
	}
	
	

	@Path("/patientOTPReminder")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public AccountResponse patientOTPReminder(ResetPassword resetPassword)
	{
		boolean found=patientService.ifEmailExist(resetPassword.getEmail());
		AccountResponse accountResponse=new AccountResponse();
		
		if(found)
		{
			//String code=OTPCode.getCode();
			Mail mail=new Mail();
			mail.setTo(resetPassword.getEmail());
			emailService2.sendCodeByEmail(mail);
			accountResponse.setResult(1);
		}
		else {
			accountResponse.setResult(0);
		}
		return accountResponse;
	}

	
	// update patient Password
	@Path("/updatePatientPasswordByEmail")
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public int updatePatientPassword(UpdatePasswordUserName updatePassword) {
		if (patientService.updatePatientPasswordByEmail(updatePassword.getPassword(), updatePassword.getEmail())== 1)
			return 1;
		else
			return -1;
	}
	
	@Path("/updatePatientPasswordByPassword")
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public AccountResponse updatePasswordByPassword(UpdatePasswordUserName updatePassword )
	{
		AccountResponse response=new AccountResponse();
		response=patientService.updatePasswordByPassword(updatePassword.getPassword(), 
				updatePassword.getNewPassword(),
				 updatePassword.getId());
		return response;
		
		
	}
	
	// update patient userName
	@Path("/updatePatientUserNameByEmail")
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public int updatePatientUserName(UpdatePasswordUserName updatePasswordUserName) {
		if (patientService.updatePatientUserNameByEmail(updatePasswordUserName.getUserName(), updatePasswordUserName.getEmail()) == 1)
			return 1;
		else
			return -1;
	}

	@Path("/patientLogin")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public LoginResponse PatientLogin(Login login)
			throws SQLException {
		return patientService.PatientLogin(login.getUserName(),login.getPassword());
	}

}
