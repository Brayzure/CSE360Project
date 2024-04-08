package application.healthSoftware.views;

import application.healthSoftware.DataController;
import application.healthSoftware.ScreenController;
import application.healthSoftware.data.PatientProfile;
import application.healthSoftware.data.User;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
		
		//Cannot read field "firstName" because "curr" is null
			//PatientProfile curr = new PatientProfile();		
			//curr = dataController.getCurrentPatientProfile();
				
		User currentUser = dataController.getCurrentUser();
		
		//Cannot read field "firstName" because "curr" is null
		PatientProfile curr = currentUser.patientProfile;
		
		String currPatientID = curr.patientID;
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
		
		
		String currImmuns = curr.immunizationsString;
		
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
				Label patientID = new Label("Patient ID:");
				Label firstName = new Label("First Name:");
				Label lastName = new Label("Last Name:");
			
				
				//make text fields
				TextField patientIDTF = new TextField(currPatientID);	patientIDTF.setEditable(false);
				TextField firstNameTF = new TextField(currFirstName);
				TextField lastNameTF = new TextField(currLastName);
		
				
				//set spacing, adjust later
				leftMid.setSpacing(5);
				
				//add children
				leftMid.getChildren().addAll(patientID, patientIDTF, firstName, firstNameTF, lastName, lastNameTF);
		
				
			VBox leftmidMid = new VBox();
			
				Label dob = new Label("Date of Birth:");
				Label email = new Label("Email:");
				Label phoneNum = new Label("Phone Number:");
				
				TextField dobTF = new TextField(currDob);
				TextField emailTF = new TextField(currEmail);
				TextField phoneNumTF = new TextField(currPhoneNum);
				
				leftmidMid.setSpacing(5);
				leftmidMid.getChildren().addAll(dob, dobTF, email, emailTF, phoneNum, phoneNumTF);
				
			//construct midMid
			VBox midMid = new VBox();
			
				//make labels
				
				Label insuranceProvider = new Label("Insurance Provider:");
				Label groupNum = new Label("Group Number:");
				Label memberID = new Label("Member ID:");
				
				//make text fields
				
				TextField insuranceProviderTF = new TextField(currInsuranceProv);
				TextField groupNumTF = new TextField(currGroupNum);
				TextField memberIDTF = new TextField(currMemberID);
				
				//set spacing, adjust later
				midMid.setSpacing(5);
				
				//add children
				midMid.getChildren().addAll(insuranceProvider, insuranceProviderTF, groupNum, groupNumTF, memberID, memberIDTF);
				
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
				rightMid.getChildren().addAll(pharmName, pharmNameTF, pharmAddress, pharmAddressTF, pharmPhoneNum, pharmPhoneNumTF);
			
		//set spacing
		mid.setAlignment(Pos.CENTER);
		mid.setPadding(new Insets(40, 0, 0, 0));
		mid.setSpacing(5);
		
		//add chlidren
		mid.getChildren().addAll(leftMid,leftmidMid, midMid, rightMid);
		
		
		//construct bottom
		VBox bot = new VBox();
		
		
			VBox immunBox = new VBox();
			
			Label immunLabel = new Label("Immunizations");
			immunLabel.setPadding(new Insets(50, 0, 0, 0));
			immunLabel.setFont(new Font("Arial", 24));
			
			TextArea immunText = new TextArea(currImmuns); 
			//immunText.setPadding(new Insets(20, 20, 20, 20));
		
			//the immunTextArea is not coroporating
			
			//immunText.setPrefWidth(10);
			immunText.setPrefColumnCount(5);
			immunText.setPrefWidth(50);
			
			immunBox.setSpacing(20);
			immunBox.setAlignment(Pos.CENTER);
			
			immunBox.getChildren().addAll(immunLabel, immunText);
			
			
		Button save = new Button("Save");
		
		save.setPrefSize(200, 100);
		save.setOnMouseClicked((e) -> {
			//update information stored in current user
			
			//User currentUser = dataController.getCurrentUser();
			//PatientProfile curr = currentUser.patientProfile;
			
			curr.firstName = firstNameTF.getText();
			curr.lastName = lastNameTF.getText();
			curr.birthday = dobTF.getText();
			curr.contactInformation.phoneNumber = phoneNumTF.getText();
			
			curr.contactInformation.email = emailTF.getText();
			curr.insurance.provider = insuranceProviderTF.getText();
			curr.insurance.groupNumber = groupNumTF.getText();
			curr.insurance.memberID = memberIDTF.getText();
			
			curr.pharmacy.name = pharmNameTF.getText();
			curr.pharmacy.address = pharmAddressTF.getText();
			curr.pharmacy.phoneNumber = pharmPhoneNumTF.getText();
			//duplicate field here, finish ui stuff and add it here
			
			curr.immunizationsString = immunText.getText();

			//double check with tyler that these two lines are correct
			dataController.savePatientProfile(curr);
			dataController.saveUser(currentUser);
			
			screenController.moveToScreen("patientHomeScreen");
		});
		
		//set spacing
		bot.setAlignment(Pos.CENTER);
		bot.setSpacing(20);

		//set children
		bot.getChildren().addAll(immunBox, save);
		
		
		//return
		return new VBox(top, mid, bot);
	}
}

	//todo: set insets of text fields?