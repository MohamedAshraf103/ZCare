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
import org.ZCare.DTO.PatientNote;
import org.ZCare.Services.PatientNoteService;

@Path("/ZCare")
public class PatientNoteResource {
	PatientNoteService patientNoteService = new PatientNoteService();

	// get All PatientNotes
	@Path("/getPatientNotesByPatientId/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Collection<PatientNote> getAllPatientNotes(@PathParam("id") long id) {
		Collection<PatientNote> patientNote=patientNoteService.getAllPatientNote(id);
		if(!patientNote.isEmpty())
			return patientNote;
		else return null;
	}

	// Add for New PatientNote
	// return 1 if added successfully and -1 if failed
	@Path("/addPatientNote")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public int addPatientNote(PatientNote patientNote) {
		if (patientNoteService.addPatientNote(patientNote) == 1)
			return 1;
		else
			return -1;
	}
	
			//update patientNote 
			@Path("/updatePatientNote")
			@Consumes(MediaType.APPLICATION_JSON)
			@POST
			public int updatePatientNote(PatientNote patientNote)
					 {
						int res=patientNoteService.updatePatientNoteData(patientNote);
						if (res== 1)
							return 1;
						else
							return -1;
			}

	// delete specific PatientNote By ID
	// return 1 if deleted successfully and -1 if failed
	@Path("/deletePatientNoteById/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@DELETE
	public int deletePatientNote(@PathParam("id") long id) {
		if (patientNoteService.deletePatientNote(id) == 1)
			return 1;
		else
			return -1;
	}

}
