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

	public ContactInformation contactInformation;
	public InsuranceInformation insurance;
	public PharmacyInformation pharmacy;
	public List<Immunization> immunizations;
	public String immunizationsString;
	
	// Basic constructor
	public PatientProfile()
	{
		this.contactInformation = new ContactInformation();
		this.insurance = new InsuranceInformation();
		this.pharmacy = new PharmacyInformation();
		this.immunizations = new ArrayList<Immunization>();		//extends abstract class list, empty
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
	
	//update existing contact information
	public void updateContact(String phoneInput, String emailInput){
		this.contactInformation = new ContactInformation(phoneInput, emailInput);
	}
	
	//update existing insurance information
	public void updateInsurance(String providerInput, String groupNumberInput, String memberIDInput)
	{
		this.insurance = new InsuranceInformation(providerInput, groupNumberInput, memberIDInput);
	}

	//update existing pharm information
	public void updatePharmacy(String nameInput, String addressInput, String phoneNumberInput)
	{
		this.pharmacy = new PharmacyInformation(nameInput, addressInput, phoneNumberInput);
	}
	
	public void addImmunization(Immunization immunInput)
	{
		immunizations.add(immunInput);
	}
}
