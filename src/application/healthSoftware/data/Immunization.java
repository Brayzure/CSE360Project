package application.healthSoftware.data;

import java.io.Serializable;

public class Immunization implements Serializable {
	//instance vars
	public String type;
	String date;
	
	//no properties constructor
	public Immunization() {
		this.type = "placeholder";
		this.date = "";
	}
	
	//constructor
	public Immunization(String typeInput, String dateInput)
	{
		this.type = typeInput;
		this.date = dateInput;
	}
}
