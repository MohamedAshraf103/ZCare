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
	public int addPatientMedicine(PatientMedicine patientMedicine) {
		int res=patientMedicineService.addPatientMedicine(patientMedicine);
		if ( res== 1)
			return 1;
		else
			return -1;
	}
	
	//update patientMedicine Time 
	@Path("/updatepatientMedicineById/{imagePath}/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public int updatePatientMedicine(@PathParam("imagePath")String imagePath,
			@PathParam("id")long id) {
		if (patientMedicineService.updatePatientMedicineTimeById(imagePath,id) == 1)
			return 1;
		else
			return -1;
	}

	// deletePatientMedicine
	// return 1 if deleted successfully else return -1
	@Path("/deletePatientMedicineById/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@DELETE
	public int deletePatientMedicine(@PathParam("id") long id) {
		if (patientMedicineService.deletePatientMedicine(id) == 1)
			return 1;
		else
			return -1;
	}

}
