package application.healthSoftware.views;

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
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import application.healthSoftware.DataController;
import application.healthSoftware.ScreenController;
import application.healthSoftware.data.User;

public class LoginScreen implements IScreen {
	public static String ScreenID = "loginScreen";
	
	ScreenController screenController;
	DataController dataController;
	
	private String username;
	private String password;
	private String userType;
	
	public LoginScreen(ScreenController sc) {
		screenController = sc;
		dataController = DataController.getInstance();
		userType = "patient";
	}
	
	public void refreshData() {
		
	}
	
	public Region getLayout() {
		VBox layout = new VBox();
		layout.setAlignment(Pos.CENTER);
		
		// Create username controls
		Label usernameLabel = new Label("Username: ");
		TextField usernameInput = new TextField();
		usernameInput.setMaxWidth(200);
		usernameInput.textProperty().addListener((observable, oldValue, newValue) -> {
			username = newValue;
		});
		
		// Create password controls
		Label passwordLabel = new Label("Password: ");
		TextField passwordInput = new PasswordField();
		passwordInput.setMaxWidth(200);
		passwordInput.textProperty().addListener((observable, oldValue, newValue) -> {
			password = newValue;
		});
		
		HBox inputLine = new HBox(usernameLabel, usernameInput);
		HBox passwordInputLine = new HBox(passwordLabel, passwordInput);
		
		inputLine.setAlignment(Pos.CENTER);
		passwordInputLine.setAlignment(Pos.CENTER);
		
		VBox inputSection = new VBox(inputLine, passwordInputLine);
		inputSection.setPadding(new Insets(15, 12, 15, 12));
		inputSection.setSpacing(50);
		inputSection.setAlignment(Pos.CENTER_LEFT);
		
		layout.getChildren().add(inputSection);
		
		Button loginButton = new Button("Log In");
		loginButton.setOnMouseClicked((e) -> {
			// Username or password not provided
			if(username.equals("") || password.equals("")) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Missing Fields");
				error.setContentText("All fields must be completed before submitting.");
				error.showAndWait();
				return;
			}
			
			boolean hasLoginError = false;
			User loginUser = dataController.getUserByUsername(username);
			if(loginUser == null) {
				hasLoginError = true;
			}
			else {
				hasLoginError = !loginUser.testPassword(password);
			}
			
			if(hasLoginError) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Incorrect Information");
				error.setContentText("Username or password is not correct.");
				error.showAndWait();
				return;
			}
			else {
				dataController.setCurrentUser(loginUser);
				switch(loginUser.userType) {
					case "patient":
						screenController.moveToScreen("patientHomeScreen");
						break;
					case "nurse":
						screenController.moveToScreen("nurseHomeScreen");
						break;
					case "doctor":
						screenController.moveToScreen("doctorHomeScreen");
						break;
					default:
						Alert error = new Alert(AlertType.ERROR);
						error.setHeaderText("System Error");
						error.setContentText("Unrecognized user type.");
						System.out.println("Did not recognize user type " + loginUser.userType);
						error.showAndWait();
				}
			}
		});
		
		Button createAccountButton = new Button("Create Account");
		createAccountButton.setOnMouseClicked((e) -> {
			screenController.moveToScreen("loginCreateAccount");
		});
		
		HBox row = new HBox(loginButton, createAccountButton);
		row.setAlignment(Pos.CENTER);
		row.setSpacing(20);
		
		layout.getChildren().add(row);
		
		return layout;
	}
	
	public HBox createTop() { //Creates an HBox at the top of the page
    	HBox hbox = new HBox();
    	hbox.setPadding(new Insets(15, 12, 15, 12)); //Styling, aligns text, sets padding and spacing
    	hbox.setSpacing(10);
    	hbox.setAlignment(Pos.CENTER);
    	
    	Text title = new Text("Welcome to ASU Hospital Center"); //Sets text that says Joe's Deli at the top of the page, along with font style
    	title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 17));
    	hbox.getChildren().add(title);
    	return hbox;
    }
}
