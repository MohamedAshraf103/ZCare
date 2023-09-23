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
import org.ZCare.DTO.PatientPlan;
import org.ZCare.Services.PatientPlanService;

@Path("/ZCare")
public class PatientPlanResource {
	PatientPlanService patientPlanService = new PatientPlanService();

	// get All PatientNotes
	@Path("/getPatientPlansByPatientId/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Collection<PatientPlan> getAllPatientPlans(@PathParam("id") long id) {
		Collection<PatientPlan> patientPlan = patientPlanService.getAllPatientPlan(id);
		if (!patientPlan.isEmpty())
			return patientPlan;
		else
			return null;
	}
	

	// Add for New PatientNote
	// return 1 if added successfully and -1 if failed
	@Path("/addPatientPlan")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public AccountResponse addPatientPlan(PatientPlan patientPlan) {
		AccountResponse response=new AccountResponse();
		response=patientPlanService.addPatientPlan(patientPlan);
		return response;
	}
	
	@Path("/deletePatientPlanById/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@DELETE
	public AccountResponse deletePatient(@PathParam("id") int id) {
		AccountResponse response=new AccountResponse();
		response =patientPlanService.deletePatientPlan(id) ;
		return response;
	}

}
