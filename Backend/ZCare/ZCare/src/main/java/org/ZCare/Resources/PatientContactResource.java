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
import org.ZCare.DTO.PatientContact;
import org.ZCare.Services.PatientContactService;

@Path("/ZCare")
public class PatientContactResource {
	PatientContactService patientContactService = new PatientContactService();
	// get All PatientNotes
		@Path("/getPatientContactsByPatientId/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		@GET
		public Collection<PatientContact> getAllPatientRoutines(@PathParam("id") long id) {
			Collection<PatientContact> patientRoutine = patientContactService.getAllPatientContact(id);
			if (!patientRoutine.isEmpty())
				return patientRoutine;
			else
				return null;
		}
		
		
		// Add for New PatientContact
		// return 1 if added successfully and -1 if failed
		@Path("/addPatientContact")
		@Consumes(MediaType.APPLICATION_JSON)
		@POST
		public AccountResponse addPatientRoutine(PatientContact patientContact) {
			AccountResponse response=new AccountResponse();
			response=patientContactService.addPatientContact(patientContact);
			return response;
		}
		
		@Path("/deletePatientContactById/{id}")
		@Consumes(MediaType.APPLICATION_JSON)
		@DELETE
		public AccountResponse deletePatient(@PathParam("id") long id) {
			AccountResponse response=new AccountResponse();
			response=patientContactService.deletePatientContact(id);
			return response;
		}

}
