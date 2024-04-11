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
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class PatientPortalChangeInfo implements IScreen {
	public static String ScreenID = "patientPortalChangeInfo";
	
	ScreenController screenController;
	private DataController dataController; 
	
	public PatientPortalChangeInfo(ScreenController sc) {
		screenController = sc;
		dataController = DataController.getInstance();
	}
	
	public void refreshData() {
		
	}
	
	public Region getLayout() {
		User currentUser = dataController.getCurrentUser();
		
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
		createAccountLabel.setFont(new Font(40));
		
		//set spacing
		top.setAlignment(Pos.CENTER);
		
		//add children
		top.getChildren().addAll(createAccountLabel);
		
	
		//construct mid
		//mid will hold 3 vboxs of labels and text fields
		HBox mid = new HBox();
			
		Font labelFont = new Font("Arial", 22);
		Insets labelInsets = new Insets(0, 0, 10, 0);
		
			//construct first vbox
			VBox one = new VBox();
			
				//make labels
				Label personalInfo = new Label("Personal Information"); 
					personalInfo.setFont(labelFont);
					personalInfo.setPadding(labelInsets);
				
				Label firstName = new Label("First Name:");	
				Label lastName = new Label("Last Name:");
				Label dob = new Label("Date of Birth:");
				
				//make text fields
				TextField firstNameTF = new TextField(currFirstName); 
					firstNameTF.setEditable(false);
					firstNameTF.setDisable(true);
				TextField lastNameTF = new TextField(currLastName);	
					lastNameTF.setEditable(false);
					lastNameTF.setDisable(true);
				TextField dobTF = new TextField(currDob); 
					dobTF.setEditable(false);
					dobTF.setDisable(true);
				
				//set spacing, adjust later
				one.setSpacing(5);
				
				//add children
				one.getChildren().addAll(personalInfo, firstName, firstNameTF, lastName, lastNameTF, dob, dobTF);
		
				
			VBox two = new VBox();
			
				Label contactInfo = new Label("Contact Information");
					contactInfo.setFont(labelFont);
					contactInfo.setPadding(labelInsets);
				Label phoneNum = new Label("Phone Number:");
				Label email = new Label("Email:");
				
				
				TextField phoneNumTF = new TextField(currPhoneNum);
				TextField emailTF = new TextField(currEmail);
				
				
				two.setSpacing(5);
				two.getChildren().addAll(contactInfo, phoneNum, phoneNumTF, email, emailTF);
				
			//construct midMid
			VBox three = new VBox();
			
				//make labels
				Label insuranceInformation = new Label("Insurance Information");
					insuranceInformation.setFont(labelFont);
					insuranceInformation.setPadding(labelInsets);
				Label insuranceProvider = new Label("Insurance Provider:");
				Label groupNum = new Label("Group Number:");
				Label memberID = new Label("Member ID:");
				
				//make text fields
				
				TextField insuranceProviderTF = new TextField(currInsuranceProv);
				TextField groupNumTF = new TextField(currGroupNum);
				TextField memberIDTF = new TextField(currMemberID);
				
				//set spacing, adjust later
				three.setSpacing(5);
				
				//add children
				three.getChildren().addAll(insuranceInformation, insuranceProvider, insuranceProviderTF, groupNum, groupNumTF, memberID, memberIDTF);
				
			//construct rightMid
			VBox four = new VBox();
	
				//make labels
				Label pharmacyInformation = new Label("Pharmacy Information");
					pharmacyInformation.setFont(labelFont);
					pharmacyInformation.setPadding(labelInsets);
				Label pharmName = new Label("Pharmacy Name:");
				Label pharmAddress = new Label("Pharmacy Address:");
				Label pharmPhoneNum = new Label("Pharmacy Phone:");
				
				
				//make text fields
				TextField pharmNameTF = new TextField(currPharmName);
				TextField pharmAddressTF = new TextField(currPharmAddress);
				TextField pharmPhoneNumTF = new TextField(currPharmPhone);
				
				
				//set spacing, adjust later
				four.setSpacing(5);
				
				//add children
				four.getChildren().addAll(pharmacyInformation, pharmName, pharmNameTF, pharmAddress, pharmAddressTF, pharmPhoneNum, pharmPhoneNumTF);
			
		//set spacing
		mid.setAlignment(Pos.CENTER);
		mid.setPadding(new Insets(40, 0, 0, 0));
		mid.setSpacing(20);
		
		//add chlidren
		mid.getChildren().addAll(one, two, three, four);
		
		
		//construct bottom
		VBox bot = new VBox();
		
		
			VBox immunBox = new VBox();
			
			Label immunLabel = new Label("Immunizations");
			immunLabel.setPadding(new Insets(25, 0, 0, 0));
			immunLabel.setFont(new Font("Arial", 16));
			
			TextArea immunText = new TextArea(currImmuns); 

			immunText.setMaxWidth(600);
			immunText.setPrefHeight(300);
			
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