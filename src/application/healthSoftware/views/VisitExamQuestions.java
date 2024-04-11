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

public class VisitExamQuestions implements IScreen {
	public static String ScreenID = "visitExamQuestions";
	
	private ScreenController screenController;
	private DataController dataController;
	
	private String allergies = "";
	private String healthConcerns = "";
	
	public VisitExamQuestions(ScreenController sc) {
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
		Label title = new Label("Examination Questions");
		title.setFont(new Font(48));
		titleRow.getChildren().add(title);
		layout.getChildren().add(titleRow);
		
		// Intermediate layout
		VBox content = new VBox();
		content.setAlignment(Pos.CENTER);
		content.setPrefHeight(500);
		content.setSpacing(15);
		layout.getChildren().add(content);
		
		/*
		 * Inputs
		 */
		VBox allergiesInput = makeCenteredInputElement("Allergies");
		HBox allergiesFieldRow = (HBox) allergiesInput.getChildren().get(1);
		TextArea allergiesField = (TextArea) allergiesFieldRow.getChildren().get(0);
		
		allergiesField.textProperty().addListener((observable, oldValue, newValue) -> {
			allergies = newValue;
		});
		
		VBox healthConcernsInput = makeCenteredInputElement("Health Concerns");
		HBox healthConcernsFieldRow = (HBox) healthConcernsInput.getChildren().get(1);
		TextArea healthConcernsField = (TextArea) healthConcernsFieldRow.getChildren().get(0);
		
		healthConcernsField.textProperty().addListener((observable, oldValue, newValue) -> {
			healthConcerns = newValue;
		});

		content.getChildren().add(allergiesInput);
		content.getChildren().add(healthConcernsInput);
		
		// Proceed button
		Button registerButton = new Button("Next");
		registerButton.setOnMouseClicked((e) -> {
			Visit current = dataController.getCurrentVisit();
			current.setAllergies(allergies);
			current.setHealthConcerns(healthConcerns);
			dataController.saveVisit(current);
			screenController.moveToScreen("nurseSummary");
		});
		HBox row = new HBox(registerButton);
		row.setAlignment(Pos.CENTER);
		content.getChildren().add(row);
		
		return layout;
	}
	
	private VBox makeCenteredInputElement(String placeholder) {
		Label label = new Label(placeholder);
		label.setFont(new Font(18));
		HBox row1 = new HBox(label);
		row1.setAlignment(Pos.CENTER);
		
		TextArea input = new TextArea();
		input.setPromptText(placeholder);
		HBox row2 = new HBox(input);
		row2.setAlignment(Pos.CENTER);
		VBox content = new VBox(row1, row2);
		
		return content;
	}
}
