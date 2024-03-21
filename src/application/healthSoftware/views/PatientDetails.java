package application.healthSoftware.views;

import application.healthSoftware.ScreenController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

//imports paxton added, may or may not be neccessary
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class PatientDetails implements IScreen {
	public static String ScreenID = "patientDetails";
	
	ScreenController screenController;
	
	
	public PatientDetails(ScreenController sc) {
		screenController = sc;
	}
	
	public void refreshData() {
		
	}
	
	public Region getLayout() {
		
		//construct top
		HBox top = new HBox();
		
		Label patientDetails = new Label("Patient Details");
		patientDetails.setFont(new Font("Arial", 80));
		
		//set spacing
		top.setAlignment(Pos.CENTER);
		
		//add children
		top.getChildren().addAll(patientDetails);
		
		
		//construct mid
		
		HBox mid = new HBox();
		
		//personal info, i think the string could be formated by helper function with data passed to by dataController?
		String personalInfoText = "Health Information\n\nName:\nPhone Number:\nEmail:\nGender Identity:\nLegal Sex:\nRace:\n";
		TextArea personalInfoDisplay = new TextArea(personalInfoText);
		
		//health info, same formatting question as above
		String healthInfoText = "Health Information\n\nHeight:\nWeight:\nBlood Pressure:\n";
		TextArea healthInfoDisplay = new TextArea(healthInfoText);
		
		//set spacing, values will probably need to be adjusted
		mid.setAlignment(Pos.CENTER);
		mid.setSpacing(20);
		
		//add children
		mid.getChildren().addAll(personalInfoDisplay, healthInfoDisplay);
		
		
		//return
		return new VBox(top, mid);
	}
}
