package application.healthSoftware.data;

import java.util.List;

public class Visit {
	public String visitID;
	public String patientID;
	private String state;
	private PatientVitals vitals;
	private String allergies;
	private String healthConcerns;
	private List<Prescription> prescriptions;
	private String recommendations;
	private String findings;
	
	public Visit(String inputVisitID, String inputPatientID) {
		visitID = inputVisitID;
		patientID = inputPatientID;
		state = "NULL";
		vitals = null;
		allergies = "NULL";
		healthConcerns = "NULL";
		prescriptions = null;
		recommendations = "NULL";
		findings = "NULL";
	}
	
	public Visit() {
		visitID = "NULL";
		patientID = "NULL";
		state = "NULL";
		vitals = null;
		allergies = "NULL";
		healthConcerns = "NULL";
		prescriptions = null;
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
}
