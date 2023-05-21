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

import org.ZCare.DTO.Patient;
import org.ZCare.Services.PatientService;

@Path("/ZCare")
public class PatientResource {
	PatientService patientService = new PatientService();

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
				return -1; //not added
		}
		else 
			return 0; // Duplicated Name
		

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
	@Path("/updatePatientDataById/{address}/{phone}/{careGiver_id}/{doctor_id}/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public int updatePatientData(@PathParam("address") String address, @PathParam("phone") String phone,
			@PathParam("careGiver_id") long careGiver_id, @PathParam("doctor_id") long doctor_id,
			@PathParam("id") long id) {
		if (patientService.updatePatientData(address, phone, careGiver_id, doctor_id, id) == 1)
			return 1;
		else
			return -1;
	}

	// update patient Password
	@Path("/updatePatientPasswordByUserName/{password}/{userName}")
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public int updatePatientPassword(@PathParam("password") String password, @PathParam("userName") String userName) {
		if (patientService.updatePatientPasswordByUserName(password, userName) == 1)
			return 1;
		else
			return -1;
	}

	// update patient userName
	@Path("/updatePatientUserNameByEmail/{userName}/{email}")
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public int updatePatientUserName(@PathParam("userName") String userName, @PathParam("email") String email) {
		if (patientService.updatePatientUserNameByEmail(userName, email) == 1)
			return 1;
		else
			return -1;
	}

	@Path("/patientLogin/{userName}/{password}")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public long PatientLogin(@PathParam("userName") String userName, @PathParam("password") String password)
			throws SQLException {
		return patientService.PatientLogin(userName, password);
	}

}
