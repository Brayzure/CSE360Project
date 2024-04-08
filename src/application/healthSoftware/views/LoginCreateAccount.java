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
import javafx.scene.control.TextArea;
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
	
	public void refreshData() {
		mockUser = new User();
		mockUser.userType = "patient";
	}
	
	public Region getLayout() {
		VBox layout = new VBox();
		
		HBox titleRow = new HBox();
		titleRow.setAlignment(Pos.CENTER);
		Label title = new Label("Create Account");
		title.setFont(new Font(48));
		titleRow.getChildren().add(title);
		layout.getChildren().add(titleRow);
		
		VBox content = new VBox();
		content.setAlignment(Pos.CENTER);
		content.setPrefHeight(500);
		content.setSpacing(15);
		layout.getChildren().add(content);
		
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
			System.out.println(mockUser.toString());
		});
		
		// Create password controls
		Label passwordLabel = new Label("Password: ");
		TextField passwordInput = new PasswordField();
		passwordInput.setMaxWidth(200);
		passwordInput.textProperty().addListener((observable, oldValue, newValue) -> {
			mockUser.setPassword(newValue);
			System.out.println(mockUser.toString());
		});
		
		// Create password controls
		Label firstNameLabel = new Label("First Name: ");
		TextField firstNameInput = new TextField();
		firstNameInput.setMaxWidth(200);
		firstNameInput.textProperty().addListener((observable, oldValue, newValue) -> {
			mockUser.firstName = newValue;
			System.out.println(mockUser.toString());
		});
		
		// Create password controls
		Label lastNameLabel = new Label("Last Name: ");
		TextField lastNameInput = new TextField();
		lastNameInput.setMaxWidth(200);
		lastNameInput.textProperty().addListener((observable, oldValue, newValue) -> {
			mockUser.lastName = newValue;
			System.out.println(mockUser.toString());
		});
		
		// Create password controls
		Label birthdayLabel = new Label("Birthday: ");
		TextField birthdayInput = new TextField();
		birthdayInput.setMaxWidth(200);
		birthdayInput.textProperty().addListener((observable, oldValue, newValue) -> {
			mockUser.birthday = newValue;
			System.out.println(mockUser.toString());
		});
		
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
		
		Button registerButton = new Button("Create Account");
		registerButton.setOnMouseClicked((e) -> {
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
			User tempUser = dataController.getUserByUsername(mockUser.username);
			if(tempUser == null) {
				PatientProfile newProfile = new PatientProfile();
				newProfile.firstName = mockUser.firstName;
				newProfile.lastName = mockUser.lastName;
				newProfile.birthday = mockUser.birthday;
				
				mockUser.generateUserID();
				mockUser.patientProfile = newProfile;
				dataController.saveUser(mockUser);
				screenController.moveToScreen("loginScreen");
			}
			else {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Username Taken");
				error.setContentText("The username you entered has already been taken, please choose another one.");
				error.showAndWait();
			}
		});
		
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
	
	private HBox makeCenteredInputElement(String placeholder) {
		TextField input = new TextField();
		input.setPromptText(placeholder);
		HBox row = new HBox(input);
		row.setAlignment(Pos.CENTER);
		
		return row;
	}
}
