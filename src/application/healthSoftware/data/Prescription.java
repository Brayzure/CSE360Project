package application.healthSoftware.data;

import java.io.Serializable;

public class Prescription implements Serializable {
	private String name;
	private String dosage;
	private String duration;
	
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
	
	public void setName(String newName) {
		name = newName;
		return;
	}
	
	public String getDosage() {
		return dosage;
	}
	
	public void setDosage(String newDosage) {
		dosage = newDosage;
		return;
	}
	
	public String getDuration() {
		return duration;
	}
	
	public void setDuration(String newDuration) {
		duration = newDuration;
		return;
	}
}
