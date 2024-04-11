package application.healthSoftware.views;

import application.healthSoftware.DataController;
import application.healthSoftware.ScreenController;
import application.healthSoftware.data.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class VisitExamFindings implements IScreen {
	public static String ScreenID = "visitExamFindings";
	
	private ScreenController screenController;
	private DataController dataController;
	
	private String examFindings = "";
	
	public VisitExamFindings(ScreenController sc) {
		screenController = sc;
		dataController = DataController.getInstance();
	}
	
	public void refreshData() {
		
	}
	
	public Region getLayout() {
		VBox layout = new VBox();
		
		// Title
		HBox titleRow = new HBox();
		titleRow.setAlignment(Pos.CENTER);
		Label title = new Label("Exam Findings");
		title.setFont(new Font(48));
		titleRow.getChildren().add(title);
		layout.getChildren().add(titleRow);
		
		// Intermediate layout
		VBox content = new VBox();
		content.setAlignment(Pos.CENTER);
		content.setPrefHeight(500);
		content.setSpacing(15);
		layout.getChildren().add(content);

		// Input
		content.getChildren().add(makeCenteredInputElement("Exam Findings"));
		
		VBox examFindingsVBox = (VBox) content.getChildren().get(0);
		HBox examFindingsHBox = (HBox) examFindingsVBox.getChildren().get(1);
		TextArea examFindingsInput = (TextArea) examFindingsHBox.getChildren().get(0);
		
		examFindingsInput.textProperty().addListener((observable, oldValue, newValue) -> {
			examFindings = newValue;
		});
		
		// Proceed button
		Button registerButton = new Button("Next");
		registerButton.setOnMouseClicked((e) -> {
			Visit cVisit = dataController.getCurrentVisit();
			cVisit.setFindings(examFindings);
			cVisit.setState("PRESCRIPTIONS");
			dataController.saveVisit(cVisit);
			screenController.moveToScreen("visitPrescriptions");
		});
		HBox row = new HBox(registerButton);
		row.setAlignment(Pos.CENTER);
		content.getChildren().add(row);
		
		return layout;
	}
	
	// Isolate some layout code here
	private VBox makeCenteredInputElement(String placeholder) {
		Label fieldTitle = new Label(placeholder);
		fieldTitle.setFont(new Font(18));
		HBox row1 = new HBox(fieldTitle);
		row1.setAlignment(Pos.CENTER);
		
		TextArea input = new TextArea();
		input.setPromptText(placeholder);
		HBox row2 = new HBox(input);
		row2.setAlignment(Pos.CENTER);
		
		VBox content = new VBox(row1, row2);
		
		return content;
	}
}
