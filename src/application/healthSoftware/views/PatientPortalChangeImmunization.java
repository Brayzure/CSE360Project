package application.healthSoftware.views;

import application.healthSoftware.ScreenController;
import application.healthSoftware.data.Immunization;
import application.healthSoftware.data.PatientProfile;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class PatientPortalChangeImmunization implements IScreen {
	public static String ScreenID = "patientPortalChangeImmunization";
	
	ScreenController screenController;
	
	
	public PatientPortalChangeImmunization(ScreenController sc) {
		screenController = sc;
	}
	
	public void refreshData() {
		
	}
	
	public Region getLayout() {
		
		PatientProfile curr = new PatientProfile(); 
		
		String currImmun1 = "";
		String currImmun2 = "";
		String currImmun3 = "";
		String currImmun4 = "";
		int counter = 1;
		
		for(Immunization i : curr.immunizations) {
			if (counter == 1)
			{
				currImmun1 = curr.immunizations.get(counter).type;
				counter++;
			}
			else if (counter == 2)
			{
				currImmun2 = curr.immunizations.get(counter).type;
				counter++;
			}
			else if (counter == 3)
			{
				currImmun3 = curr.immunizations.get(counter).type;
				counter++;
			}
			else if (counter == 4)
			{
				currImmun4 = curr.immunizations.get(counter).type;
				counter++;
			}
		}

		
		//start tyler's skeleton
		Label helloLabel = new Label("HELLO");
		
		Button logoutButton = new Button("LOG OUT");
		
		logoutButton.setOnAction(e -> {
			screenController.moveToScreen("loginScreen");
		});
		

		//construct top
		HBox top = new HBox();
		
		Label createAccountLabel = new Label("Update Immunizations");
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
		TextField immun1 = new TextField(currImmun1);	//immun1.setPadding(new Insets(0, 0, 0, 20));
		TextField immun2 = new TextField(currImmun2);	//immun1.setPadding(new Insets(0, 0, 0, 20));
		TextField immun3 = new TextField(currImmun3);	//immun1.setPadding(new Insets(0, 0, 0, 20));
		TextField immun4 = new TextField(currImmun4);	//immun1.setPadding(new Insets(0, 0, 0, 20));
		
		immun1.setPrefSize(200, 10);		//set the sizes when doing ui cleanup with this 
		
		//construct button
		Button addMore = new Button("Add More");	addMore.setPadding(new Insets(5, 45, 5, 45));
		Button save = new Button("Save");		save.setPadding(new Insets(0, 15, 0, 15));
		save.setOnMouseClicked((e) -> {
			screenController.moveToScreen("patientHomeScreen");
		});
		
		//set spacing, change later
		mid.setAlignment(Pos.CENTER);
		mid.setSpacing(10);
		
		//add chlidren
		mid.getChildren().addAll(immun1, immun2, immun3, immun4, addMore, save);
		
		
		
		//return
		return new VBox(top, mid);
	}
}