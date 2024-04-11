package application.healthSoftware.data;

import java.io.Serializable;

public class ContactInformation implements Serializable {
	//instance vars
	public String phoneNumber;
	public String email;
	
	//no properties constructor
	public ContactInformation()
	{
		this.phoneNumber = "";
		this.email = "";
	}
	
	// Constructor
	public ContactInformation(String phoneInput, String emailInput)
	{
		this.phoneNumber = phoneInput;
		this.email = emailInput;
	}
	
	// String representation
	public String toString() {
		String out = "phoneNumber=" + phoneNumber
				+ "\nemail=" + email;
		return out;
	}
}
