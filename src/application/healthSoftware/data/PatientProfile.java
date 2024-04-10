package application.healthSoftware.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PatientProfile implements Serializable {

	// Change whenever we DELETE a field or we CHANGE its type
	// Methods can be changed or deleted freely
	private static final long serialVersionUID = 1L;
	
	//instance vars
	public String patientID;
	public String firstName;
	public String lastName;
	public String birthday;

	ContactInformation contactInformation;
	InsuranceInformation insurance;
	PharmacyInformation pharmacy;
	List<Immunization> immunizations;	//i will need to get size of this later for the change info i think, im not sure how the ADD MORE button will work. might take some new javafx knowledge.
	
	
	public PatientProfile()
	{
		this.contactInformation = new ContactInformation();
		this.insurance = new InsuranceInformation();
		this.pharmacy = new PharmacyInformation();
		this.immunizations = new ArrayList<Immunization>();		//extends abstract class list, empty
	}
	
	//constructor, take no properties, run methods below to populate
	//probably wont be used
	public PatientProfile(String patientIDInput, String firstInput, String lastInput, String birthInput, ContactInformation contactInput, InsuranceInformation insuranceInput, PharmacyInformation pharmInput, List<Immunization> immunsInput)
	{
		//vars
		this.patientID = patientIDInput;
		this.firstName = firstInput;
		this.lastName = lastInput;
		this.birthday = birthInput;
		
		//classes, if method two, create instances of below
		this.contactInformation = contactInput;
		this.insurance = insuranceInput;
		this.pharmacy = pharmInput;
		this.immunizations = immunsInput;
	}
	

	
	public String toString() {
		String out = "patientID=" + patientID
				+ "\nfirstName=" + firstName
				+ "\nlastName=" + lastName
				+ "\nbirthday=" + birthday
				+ "\n" + contactInformation.toString()
				+ "\n" + insurance.toString()
				+ "\n" + pharmacy.toString()
				+ "\nimmunizations=" + immunizationsString;
		return out;
	}
	
	
	//IMPORTANT TYLER READ THIS PLEASE
	
	//for each below methods, i figured there was two ways to do it
	//i think the second way is correct but just incase it isnt, i implemented both methods and commented out the one that i think is inccorect
	
	
	//methods, check class diagram on discord to fill out
	
	//update existing contact information
	public void updateContact(String phoneInput, String emailInput){
		//method one, create a new ContactInformation "instance" (right term?)
		this.contactInformation = new ContactInformation(phoneInput, emailInput);
		
		//method two, just update this. I think this is the correct way
			//this.contactInformation.phoneNumber = phoneInput;
			//this.contactInformation.email = emailInput;
	}
	
	//update existing insurance information
	public void updateInsurance(String providerInput, String groupNumberInput, String memberIDInput)
	{
		//method one
		this.insurance = new InsuranceInformation(providerInput, groupNumberInput, memberIDInput);
		
		//method two
			//this.insurance.provider = providerInput;
			//this.insurance.groupNumber = groupNumberInput;
			//this.insurance.memberID = memberIDInput;
	}

	//update existing pharm information
	public void updatePharmacy(String nameInput, String addressInput, String phoneNumberInput)
	{
		//method one
		this.pharmacy = new PharmacyInformation(nameInput, addressInput, phoneNumberInput);
	
		//method two
			//this.pharmacy.name = nameInput;
			//this.pharmacy.address = addressInput;
			//this.pharmacy.phoneNumber = phoneNumberInput;
	}
	
	public void addImmunization(Immunization immunInput)
	{
		immunizations.add(immunInput);
	}
}
