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

public class PatientPortalCreateImmunization implements IScreen {
	public static String ScreenID = "patientPortalCreateImmunization";
	
	ScreenController screenController;
	
	
	public PatientPortalCreateImmunization(ScreenController sc) {
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
		VBox mid = new VBox();
		
		Label immunHistory = new Label("Immunization History:");
		immunHistory.setFont(new Font("Arial", 40));
		
		//construct textfields
		TextField immun1 = new TextField("Name of Immunization");	immun1.setPadding(new Insets(0, 0, 0, 20));
		TextField immun2 = new TextField("Name of Immunization");	immun1.setPadding(new Insets(0, 0, 0, 20));
		TextField immun3 = new TextField("Name of Immunization");	immun1.setPadding(new Insets(0, 0, 0, 20));
		TextField immun4 = new TextField("Name of Immunization");	immun1.setPadding(new Insets(0, 0, 0, 20));
		
		//construct button
		Button addMore = new Button("Add More");	addMore.setPadding(new Insets(5, 45, 5, 45));
		Button signUp = new Button("Sign Up");		signUp.setPadding(new Insets(0, 15, 0, 15));
		signUp.setOnMouseClicked((e) -> {
			screenController.moveToScreen("loginScreen");
		});
		
		//set spacing, change later
		mid.setAlignment(Pos.CENTER);
		
		//add chlidren
		mid.getChildren().addAll(immun1, immun2, immun3, immun4, addMore, signUp);
		
		
		
		//return
		return new VBox(top, mid);
	}
}

	//todo: set insets of text fields?