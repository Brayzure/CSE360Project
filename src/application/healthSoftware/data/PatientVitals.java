package application.healthSoftware.data;

public class PatientVitals {
	private int height;
	private int weight;
	private int temperature;
	private int systolicBP;
	private int diastolicBP;
	
	public PatientVitals() {
		height = 0;
		weight = 0;
		temperature = 0;
		systolicBP = 0;
		diastolicBP = 0;
	}
	
	public PatientVitals(int heightInput, int weightInput, int tempInput, int sBPInput, int dBPInput) {
		height = heightInput;
		weight = weightInput;
		temperature = tempInput;
		systolicBP = sBPInput;
		diastolicBP = dBPInput;
	}
	
	public void setHeight(int nHeight) {
		height = nHeight;
		return;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setWeight(int nWeight) {
		weight = nWeight;
		return;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setTemp(int nTemp) {
		temperature = nTemp;
		return;
	}
	
	public int getTemp() {
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
