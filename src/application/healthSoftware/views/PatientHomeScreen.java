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

public class PatientHomeScreen implements IScreen {
	public static String ScreenID = "patientHomeScreen";
	
	ScreenController screenController;
	
	
	public PatientHomeScreen(ScreenController sc) {
		screenController = sc;
	}
	
	public void refreshData() {
		
	}
	
	public Region getLayout() {		
		//constructing top hbox
		HBox top = new HBox();
		
		//add icon heart 
		//add icon bell
		Label patientHome = new Label("Patient Home");
		Button notifications = new Button("Notifications");	//temp button, replace with icon later
		
		
		
		//change appearance v
		patientHome.setFont(new Font("Arial", 80));
		
		//set spacing
		top.setAlignment(Pos.CENTER);
		top.setSpacing(20);
		
		//add chlidren
		top.getChildren().addAll(patientHome, notifications);		//need to add icons once those are done
		
		
		
		//constructing left vbox with change info and create message buttons
		VBox buttonVBox = new VBox();
		
		Button changeInfo = new Button("Change Information"); 
		changeInfo.setPadding(new Insets(5,25,20,25)); 
		changeInfo.setOnMouseClicked((e) -> {
			screenController.moveToScreen("patientPortalChangeInfo");
		});
		
		Button changeImmunizations = new Button("Change Immunizations"); 
		changeImmunizations.setPadding(new Insets(5,25,20,25)); 
		changeImmunizations.setOnMouseClicked((e) -> {
			screenController.moveToScreen("patientPortalChangeImmunization");
		});
		
		Button createMessage = new Button("Create Message");
		createMessage.setPadding(new Insets(5,25,20,25));
		createMessage.setOnMouseClicked((e) -> {
			screenController.moveToScreen("messageScreen");
		});
		
		//im not sure how button handling will work in this so im not doing it until the meeting
		//changeInfo.setOnAction(this);
		
		//set spacing
		buttonVBox.setAlignment(Pos.CENTER);
		buttonVBox.setPadding(new Insets(0, 100, 0, 0));
		buttonVBox.setSpacing(20);
		
		//add to button vbox, set spacings later
		buttonVBox.getChildren().addAll(changeInfo, changeImmunizations, createMessage);
		
		//construct previous vists 
		VBox visitsVBox = new VBox();
		
		Label previousVistsLabel = new Label("Previous Visits");
		previousVistsLabel.setFont(new Font("Arial", 28));
		
		TextField previousVisitsText = new TextField("Previous Visits Listed Here");	//"Previous Visits Listed Here"
		previousVisitsText.setPadding(new Insets(0, 75, 150, 75));
		//date of visit, details
		//ill need to get the date of visit somehow, ask where that data is coming from
		
		Button viewAll = new Button("View All");
		viewAll.setPadding(new Insets(5,25,20,25));	//will have to change insets later
		
		//set spacing
		visitsVBox.setAlignment(Pos.CENTER);
		visitsVBox.setPadding(new Insets(0, 0, 0, 100));
		visitsVBox.setSpacing(20);
		
		//add chlidren
		visitsVBox.getChildren().addAll(previousVistsLabel, previousVisitsText, viewAll);	//add date of visit and buttons later

		//construct midRoot out of above two vboxes
		HBox midRoot = new HBox(buttonVBox, visitsVBox);
		
		midRoot.setAlignment(Pos.CENTER);
		midRoot.setPadding(new Insets(100, 0, 100, 0));
		//return the final box/layout here i think
		//return new VBox(helloLabel, logoutButton);
		
		//return final layout
		return new VBox(top, midRoot);
	}
}


//todo
//  - icon in top right
//  - previous visits
//		- how do i get the data for this?
//	- spacing for everything

//notes
//	- hbox on top for icon, patient home label, bell notif icon
//	
//	- 