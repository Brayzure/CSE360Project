package application.healthSoftware.data;

public class InsuranceInformation {
	//instance vars
	String provider;
	String groupNumber;
	String memberID;
	
	//no properties constructor
	public InsuranceInformation()
	{
		this.provider = "";
		this.groupNumber = "";
		this.memberID = "";
	}
	
	//constructor
	public InsuranceInformation(String providerInput, String groupNumberInput, String memberIDInput)
	{
		this.provider = providerInput;
		this.groupNumber = groupNumberInput;
		this.memberID = memberIDInput;
	}
}
