package application.healthSoftware.views;

import application.healthSoftware.DataController;
import application.healthSoftware.ScreenController;
import application.healthSoftware.data.PatientProfile;
import application.healthSoftware.data.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class LoginCreateAccount implements IScreen {
	public static String ScreenID = "loginCreateAccount";
	
	private ScreenController screenController;
	private DataController dataController;
	
	private User mockUser = new User();
	
	public LoginCreateAccount(ScreenController sc) {
		screenController = sc;
		dataController = DataController.getInstance();
	}
	
	// Make a new mock user every time we enter this page
	public void refreshData() {
		mockUser = new User();
		mockUser.userType = "patient";
	}
	
	public Region getLayout() {
		VBox layout = new VBox();
		
		// Title
		HBox titleRow = new HBox();
		titleRow.setAlignment(Pos.CENTER);
		Label title = new Label("Create Account");
		title.setFont(new Font(48));
		titleRow.getChildren().add(title);
		layout.getChildren().add(titleRow);
		
		// Intermediate layout
		VBox content = new VBox();
		content.setAlignment(Pos.CENTER);
		content.setPrefHeight(500);
		content.setSpacing(15);
		layout.getChildren().add(content);
		
		// User type radio buttons
		Label userSelect = new Label("User Type");
		HBox userSelectRow = new HBox(userSelect);
		userSelectRow.setAlignment(Pos.CENTER);
		content.getChildren().add(userSelectRow);

		ToggleGroup userGroup = new ToggleGroup();
		RadioButton patientButton = new RadioButton("Patient");
		patientButton.setSelected(true);
		RadioButton nurseButton = new RadioButton("Nurse");
		RadioButton doctorButton = new RadioButton("Doctor");
		patientButton.setToggleGroup(userGroup);
		nurseButton.setToggleGroup(userGroup);
		doctorButton.setToggleGroup(userGroup);

		patientButton.setOnMouseClicked((e) -> {
			mockUser.userType = "patient";
		});
		nurseButton.setOnMouseClicked((e) -> {
			mockUser.userType = "nurse";
		});
		doctorButton.setOnMouseClicked((e) -> {
			mockUser.userType = "doctor";
		});
		
		HBox radioRow = new HBox(patientButton, nurseButton, doctorButton);
		radioRow.setAlignment(Pos.CENTER);
		radioRow.setSpacing(20);
		content.getChildren().add(radioRow);
		
		// Create username controls
		Label usernameLabel = new Label("Username: ");
		TextField usernameInput = new TextField();
		usernameInput.setMaxWidth(200);
		usernameInput.textProperty().addListener((observable, oldValue, newValue) -> {
			mockUser.username = newValue;
		});
		
		// Create password controls
		Label passwordLabel = new Label("Password: ");
		TextField passwordInput = new PasswordField();
		passwordInput.setMaxWidth(200);
		passwordInput.textProperty().addListener((observable, oldValue, newValue) -> {
			mockUser.setPassword(newValue);
		});
		
		// Create password controls
		Label firstNameLabel = new Label("First Name: ");
		TextField firstNameInput = new TextField();
		firstNameInput.setMaxWidth(200);
		firstNameInput.textProperty().addListener((observable, oldValue, newValue) -> {
			mockUser.firstName = newValue;
		});
		
		// Create password controls
		Label lastNameLabel = new Label("Last Name: ");
		TextField lastNameInput = new TextField();
		lastNameInput.setMaxWidth(200);
		lastNameInput.textProperty().addListener((observable, oldValue, newValue) -> {
			mockUser.lastName = newValue;
		});
		
		// Create password controls
		Label birthdayLabel = new Label("Birthday: ");
		TextField birthdayInput = new TextField();
		birthdayInput.setMaxWidth(200);
		birthdayInput.textProperty().addListener((observable, oldValue, newValue) -> {
			mockUser.birthday = newValue;
		});
		
		// Put everything together
		HBox inputLine = new HBox(usernameLabel, usernameInput);
		HBox passwordInputLine = new HBox(passwordLabel, passwordInput);
		HBox firstNameLine = new HBox(firstNameLabel, firstNameInput);
		HBox lastNameLine = new HBox(lastNameLabel, lastNameInput);
		HBox birthdayLine = new HBox(birthdayLabel, birthdayInput);
		inputLine.setAlignment(Pos.CENTER);
		passwordInputLine.setAlignment(Pos.CENTER);
		firstNameLine.setAlignment(Pos.CENTER);
		lastNameLine.setAlignment(Pos.CENTER);
		birthdayLine.setAlignment(Pos.CENTER);
		VBox inputSection = new VBox(inputLine, passwordInputLine, firstNameLine, lastNameLine, birthdayLine);
		inputSection.setPadding(new Insets(15, 12, 15, 12));
		inputSection.setSpacing(50);
		inputSection.setAlignment(Pos.CENTER_LEFT);
		content.getChildren().add(inputSection);
		
		// Register button
		Button registerButton = new Button("Create Account");
		registerButton.setOnMouseClicked((e) -> {
			// Check if any fields are empty
			if(usernameInput.getText().equals("") ||
					passwordInput.getText().equals("") ||
					firstNameInput.getText().equals("") ||
					lastNameInput.getText().equals("") ||
					birthdayInput.getText().equals(""))
			{
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Missing Fields");
				error.setContentText("All fields must be completed before submitting.");
				error.showAndWait();
				return;
			}
			// Everything is filled, check to see if username is taken
			User tempUser = dataController.getUserByUsername(mockUser.username);
			// Username not taken, proceed
			if(tempUser == null) {
				mockUser.generateUserID();
				String nextScreen = "loginScreen";
				
				// PatientProfile handling
				if(mockUser.userType.equals("patient")) {
					nextScreen = "patientPortalCreateAccount";
					// Search for pre-existing matching profile
					PatientProfile p = dataController.searchForPatientProfile(mockUser.firstName, mockUser.lastName, mockUser.birthday);
					// Match found, set and alter user ID
					if(p != null) {
						System.out.println("Found patient profile, seeing if it's already associated with an account");
						User u = dataController.getUser(p.patientID);
						if(u != null) {
							System.out.println("User account found that carries this patient profile");
							Alert error = new Alert(AlertType.ERROR);
							error.setHeaderText("Account Error");
							error.setContentText("Name and birthday already associated with a patient account, please log in.");
							error.showAndWait();
							return;
						}
						else {
							System.out.println("Patient profile not associated with an existing account, continuing.");
							mockUser.patientProfile = p;
							mockUser.userID = p.patientID;
						}
						
					}
					// Match not found, initialize
					else {
						System.out.println("No patient profile found, initializing.");
						PatientProfile newProfile = new PatientProfile();
						newProfile.patientID = mockUser.userID;
						newProfile.firstName = mockUser.firstName;
						newProfile.lastName = mockUser.lastName;
						newProfile.birthday = mockUser.birthday;
						mockUser.patientProfile = newProfile;
						dataController.savePatientProfile(newProfile);
					}
				}
				
				dataController.saveUser(mockUser);
				dataController.setCurrentPatientProfile(mockUser.patientProfile);
				screenController.moveToScreen(nextScreen);
			}
			// Username taken, error
			else {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Username Taken");
				error.setContentText("The username you entered has already been taken, please choose another one.");
				error.showAndWait();
			}
		});
		
		// Return button
		Button cancelButton = new Button("Cancel");
		cancelButton.setOnMouseClicked((e) -> {
			screenController.moveToScreen("loginScreen");
		});
		HBox row = new HBox(cancelButton, registerButton);
		row.setAlignment(Pos.CENTER);
		row.setSpacing(20);
		content.getChildren().add(row);
		
		return layout;
	}
}
