package application.healthSoftware.data;

public class PharmacyInformation {
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
}
