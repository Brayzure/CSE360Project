package application.healthSoftware.data;

import java.io.Serializable;

public class PharmacyInformation implements Serializable {
	//instance vars
	String name;
	String address;
	String phoneNumber;
	
	//no properties constructor
	public PharmacyInformation() {
		this.name = "";
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
	
	public String toString() {
		String out = "pharmacyName=" + name
				+ "\npharmacyAddress=" + address
				+ "\npharmacyPhone=" + phoneNumber;
		return out;
	}
}
