package application.healthSoftware.views;

import application.healthSoftware.DataController;
import application.healthSoftware.ScreenController;
import application.healthSoftware.Util;
import application.healthSoftware.data.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class VisitPatientLookup implements IScreen {
	public static String ScreenID = "visitPatientLookup";
	
	private String firstName;
	private String lastName;
	private String birthday;
	
	private PatientProfile currentPatientProfile;
	private boolean isNewProfile;
	
	private ScreenController screenController;
	private DataController dataController;
	
	public VisitPatientLookup(ScreenController sc) {
		screenController = sc;
		dataController = DataController.getInstance();
	}
	
	public void refreshData() {
		currentPatientProfile = null;
	}
	
	public Region getLayout() {
		VBox layout = new VBox();
		
		HBox titleRow = new HBox();
		titleRow.setAlignment(Pos.CENTER);
		Label title = new Label("Patient Lookup");
		title.setFont(new Font(48));
		titleRow.getChildren().add(title);
		layout.getChildren().add(titleRow);
		
		VBox content = new VBox();
		content.setAlignment(Pos.CENTER);
		content.setPrefHeight(500);
		content.setSpacing(15);
		layout.getChildren().add(content);
		
		/*
		TextField firstNameInput = new TextField();
		firstNameInput.setPromptText("First Name");
		HBox row1 = new HBox(firstNameInput);
		row1.setAlignment(Pos.CENTER);
		
		TextField lastNameInput = new TextField();
		lastNameInput.setPromptText("Last Name");
		HBox row2 = new HBox(lastNameInput);
		row2.setAlignment(Pos.CENTER);
		
		TextField birthdayInput = new TextField();
		birthdayInput.setPromptText("Birthday");
		HBox row3 = new HBox(birthdayInput);
		row3.setAlignment(Pos.CENTER);
		*/
		
		if(currentPatientProfile != null) {
			String status = "";
			if(isNewProfile) {
				status = "Proceeding will create a new patient profile for " + currentPatientProfile.firstName + " " + currentPatientProfile.lastName;
			}
			else {
				status = "Found an existing patient profile for " + currentPatientProfile.firstName + " " + currentPatientProfile.lastName;
			}
			Label statusLabel = new Label(status);
			HBox row = new HBox(statusLabel);
			row.setAlignment(Pos.CENTER);
			content.getChildren().add(row);
		}

		// Create password controls
		Label firstNameLabel = new Label("First Name: ");
		TextField firstNameInput = new TextField();
		firstNameInput.setMaxWidth(200);
		firstNameInput.textProperty().addListener((observable, oldValue, newValue) -> {
			firstName = newValue;
		});
		
		// Create password controls
		Label lastNameLabel = new Label("Last Name: ");
		TextField lastNameInput = new TextField();
		lastNameInput.setMaxWidth(200);
		lastNameInput.textProperty().addListener((observable, oldValue, newValue) -> {
			lastName = newValue;
		});
		
		// Create password controls
		Label birthdayLabel = new Label("Birthday: ");
		TextField birthdayInput = new TextField();
		birthdayInput.setMaxWidth(200);
		birthdayInput.textProperty().addListener((observable, oldValue, newValue) -> {
			birthday = newValue;
		});
		
		HBox firstNameLine = new HBox(firstNameLabel, firstNameInput);
		HBox lastNameLine = new HBox(lastNameLabel, lastNameInput);
		HBox birthdayLine = new HBox(birthdayLabel, birthdayInput);
		firstNameLine.setAlignment(Pos.CENTER);
		lastNameLine.setAlignment(Pos.CENTER);
		birthdayLine.setAlignment(Pos.CENTER);
		
		VBox inputSection = new VBox(firstNameLine, lastNameLine, birthdayLine);
		
		inputSection.setPadding(new Insets(15, 12, 15, 12));
		inputSection.setSpacing(50);
		inputSection.setAlignment(Pos.CENTER_LEFT);
		content.getChildren().add(inputSection);
		
		Button registerButton = new Button("Register Patient");
		registerButton.setOnMouseClicked((e) -> {
			PatientProfile tempP = dataController.searchForPatientProfile(firstName, lastName, birthday);
			if(tempP == null) {
				isNewProfile = true;
				tempP = new PatientProfile();
				tempP.firstName = firstName;
				tempP.lastName = lastName;
				tempP.birthday = birthday;
				System.out.println("Creating new profile");
			}
			else {
				isNewProfile = false;
				System.out.println("Found existing profile");
			}
			
			currentPatientProfile = tempP;
			
			screenController.moveToScreen(VisitPatientLookup.ScreenID);
		});
		
		Button proceedButton = new Button("Proceed");
		proceedButton.setOnMouseClicked((e) -> {
			if(currentPatientProfile == null) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("No Patient Registered");
				error.setContentText("You must look up a patient before proceeding.");
				error.showAndWait();
				return;
			}
			else {
				dataController.savePatientProfile(currentPatientProfile);
				dataController.setCurrentPatientProfile(currentPatientProfile);
			  String newVisitID = Util.generateID();
			  Visit newVisit = new Visit(newVisitID, currentPatientProfile.patientID);
			  newVisit.setState("VITALS");
			  dataController.setCurrentVisit(newVisit);
			  dataController.saveVisit(newVisit);
				screenController.moveToScreen("visitVitals");
			}
		});
		
		HBox row = new HBox(registerButton, proceedButton);
		row.setAlignment(Pos.CENTER);
		row.setSpacing(20);
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
