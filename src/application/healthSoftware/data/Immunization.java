package application.healthSoftware.data;

public class Immunization {
	//instance vars
	String type;
	String date;
	
	//no properties constructor
	public Immunization() {
		this.type = "";
		this.date = "";
	}
	
	//constructor
	public Immunization(String typeInput, String dateInput)
	{
		this.type = typeInput;
		this.date = dateInput;
	}
}
