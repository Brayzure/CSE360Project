package application.healthSoftware.data;

import java.io.Serializable;

public class PharmacyInformation implements Serializable {
	//instance vars
	public String name;
	public String address;
	public String phoneNumber;
	
	
	//no properties constructor
	public PharmacyInformation() {
		this.name = "placeholder";	//remove placeholder later
		this.address = "";
		this.phoneNumber = "";
	}
	
	//constructor
	public PharmacyInformation(String nameInput, String addressInput, String phoneNumberInput)
	{
		this.name = nameInput;
		this.address = addressInput;
		this.phoneNumber = phoneNumberInput;
	}
}
