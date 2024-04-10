package application.healthSoftware.data;

import java.io.Serializable;

public class PatientVitals implements Serializable {
	private String height;
	private int weight;
	private Double temperature;
	private int systolicBP;
	private int diastolicBP;
	
	public PatientVitals() {
		height = "";
		weight = 0;
		temperature = 0.0;
		systolicBP = 0;
		diastolicBP = 0;
	}
	
	public PatientVitals(String heightInput, int weightInput, Double tempInput, int sBPInput, int dBPInput) {
		height = heightInput;
		weight = weightInput;
		temperature = tempInput;
		systolicBP = sBPInput;
		diastolicBP = dBPInput;
	}
	
	public void setHeight(String nHeight) {
		height = nHeight;
		return;
	}
	
	public String getHeight() {
		return height;
	}
	
	public void setWeight(int nWeight) {
		weight = nWeight;
		return;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setTemp(Double nTemp) {
		temperature = nTemp;
		return;
	}
	
	public Double getTemp() {
		return temperature;
	}
	
	public void setSystolic(int nSystolic) {
		systolicBP = nSystolic;
		return;
	}
	
	public int getSystolic() {
		return systolicBP;
	}
	
	public void setDiastolic(int nDiastolic) {
		diastolicBP = nDiastolic;
		return;
	}
	
	public int getDiastolic() {
		return diastolicBP;
	}
}
