package application.healthSoftware.data;

public class PatientVitals {
	int height;
	int weight;
	int temperature;
	int systolicBP;
	int diastolicBP;
	
	public PatientVitals() {
		this.height = 0;
		this.weight = 0;
		this.temperature = 0;
		this.systolicBP = 0;
		this.diastolicBP = 0;
	}
	
	public PatientVitals(int heightInput, int weightInput, int tempInput, int sBPInput, int dBPInput) {
		this.height = heightInput;
		this.weight = weightInput;
		this.temperature = tempInput;
		this.systolicBP = sBPInput;
		this.diastolicBP = dBPInput;
	}
}
