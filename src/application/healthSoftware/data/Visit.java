package application.healthSoftware.data;

import java.util.List;

public class Visit {
	public String visitID;
	public String patientID;
	String state;
	PatientVitals vitals;
	String allergies;
	String healthConcerns;
	List<Prescription> prescriptions;
	String recommendations;
	String findings;
	
	public Visit() {
		
	}
}
