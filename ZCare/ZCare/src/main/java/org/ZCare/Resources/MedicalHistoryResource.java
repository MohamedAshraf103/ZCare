package org.ZCare.Resources;

import org.ZCare.DTO.MedicalHistory;
import org.ZCare.Services.MedicalHistoryService;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

import javax.ws.rs.*;

@Path("/ZCare")
public class MedicalHistoryResource {
	MedicalHistoryService medicalHistoryService = new MedicalHistoryService();

	// get Specific Patient Medicine
	@Path("/getPatientMedicalHistoryByPatientId/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Collection<MedicalHistory> getPatientMedicalHistoryByPatientId(@PathParam("id") long id) {
		Collection <MedicalHistory> medicalHistory=medicalHistoryService
				.getPatientMedicalHistoryByPatientId(id);
		if(!medicalHistory.isEmpty())
			return medicalHistory;
		else return null;
	}

	@Path("/addMedicalHistory")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public int addMedicalHistory(MedicalHistory medicalHistory) {
		if (medicalHistoryService.addMedicalHistory(medicalHistory) == 1)
			return 1;
		else
			return -1;

	}
	
	//update patient MedicalHistory
			@Path("/updateMedicalHistoryById/{imagePath}/{genomePath}/{id}")
			@Consumes(MediaType.APPLICATION_JSON)
			@PUT
			public int updatePatientMedicalHistory(@PathParam("imagePath")String imagePath,
					@PathParam("genomePath")String genomePath,@PathParam("id")long id) {
				if (medicalHistoryService.updateMedicalHistoryById(imagePath,genomePath,id) == 1)
					return 1;
				else
					return -1;
			}

	// deletePatientMedicine
	// return 1 if deleted successfully else return -1
	@Path("/deletePatientMedicalHistoryById/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@DELETE
	public int deletePatientMedicalHistoryById(@PathParam("id") long id) {
		if (medicalHistoryService.deletePatientMedicalHistory(id) == 1)
			return 1;
		else
			return -1;
	}

}
