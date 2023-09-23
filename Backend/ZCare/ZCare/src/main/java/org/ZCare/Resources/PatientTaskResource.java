package org.ZCare.Resources;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.ZCare.DTO.PatientTask;
import org.ZCare.Services.PatientTaskService;
import org.ZCare.DTO.AccountResponse;

@Path("/ZCare")
public class PatientTaskResource {
	PatientTaskService patientTaskService = new PatientTaskService();

	// get All PatientRoutines
	@Path("/getPatientRoutinesByRoutineId/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Collection<PatientTask> getAllPatientNotes(@PathParam("id") long id) {
		Collection<PatientTask> patientTask = patientTaskService.getAllPatientTaskRoutine(id);
		if (!patientTask.isEmpty())
			return patientTask;
		else
			return null;
	}

	// get All PatientPlans
	@Path("/getPatientPlanesByPlanId/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Collection<PatientTask> getAllPatientPlans(@PathParam("id") long id) {
		Collection<PatientTask> patientTask = patientTaskService.getAllPatientTaskPlans(id);
		if (!patientTask.isEmpty())
			return patientTask;
		else
			return null;
	}

	// Add for New PatientNote
	// return 1 if added successfully and -1 if failed
	@Path("/addPatientTask")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public AccountResponse addPatientNote(PatientTask patientNote) {
		AccountResponse response = new AccountResponse();
		response = patientTaskService.addPatientTask(patientNote);
		return response;
	}

	@Path("/addPatientRoutineTaskList")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public AccountResponse addPatientRoutineTaskList(List<PatientTask> list) {
		AccountResponse response = new AccountResponse();
		response = patientTaskService.addPatientRoutineTaskList(list);
		return response;
	}

	@Path("/addPatientPlanTaskList")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public AccountResponse addPatientPlanTaskList(List<PatientTask> list) {
		AccountResponse response = new AccountResponse();
		response = patientTaskService.addPatientPlanTaskList(list);
		return response;
	}

	// update patientNote
	@Path("/updatePatientTaskRoutine")
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public AccountResponse updatePatientTask(PatientTask patientTask) {
		AccountResponse response = new AccountResponse();
		response=patientTaskService.updatePatientTaskRoutine(patientTask);
		return response;
	}
	
	@Path("/updatePatientTaskPlan")
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public AccountResponse updatePatientTaskPlan(PatientTask patientTask) {
		AccountResponse response = new AccountResponse();
		response=patientTaskService.updatePatientTaskPlan(patientTask);
		return response;
	}

	// delete specific PatientNote By ID
	// return 1 if deleted successfully and -1 if failed
	@Path("/deletePatientTaskRoutineById/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@DELETE
	public AccountResponse deletePatientTaskRoutine(@PathParam("id") long id) {
		AccountResponse response = new AccountResponse();
		response = patientTaskService.deletePatientTaskRoutine(id);
		return response;
	}

	@Path("/deletePatientTaskPlanById/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@DELETE
	public AccountResponse deletePatientTaskPlan(@PathParam("id") long id) {
		AccountResponse response = new AccountResponse();
		response = patientTaskService.deletePatientTaskPlan(id);
		return response;
	}

}
