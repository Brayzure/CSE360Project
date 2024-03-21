package application.healthSoftware.views;

import application.healthSoftware.ScreenController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

//imports paxton added, may or may not be neccessary
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class PatientPortalCreateAccount implements IScreen {
	public static String ScreenID = "patientPortalCreateAccount";
	
	ScreenController screenController;
	
	
	public PatientPortalCreateAccount(ScreenController sc) {
		screenController = sc;
	}
	
	public void refreshData() {
		
	}
	
	public Region getLayout() {
		//start tyler's skeleton
		Label helloLabel = new Label("HELLO");
		
		Button logoutButton = new Button("LOG OUT");
		
		logoutButton.setOnAction(e -> {
			screenController.moveToScreen("loginScreen");
		});
		

		//construct top
		HBox top = new HBox();
		
		Label createAccountLabel = new Label("Create Account");
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
				Label phoneNum = new Label("Phone Numebr:");
				
				//make text fields
				TextField firstNameTF = new TextField();
				TextField lastNameTF = new TextField();
				TextField dobTF = new TextField();
				TextField phoneNumTF = new TextField();
				
				//set spacing, adjust later
				leftMid.setSpacing(5);
				
				//add chlidren
				leftMid.getChildren().addAll(firstName, firstNameTF, lastName, lastNameTF, dob, dobTF, phoneNum, phoneNumTF);
		
				
			//construct midMid
			VBox midMid = new VBox();
			
				//make labels
				Label email = new Label("First Name:");
				Label insuranceProvider = new Label("Last Name:");
				Label groupNum = new Label("Date of Birth:");
				Label pharmName = new Label("Phone Numebr:");
				
				//make text fields
				TextField emailTF = new TextField();
				TextField insuranceProviderTF = new TextField();
				TextField groupNumTF = new TextField();
				TextField pharmNameTF = new TextField();
				
				//set spacing, adjust later
				midMid.setSpacing(5);
				
				//add chlidren
				midMid.getChildren().addAll(email, emailTF, insuranceProvider, insuranceProviderTF, groupNum, groupNumTF, pharmName, pharmNameTF);
				
			//construct rightMid
			VBox rightMid = new VBox();
	
				//make labels
				Label pharmName2 = new Label("First Name:");
				Label memberID = new Label("Last Name:");
				Label pharmAddress = new Label("Date of Birth:");
				Label pharmPhoneNum = new Label("Phone Numebr:");
				
				//make text fields
				TextField pharmName2TF = new TextField();
				TextField memberIDTF = new TextField();
				TextField pharmAddressTF = new TextField();
				TextField pharmPhoneNumTF = new TextField();
				
				//set spacing, adjust later
				rightMid.setSpacing(5);
				
				//add chlidren
				rightMid.getChildren().addAll(pharmName2, pharmName2TF, memberID, memberIDTF, pharmAddress, pharmAddressTF, pharmPhoneNum, pharmPhoneNumTF);
			
		//set spacing
		mid.setAlignment(Pos.CENTER);
		mid.setSpacing(5);
		
		//add chlidren
		mid.getChildren().addAll(leftMid, midMid, rightMid);
		
		
		//construct bottom
		HBox bot = new HBox();
		
		Button cont = new Button("Continue");
		cont.setOnMouseClicked((e) -> {
			screenController.moveToScreen("patientPortalCreateImmunization");
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