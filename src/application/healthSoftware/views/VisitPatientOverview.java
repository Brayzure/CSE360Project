package application.healthSoftware.views;

import application.healthSoftware.DataController;
import application.healthSoftware.ScreenController;
import application.healthSoftware.data.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class VisitPatientOverview implements IScreen {
	public static String ScreenID = "visitPatientOverview";
	
	private ScreenController screenController;
	private DataController dataController;
	
	public VisitPatientOverview(ScreenController sc) {
		screenController = sc;
		dataController = DataController.getInstance();
	}
	
	public void refreshData() {
		
	}
	
	public Region getLayout() {
		VBox layout = new VBox();
		
		HBox titleRow = new HBox();
		titleRow.setAlignment(Pos.CENTER);
		Label title = new Label("Patient Registration Complete");
		title.setFont(new Font(48));
		titleRow.getChildren().add(title);
		layout.getChildren().add(titleRow);
		
		VBox content = new VBox();
		content.setAlignment(Pos.CENTER);
		content.setPrefHeight(500);
		content.setSpacing(15);
		layout.getChildren().add(content);

		HBox announceRow = new HBox();
		announceRow.setAlignment(Pos.CENTER);
		Label announcement = new Label("Patient is ready to see the doctor! This is where we will display their overview once the PatientProfile class is fully defined.");
		announcement.setFont(new Font(30));
		announcement.setWrapText(true);
		announcement.setMaxWidth(500);
		announceRow.getChildren().add(announcement);
		content.getChildren().add(announceRow);
		
		Button proceedButton = new Button("Proceed");
		proceedButton.setOnMouseClicked((e) -> {
			Visit current = dataController.getCurrentVisit();
			current.setState("FINDINGS");
			dataController.saveVisit(current);
			dataController.setCurrentVisit(null);
			screenController.moveToScreen("nurseHomeScreen");
		});
		HBox row = new HBox(proceedButton);
		row.setAlignment(Pos.CENTER);
		content.getChildren().add(row);
		
		return layout;
	}
}
