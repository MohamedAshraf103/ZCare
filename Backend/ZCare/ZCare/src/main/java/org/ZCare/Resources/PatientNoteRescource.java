package org.ZCare.Resources;

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

import org.ZCare.DTO.AccountResponse;
import org.ZCare.DTO.PatientNote;
import org.ZCare.Services.PatientNoteService;

@Path("/ZCare")
public class PatientNoteRescource {
	PatientNoteService patientNoteService = new PatientNoteService();
	
	@Path("/getPatientNoteByPatientId/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Collection<PatientNote> getPatientNoteByPatientId(@PathParam("id") long id) {
		Collection<PatientNote> patientNoteList=patientNoteService.getPatientNoteById(id);
		if(!patientNoteList.isEmpty())
			return patientNoteList;
		else return null;
	}

	
	// add Patient Medicine
		// return 1 if saved successfully else return -1
		@Path("/addPatientNote")
		@Consumes(MediaType.APPLICATION_JSON)
		@POST
		public AccountResponse addPatientNote(PatientNote patientNote) {
			AccountResponse response=new AccountResponse();
			response=patientNoteService.addPatientNote(patientNote);
			return response;
		}
		
		// deletePatientMedicine
		// return 1 if deleted successfully else return -1
		@Path("/deletePatientNoteById/{id}")
		@Consumes(MediaType.APPLICATION_JSON)
		@DELETE
		public AccountResponse deletePatientNote(@PathParam("id") int id) {
			AccountResponse response=new AccountResponse();
			response=patientNoteService.deletePatientNote(id);
			return response;
		}
		
		@Path("/updatePatientNoteById")
		@Consumes(MediaType.APPLICATION_JSON)
		@PUT
		public AccountResponse updatePatientNote(PatientNote patientNote)
		{
			AccountResponse response=new AccountResponse();
			response=patientNoteService.updatePatientNote(patientNote);
			return response;
		}

}
