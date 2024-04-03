package application.healthSoftware.views;

import application.healthSoftware.DataController;
import application.healthSoftware.ScreenController;
import application.healthSoftware.data.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class VisitVitals implements IScreen {
	public static String ScreenID = "visitVitals";
	
	private ScreenController screenController;
	private DataController dataController;
	
	private int height;
	private int weight;
	private int bodyTemp;
	private int systolicBP;
	private int diastolicBP;
	
	public VisitVitals(ScreenController sc) {
		screenController = sc;
		dataController = DataController.getInstance();
	}
	
	public void refreshData() {
		
	}
	
	public Region getLayout() {
		VBox layout = new VBox();
		
		HBox titleRow = new HBox();
		titleRow.setAlignment(Pos.CENTER);
		Label title = new Label("Patient Vitals");
		title.setFont(new Font(48));
		titleRow.getChildren().add(title);
		layout.getChildren().add(titleRow);
		
		VBox content = new VBox();
		content.setAlignment(Pos.CENTER);
		content.setPrefHeight(500);
		content.setSpacing(15);
		layout.getChildren().add(content);
		
		HBox heightInput = makeCenteredInputElement("Height");
		TextField heightField = (TextField) heightInput.getChildren().get(0);
		
		heightField.textProperty().addListener((observable, oldValue, newValue) -> {
			height = Integer.parseInt(newValue);
		});
		
		HBox weightInput = makeCenteredInputElement("Weight");
		TextField weightField = (TextField) weightInput.getChildren().get(0);
		
		weightField.textProperty().addListener((observable, oldValue, newValue) -> {
			weight = Integer.parseInt(newValue);
		});
		
		HBox bodyTempInput = makeCenteredInputElement("Body Temperature");
		TextField bodyTempField = (TextField) bodyTempInput.getChildren().get(0);
		
		bodyTempField.textProperty().addListener((observable, oldValue, newValue) -> {
			bodyTemp = Integer.parseInt(newValue);
		});
		
		HBox systolicInput = makeCenteredInputElement("Systolic BP");
		TextField systolicField = (TextField) systolicInput.getChildren().get(0);
		
		systolicField.textProperty().addListener((observable, oldValue, newValue) -> {
			systolicBP = Integer.parseInt(newValue);
		});
		
		HBox diastolicInput = makeCenteredInputElement("Diastolic BP");
		TextField diastolicField = (TextField) diastolicInput.getChildren().get(0);
		
		diastolicField.textProperty().addListener((observable, oldValue, newValue) -> {
			diastolicBP = Integer.parseInt(newValue);
		});

		content.getChildren().add(heightInput);
		content.getChildren().add(weightInput);
		content.getChildren().add(bodyTempInput);
		content.getChildren().add(systolicInput);
		content.getChildren().add(diastolicInput);
		
		Button registerButton = new Button("Register Vitals");
		registerButton.setOnMouseClicked((e) -> {
			PatientVitals newPatientVitals = new PatientVitals(height, weight, bodyTemp, systolicBP, diastolicBP);
			Visit current = dataController.getCurrentVisit();
			current.setVitals(newPatientVitals);
			current.setState("VITALS");
			dataController.saveVisit(current);
			screenController.moveToScreen("visitExamRoom");
		});
		HBox row = new HBox(registerButton);
		row.setAlignment(Pos.CENTER);
		content.getChildren().add(row);
		
		return layout;
	}
	
	private HBox makeCenteredInputElement(String placeholder) {
		TextField input = new TextField();
		input.setPromptText(placeholder);
		HBox row = new HBox(input);
		row.setAlignment(Pos.CENTER);
		
		return row;
	}
}
