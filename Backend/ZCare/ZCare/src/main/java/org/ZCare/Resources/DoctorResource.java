package org.ZCare.Resources;

import org.ZCare.Services.EmailServiceImpl;
import org.ZCare.util.OTPCode;
import org.ZCare.Services.EmailService;
import org.ZCare.DTO.Mail;
import org.ZCare.DTO.AccountResponse;
import org.ZCare.DTO.Doctor;
import org.ZCare.DTO.Login;
import org.ZCare.DTO.LoginResponse;
import org.ZCare.DTO.ResetPassword;
import org.ZCare.DTO.UpdatePasswordUserName;
import org.ZCare.Services.DoctorService;
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
import java.util.Collection;

@Path("/ZCare")
public class DoctorResource {
	private EmailService emailService = new EmailServiceImpl();
	DoctorService doctorService = new DoctorService();
	// get All Doctors

	@Path("/getAllDoctors")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Collection<Doctor> getAllDoctors() {
		Collection<Doctor> doctors = doctorService.getAllDoctors();
		if (!doctors.isEmpty())
			return doctors;
		else
			return null;
	}

	// get Doctor By ID
	@Path("/getDoctorById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Doctor getDoctorById(@PathParam("id") long id) {
		Doctor doc = doctorService.getDoctorById(id);
		if (doc != null)
			return doc;
		else
			return null;
	}

	// add new Doctor
	@Path("/doctorSignUp")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public int doctorSignUp(Doctor doctor) {
		long res = doctorService.checkName(doctor.getUserName());
		if (res == 0) {
			boolean x = doctorService.ifEmailExist(doctor.getEmail());
			if (!x) {
				if (doctorService.DoctorSignUp(doctor) == 1)
					return 1;// added successfully
				else
					return 0; // not added
			} else
				return -2; // duplicate mail
		}

		else
			return -1; // Duplicated Name
	}

	// update Doctor Data By ID
	@Path("/updateDoctorProfile")
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public AccountResponse updateDoctorProfile(Doctor doctor) {
		AccountResponse response = new AccountResponse();
		response = doctorService.updateDoctorProfile(doctor);
		return response;
	}

	@Path("/updateDoctorPasswordByPassword")
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public AccountResponse updatePasswordByPassword(UpdatePasswordUserName updatePassword) {
		AccountResponse response = new AccountResponse();
		response = doctorService.updatePasswordByPassword(updatePassword.getPassword(), updatePassword.getNewPassword(),
				updatePassword.getId());
		return response;

	}

	// update Doctor userName By Email
	@Path("/updateDoctorUserNameByEmail")
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public int updateDoctorUserName(UpdatePasswordUserName updatePasswordUserName) {
		if (doctorService.updateDoctorUserNameByEmail(updatePasswordUserName.getUserName(),
				updatePasswordUserName.getEmail()) == 1)
			return 1;
		else
			return -1;
	}

	// Update Doctor Password By UserName
	@Path("/updateDoctorPasswordByEmail")
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public int updateDoctorPassword(UpdatePasswordUserName updatePasswordUserName) {
		if (doctorService.updateDoctorPasswordByEmail(updatePasswordUserName.getPassword(),
				updatePasswordUserName.getEmail()) == 1)
			return 1;
		else
			return -1;
	}

	// Delete Doctor By ID
	@Path("/deleteDoctorById/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@DELETE
	public int deleteDoctor(@PathParam("id") long id) {
		if (doctorService.deleteDoctor(id) == 1)
			return 1;
		else
			return -1;
	}

	@Path("/doctorOTP")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public AccountResponse doctorOTP(ResetPassword resetPassword) {
		boolean found = doctorService.ifEmailExist(resetPassword.getEmail());
		AccountResponse accountResponse = new AccountResponse();

		if (found) {
			String code = OTPCode.getCode();
			Mail mail = new Mail(resetPassword.getEmail(), code);
			emailService.sendCodeByEmail(mail);
			accountResponse.setResult(1);
			accountResponse.setCode(code);
		} else {
			accountResponse.setResult(0);
		}
		return accountResponse;
	}

	@Path("/doctorLogin")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public LoginResponse doctorLogin(Login login) throws SQLException {
		return doctorService.DoctorLogin(login.getUserName(), login.getPassword());
	}

}
