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
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class PatientHomeScreen implements IScreen {
	public static String ScreenID = "patientHomeScreen";
	
	ScreenController screenController;
	private DataController dataController;
	
	
	public PatientHomeScreen(ScreenController sc) {
		screenController = sc;
		dataController = DataController.getInstance();
	}
	
	public void refreshData() {
		
	}
	
	public Region getLayout() {		
		//constructing top hbox
		HBox top = new HBox();
		
		Label patientHome = new Label("Patient Home");
		Button notifications = new Button("Notifications");
		
		
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
		createMessage.setPrefSize(250, 100);
		createMessage.setFont(new Font("Arial", 20));
		createMessage.setOnMouseClicked((e) -> {
			screenController.moveToScreen("patientCreateQuestion");
		});
		
		Button viewAnswers = new Button("View Answers");
		viewAnswers.setPrefSize(250, 100);
		viewAnswers.setFont(new Font("Arial", 20));
		viewAnswers.setOnMouseClicked((e) -> {
			screenController.moveToScreen("patientViewAnswers");
		});
		
		//set spacing
		buttonVBox.setAlignment(Pos.CENTER);
		buttonVBox.setSpacing(20);
		
		//add to button vbox, set spacings later
		buttonVBox.getChildren().addAll(changeInfo, createMessage, viewAnswers);
		
		//construct previous vists 
		VBox visitsVBox = new VBox();
		
		Label previousVistsLabel = new Label("Previous Visits");
		previousVistsLabel.setFont(new Font("Arial", 48));
		
		
		//get visits and display in textarea
		String visitsDisplay = "";
		
		List<Visit> visitList = dataController.getAllVisitsForPatientWithState(curr.patientID, "COMPLETE");
		if (!visitList.isEmpty())
		{
			for(Visit visits: visitList) {
				visitsDisplay = visitsDisplay + "Concern: " + visits.getHealthConcerns() + "\nFindings: " + visits.getFindings() + "\nRecommendations: " + visits.getRecommendations() + "\n\n";
			}
		}
		else
		{
			visitsDisplay = "No Visits Found";
		}
		
		TextArea previousVisitsText = new TextArea(visitsDisplay);	//"Previous Visits Listed Here"
		
		previousVisitsText.setPrefSize(400, 250);
		
		
		Button viewAll = new Button("View All");
		viewAll.setFont(new Font("Arial", 20));
		viewAll.setPrefSize(250, 100);
		
		//set spacing
		visitsVBox.setAlignment(Pos.CENTER);
		visitsVBox.setPadding(new Insets(0, 0, 0, 100));
		visitsVBox.setSpacing(20);
		
		
		//add chlidren
		visitsVBox.getChildren().addAll(previousVistsLabel, previousVisitsText, viewAll);

		//construct midRoot out of above two vboxes
		HBox midRoot = new HBox(buttonVBox, visitsVBox);
		
		midRoot.setAlignment(Pos.CENTER);
		midRoot.setPadding(new Insets(100, 0, 100, 0));
		return new VBox(top, midRoot);
	}
}