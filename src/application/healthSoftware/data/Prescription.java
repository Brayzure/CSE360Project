package application.healthSoftware.data;

public class Prescription {
	String name;
	String dosage;
	String duration;
	
	public Prescription() {
		this.name = "";
		this.dosage = "";
		this.duration = "";
	}
	
	public Prescription(String nName, String nDosage, String nDuration) {
		this.name = nName;
		this.dosage = nDosage;
		this.duration = nDuration;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDosage() {
		return dosage;
	}
	
	public String getDuration() {
		return duration;
	}
}
