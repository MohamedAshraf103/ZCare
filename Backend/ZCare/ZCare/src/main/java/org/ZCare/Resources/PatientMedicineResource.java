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
import org.ZCare.DTO.PatientMedicine;
import org.ZCare.Services.PatientMedicineService;
@Path("/ZCare")
public class PatientMedicineResource {
	PatientMedicineService patientMedicineService = new PatientMedicineService();

	// get Specific Patient Medicine
	@Path("/getPatientMedicineByPatientId/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Collection<PatientMedicine> getPatientMedicineByPatientId(@PathParam("id") long id) {
		Collection<PatientMedicine> patientMedicineList=patientMedicineService.getPatientMedicineById(id);
		if(!patientMedicineList.isEmpty())
			return patientMedicineList;
		else return null;
	}

	// add Patient Medicine
	// return 1 if saved successfully else return -1
	@Path("/addPatientMedicine")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public AccountResponse addPatientMedicine(PatientMedicine patientMedicine) {
		AccountResponse response=new AccountResponse();
		response=patientMedicineService.addPatientMedicine(patientMedicine);
		return response;
	}
	
	
	// deletePatientMedicine
	// return 1 if deleted successfully else return -1
	@Path("/deletePatientMedicineById/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@DELETE
	public AccountResponse deletePatientMedicine(@PathParam("id") long id) {
		AccountResponse response=new AccountResponse();
		response=patientMedicineService.deletePatientMedicine(id);
		return response;
	}

}
