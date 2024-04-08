package application.healthSoftware.views;

import application.healthSoftware.DataController;
import application.healthSoftware.ScreenController;
import application.healthSoftware.data.PatientProfile;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

//imports paxton added, may or may not be neccessary
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class PatientPortalChangeInfo implements IScreen {
	public static String ScreenID = "patientPortalChangeInfo";
	
	ScreenController screenController;
	
	
	public PatientPortalChangeInfo(ScreenController sc) {
		screenController = sc;
	}
	
	public void refreshData() {
		
	}
	
	
	private DataController dataController; {
		dataController = DataController.getInstance();
	};
	
	
	/*PatientProfile curr;  {
		curr = dataController.getCurrentPatientProfile();
	};
	*/
	
	//placeholder PatientProfile until i figure out how to get the current one, osmething to do with user id from User.java class??

	
	public Region getLayout() {
		
		PatientProfile curr = new PatientProfile();
		
		
		
		//curr = getCurrentPatientProfile(){
			
		//};
		
		
		String currFirstName = curr.firstName; 
		String currLastName = curr.lastName;
		String currDob = curr.birthday;
		String currPhoneNum = curr.contactInformation.phoneNumber;
		
		String currEmail = curr.contactInformation.email;
		String currInsuranceProv = curr.insurance.provider;
		String currGroupNum = curr.insurance.groupNumber;
		String currMemberID = curr.insurance.memberID;
		
		String currPharmName = curr.pharmacy.name;
		String currPharmAddress = curr.pharmacy.address;
		String currPharmPhone = curr.pharmacy.phoneNumber;
		//this is a duplicate text field in the ui, fix ui then come back and fix this.
		String currPharmPhoneDuplicate = currPharmPhone;
		
		
		//construct top
		HBox top = new HBox();
		
		Label createAccountLabel = new Label("Change Information");
		createAccountLabel.setFont(new Font("Arial", 80));
		
		//set spacing
		top.setAlignment(Pos.CENTER);
		
		//add children
		top.getChildren().addAll(createAccountLabel);
		
	
		//construct mid
		//mid will hold 3 vboxs of labels and text fields
		HBox mid = new HBox();
			
			//construct first vbox
			VBox leftMid = new VBox();
			
				//make labels
				Label firstName = new Label("First Name:");
				Label lastName = new Label("Last Name:");
				Label dob = new Label("Date of Birth:");
				Label phoneNum = new Label("Phone Number:");
				
				//make text fields
				TextField firstNameTF = new TextField(currFirstName);
				TextField lastNameTF = new TextField(currLastName);
				TextField dobTF = new TextField(currDob);
				TextField phoneNumTF = new TextField(currPhoneNum);
				
				//set spacing, adjust later
				leftMid.setSpacing(5);
				
				//add children
				leftMid.getChildren().addAll(firstName, firstNameTF, lastName, lastNameTF, dob, dobTF, phoneNum, phoneNumTF);
		
				
			//construct midMid
			VBox midMid = new VBox();
			
				//make labels
				Label email = new Label("Email:");
				Label insuranceProvider = new Label("Insurance Provider:");
				Label groupNum = new Label("Group Number:");
				Label memberID = new Label("Member ID:");
				
				//make text fields
				TextField emailTF = new TextField(currEmail);
				TextField insuranceProviderTF = new TextField(currInsuranceProv);
				TextField groupNumTF = new TextField(currGroupNum);
				TextField memberIDTF = new TextField(currMemberID);
				
				//set spacing, adjust later
				midMid.setSpacing(5);
				
				//add children
				midMid.getChildren().addAll(email, emailTF, insuranceProvider, insuranceProviderTF, groupNum, groupNumTF, memberID, memberIDTF);
				
			//construct rightMid
			VBox rightMid = new VBox();
	
				//make labels
				Label pharmName = new Label("Pharmacy Name:");
				Label pharmAddress = new Label("Pharmacy Address:");
				Label pharmPhoneNum = new Label("Pharmacy Phone:");
				Label phoneNum2 = new Label("Phone Number:");
				
				//make text fields
				TextField pharmNameTF = new TextField(currPharmName);
				TextField pharmAddressTF = new TextField(currPharmAddress);
				TextField pharmPhoneNumTF = new TextField(currPharmPhone);
				TextField phoneNum2TF = new TextField(currPharmPhoneDuplicate);
				
				//set spacing, adjust later
				rightMid.setSpacing(5);
				
				//add children
				rightMid.getChildren().addAll(pharmName, pharmNameTF, pharmAddress, pharmAddressTF, pharmPhoneNum, pharmPhoneNumTF, phoneNum2, phoneNum2TF);
			
		//set spacing
		mid.setAlignment(Pos.CENTER);
		mid.setSpacing(5);
		
		//add chlidren
		mid.getChildren().addAll(leftMid, midMid, rightMid);
		
		
		//construct bottom
		HBox bot = new HBox();
		
		Button cont = new Button("Continue");
		cont.setOnMouseClicked((e) -> {
			screenController.moveToScreen("patientHomeScreen");
		});
		
		//set spacing
		bot.setAlignment(Pos.CENTER);

		//set children
		bot.getChildren().addAll(cont);
		
		
		//return
		return new VBox(top, mid, bot);
	}
}

	//todo: set insets of text fields?