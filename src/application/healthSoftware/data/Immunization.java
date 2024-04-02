package application.healthSoftware.data;

import java.io.Serializable;

public class Immunization implements Serializable {
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
