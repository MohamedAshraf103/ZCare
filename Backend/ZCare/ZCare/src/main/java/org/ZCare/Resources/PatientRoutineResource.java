package org.ZCare.Resources;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ZCare.DTO.AccountResponse;
import org.ZCare.DTO.PatientRoutine;
import org.ZCare.Services.PatientRoutineService;

@Path("/ZCare")
public class PatientRoutineResource {
	PatientRoutineService patientRoutineService = new PatientRoutineService();

	// get All PatientNotes
	@Path("/getPatientRoutinesByPatientId/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Collection<PatientRoutine> getAllPatientRoutines(@PathParam("id") long id) {
		Collection<PatientRoutine> patientRoutine = patientRoutineService.getAllPatientRoutine(id);
		if (!patientRoutine.isEmpty())
			return patientRoutine;
		else
			return null;
	}

	// Add for New PatientNote
	// return 1 if added successfully and -1 if failed
	@Path("/addPatientRoutine")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public AccountResponse addPatientRoutine(PatientRoutine patientRoutine) {
		AccountResponse response=new AccountResponse();
		response=patientRoutineService.addPatientRoutine(patientRoutine);
		return response;

	}
	
//	// update Patient Routine
//	@Path("/updatePatientRoutine")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@POST
//	public int updatePatientRoutine(PatientRoutine patientRoutine) {
//		int res = patientRoutineService.updatePatientRoutineData(patientRoutine);
//		if (res == 1)
//			return 1;
//		else
//			return -1;
//	}

	// delete specific PatientNote By ID
	// return 1 if deleted successfully and -1 if failed
	@Path("/deletePatientRoutineById/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@DELETE
	public AccountResponse deletePatient(@PathParam("id") long id) {
		AccountResponse response=new AccountResponse();
		response=patientRoutineService.deletePatientRoutine(id);
		return response;
	}

}
