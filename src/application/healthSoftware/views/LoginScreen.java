package application.healthSoftware.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
	public LoginScreen(ScreenController sc) {
		screenController = sc;
		dataController = DataController.getInstance();
	}
	
	public void refreshData() {
		
	}
	
	public Region getLayout() {
		VBox layout = new VBox();
		layout.setAlignment(Pos.CENTER);
		
		// Title
		Label title = new Label("ASU Hospital Center");
		title.setFont(new Font(48));
		HBox titleRow = new HBox(title);
		titleRow.setAlignment(Pos.CENTER);
		layout.getChildren().add(titleRow);
		
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
		
		// Put it all together
		HBox inputLine = new HBox(usernameLabel, usernameInput);
		HBox passwordInputLine = new HBox(passwordLabel, passwordInput);
		
		inputLine.setAlignment(Pos.CENTER);
		passwordInputLine.setAlignment(Pos.CENTER);
		
		VBox inputSection = new VBox(inputLine, passwordInputLine);
		inputSection.setPadding(new Insets(15, 12, 15, 12));
		inputSection.setSpacing(50);
		inputSection.setAlignment(Pos.CENTER_LEFT);
		
		layout.getChildren().add(inputSection);
		
		// Lgoin button logic
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
			// See if username is a match
			User loginUser = dataController.getUserByUsername(username);
			if(loginUser == null) {
				hasLoginError = true;
			}
			else {
				// Test if provided password works
				hasLoginError = !loginUser.testPassword(password);
			}
			
			// Had an error somewhere along the way, display
			if(hasLoginError) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Incorrect Information");
				error.setContentText("Username or password is not correct.");
				error.showAndWait();
				return;
			}
			// No error, login successful
			else {
				dataController.setCurrentUser(loginUser);
        
				// Determine home screen to proceed to
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
					// Ideally should never happen except perhaps with stale data
					default:
						Alert error = new Alert(AlertType.ERROR);
						error.setHeaderText("System Error");
						error.setContentText("Unrecognized user type.");
						System.out.println("Did not recognize user type " + loginUser.userType);
						error.showAndWait();
				}
			}
		});
		
		// Navigate to account creation screen
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
    	
    	Text title = new Text("Welcome to ASU Hospital Center");
    	title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 17));
    	hbox.getChildren().add(title);
    	return hbox;
    }
}
