package application.healthSoftware.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Visit implements Serializable {
	// Change whenever we DELETE a field or we CHANGE its type
	// Methods can be changed or deleted freely
	private static final long serialVersionUID = 1L;
		
	public String visitID;
	public String patientID;
	private String state;
	private PatientVitals vitals;
	private String allergies;
	private String healthConcerns;
	private List<Prescription> prescriptions;
	private String recommendations;
	private String findings;
	private String prescriptionString;
	
	public Visit(String inputVisitID, String inputPatientID) {
		visitID = inputVisitID;
		patientID = inputPatientID;
		state = "NEW";
		vitals = null;
		allergies = "NULL";
		healthConcerns = "NULL";
		prescriptions = new ArrayList<Prescription>();
		recommendations = "NULL";
		findings = "NULL";
	}
	
	public Visit() {
		visitID = "NULL";
		patientID = "NULL";
		state = "NEW";
		vitals = null;
		allergies = "NULL";
		healthConcerns = "NULL";
		prescriptions = new ArrayList<Prescription>();
		recommendations = "NULL";
		findings = "NULL";
	}
	
	public void setState(String nState) {
		state = nState;
		return;
	}
	
	public String getState() {
		return state;
	}
	
	public void setVitals(PatientVitals nVitals) {
		vitals = nVitals;
		return;
	}
	
	public PatientVitals getVitals() {
		return vitals;
	}
	
	public void setAllergies(String nAllergies) {
		allergies = nAllergies;
		return;
	}
	
	public String getAllergies() {
		return allergies;
	}
	
	public void setHealthConcerns(String nHealthConcerns) {
		healthConcerns = nHealthConcerns;
		return;
	}
	
	public String getHealthConcerns() {
		return healthConcerns;
	}
	
	public void setPrescriptions(List<Prescription> nPrescriptions) {
		prescriptions = nPrescriptions;
		return;
	}
	
	public void appendPrescription(Prescription nPrescription) {
		prescriptions.add(nPrescription);
		setPrescriptionString();
		return;
	}
	
	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}
	
	public void setRecommendations(String nRecommendations) {
		recommendations = nRecommendations;
		return;
	}
	
	public String getRecommendations() {
		return recommendations;
	}
	
	public void setFindings(String nFindings) {
		findings = nFindings;
		return;
	}
	
	public String getFindings() {
		return findings;
	}
	
	public void setPrescriptionString() {
		prescriptionString = prescriptions.stream().map((e) -> { return e.getName(); }).collect(Collectors.joining(", "));
		System.out.println(prescriptionString);
		return;
	}
	
	public String getPrescriptionString() {
		return prescriptionString;
	}
}
