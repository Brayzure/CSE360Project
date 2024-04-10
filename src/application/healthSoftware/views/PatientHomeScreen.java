package application.healthSoftware.views;

import java.util.List;

import application.healthSoftware.DataController;
import application.healthSoftware.ScreenController;
import application.healthSoftware.data.PatientProfile;
import application.healthSoftware.data.User;
import application.healthSoftware.data.Visit;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
	
	private DataController dataController; {
		dataController = DataController.getInstance();
	};
	
	public Region getLayout() {		
		//constructing top hbox
		HBox top = new HBox();
		
		//add icon heart 
		//add icon bell
		Label patientHome = new Label("Patient Home");
		Button notifications = new Button("Notifications");	//temp button, replace with icon later
		
		
		User currentUser = dataController.getCurrentUser();
		
		PatientProfile curr = currentUser.patientProfile;
		
		
		
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
		//changeInfo.setPadding(new Insets(5,25,20,25)); 
		changeInfo.setFont(new Font("Arial", 20));
		changeInfo.setPrefSize(250, 100);
		
		changeInfo.setOnMouseClicked((e) -> {
			screenController.moveToScreen("patientPortalChangeInfo");
		});
		
		Button changeImmunizations = new Button("Change Immunizations"); 
		changeImmunizations.setPadding(new Insets(5,25,20,25)); 
		changeImmunizations.setOnMouseClicked((e) -> {
			screenController.moveToScreen("patientPortalChangeImmunization");
		});
		
		Button createMessage = new Button("Ask Question");
		//createMessage.setPadding(new Insets(5,25,20,25));
		createMessage.setPrefSize(250, 100);
		createMessage.setFont(new Font("Arial", 20));
		createMessage.setOnMouseClicked((e) -> {
			screenController.moveToScreen("patientCreateQuestion");
		});
		
		Button viewAnswers = new Button("View Answers");
		//viewAnswers.setPadding(new Insets(5,25,20,25));
		viewAnswers.setPrefSize(250, 100);
		viewAnswers.setFont(new Font("Arial", 20));
		viewAnswers.setOnMouseClicked((e) -> {
			screenController.moveToScreen("patientViewAnswers");
		});
		
		//im not sure how button handling will work in this so im not doing it until the meeting
		//changeInfo.setOnAction(this);
		
		//set spacing
		buttonVBox.setAlignment(Pos.CENTER);
		//buttonVBox.setPadding(new Insets(0, 100, 0, 0));
		buttonVBox.setSpacing(20);
		
		//add to button vbox, set spacings later
		buttonVBox.getChildren().addAll(changeInfo, /*changeImmunizations,*/createMessage, viewAnswers);
		
		//construct previous vists 
		VBox visitsVBox = new VBox();
		
		Label previousVistsLabel = new Label("Previous Visits");
		previousVistsLabel.setFont(new Font("Arial", 48));
		
		
		//get visits and display in textarea
		String visitsDisplay = "";
		
		List<Visit> visitList = dataController.getAllVisitsWithState("FINDINGS");
		if (!visitList.isEmpty())
		{
			for(Visit visits: visitList) {
				PatientProfile visitingPatient = dataController.getPatientProfile(visits.patientID);
				
				visitsDisplay = visitsDisplay + "Patient: " + visitingPatient.lastName + ", " + visitingPatient.firstName + "\nState: " + visits.getState() + "\n\n";
			}
		}
		else
		{
			visitsDisplay = "No Visits Found";
		}
		
		TextArea previousVisitsText = new TextArea(visitsDisplay);	//"Previous Visits Listed Here"
		
		previousVisitsText.setPrefSize(400, 250);
		
		//previousVisitsText.setPadding(new Insets(0, 75, 150, 75));
		//date of visit, details
		//ill need to get the date of visit somehow, ask where that data is coming from
		
		Button viewAll = new Button("View All");
		//viewAll.setPadding(new Insets(5,25,20,25));	//will have to change insets later
		viewAll.setFont(new Font("Arial", 20));
		viewAll.setPrefSize(250, 100);
		
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