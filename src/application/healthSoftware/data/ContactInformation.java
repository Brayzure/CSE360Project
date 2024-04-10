package application.healthSoftware.data;

import java.io.Serializable;

public class ContactInformation implements Serializable {
	//instance vars
	String phoneNumber;
	String email;
	
	
	//no properties constructor
	public ContactInformation()
	{
		this.phoneNumber = "";
		this.email = "";
	}
	
	//constructor, i believe that this is neccessary but tyler just comment this out if it isnt
	public ContactInformation(String phoneInput, String emailInput)
	{
		this.phoneNumber = phoneInput;
		this.email = emailInput;
	}
	
	public String toString() {
		String out = "phoneNumber=" + phoneNumber
				+ "\nemail=" + email;
		return out;
	}
}
