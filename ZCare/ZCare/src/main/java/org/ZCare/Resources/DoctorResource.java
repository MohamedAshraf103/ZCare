package org.ZCare.Resources;

import org.ZCare.DTO.Doctor;
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
			if (doctorService.DoctorSignUp(doctor) == 1)
				return 1; // added Successfully
			else
				return -1; // not added
		} else
			return 0; // repeated name
	}

	// update Doctor Data By ID
	@Path("/updateDoctorDataById/{description}/{rate}/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public int updateDoctorData(@PathParam("description") String description, @PathParam("rate") String rate,
			@PathParam("id") long id) {
		if (doctorService.updateDoctorDataById(description, rate, id) == 1)
			return 1;
		else
			return -1;
	}

	// update Doctor userName By Email
	@Path("/updateDoctorUserNameByEmail/{userName}/{email}")
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public int updateDoctorUserName(@PathParam("userName") String userName, @PathParam("email") String email) {
		if (doctorService.updateDoctorUserNameByEmail(userName, email) == 1)
			return 1;
		else
			return -1;
	}

	// Update Doctor Password By UserName
	@Path("/updateDoctorPasswordByUserName/{password}/{userName}")
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public int updateDoctorPassword(@PathParam("password") String password, @PathParam("userName") String userName) {
		if (doctorService.updateDoctorPasswordByUserName(userName, password) == 1)
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

	@Path("/doctorLogin/{userName}/{password}")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public long doctorLogin(@PathParam("userName") String userName, @PathParam("password") String password)
			throws SQLException {
		return doctorService.DoctorLogin(userName, password);
	}

}
