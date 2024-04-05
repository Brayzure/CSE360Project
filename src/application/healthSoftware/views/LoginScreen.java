package application.healthSoftware.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import application.healthSoftware.ScreenController;

public class LoginScreen implements IScreen {
	public static String ScreenID = "loginScreen";
	
	ScreenController screenController;
	
	private String userName;
	private String passWord;
	private String userType;
	
	public LoginScreen(ScreenController sc) {
		screenController = sc;
		userType = "patient";
	}
	
	public void refreshData() {
		
	}
	
	public Region getLayout() {
		// Create username controls
		Label userNameLabel = new Label("Username: ");
		TextField userNameInput = new TextField();
		userNameInput.setMaxWidth(200);
		//Create password controls
		Label passWordLabel = new Label("Password: ");
		TextField passWordInput = new TextField();
		passWordInput.setMaxWidth(200);
		// If we have a first name stored already, prepopulate relevant fields
		if(userName != null) {
			userNameInput.textProperty().set(userName);
		}
		if(passWord != null) {
			passWordInput.textProperty().set(passWord);
		}
		
		// Login button
		Button login = new Button("Log In");
		Button createAccount = new Button("Create Account");
		HBox loginButton = new HBox(login, createAccount);
		loginButton.setPadding(new Insets(15, 12, 15, 12));
		loginButton.setAlignment(Pos.CENTER);
		
		// Layout
		HBox inputLine = new HBox(userNameLabel, userNameInput);
		HBox passwordInputLine = new HBox(passWordLabel, passWordInput);
		VBox inputSection = new VBox(inputLine, passwordInputLine);
		inputSection.setPadding(new Insets(15, 12, 15, 12));
		inputSection.setSpacing(50);
		inputSection.setAlignment(Pos.CENTER_LEFT);
		BorderPane layout = new BorderPane(null, createTop() , createRight(), loginButton, inputSection);
		
		// Update saved value when input box value changes
		userNameInput.textProperty().addListener((observable, oldValue, newValue) -> {
			userName = newValue;
		});
		passWordInput.textProperty().addListener((observable, oldValue, newValue) -> {
			passWord = newValue;
		});
		
		// Change screen on button click
		login.setOnAction(e -> {
			screenController.moveToScreen(userType + "HomeScreen");
		});
		
		createAccount.setOnAction((e) -> {
			screenController.moveToScreen("loginCreateAccount");
		});
		
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
	
	private VBox createRight() {
    	VBox vbox = new VBox();
    	vbox.setPadding(new Insets(15, 50, 15, 15));
		vbox.setSpacing(45);
		vbox.setAlignment(Pos.CENTER_LEFT);
    	
		Label debugLabel = new Label("Current userType: " + userType);
    	
    	final ToggleGroup users = new ToggleGroup(); //Creates a toggle group for the radio buttons
    	String user[] = {"Patient", "Nurse", "Doctor"}; //Creates the radio buttons
    	for(int i = 0; i < user.length; i++) {
    		RadioButton userItem = new RadioButton(user[i]);
    		userItem.setToggleGroup(users);
    		userItem.setUserData(user[i].toLowerCase()); //Sets user data so that the name of the selection can be retrieved for the Bill
    		userItem.setOnMouseClicked((e) -> {
    			String type = userItem.getUserData().toString();
    			userType = type;
    			debugLabel.setText("Current userType: " + userType);
    		});
    		if(i == 0) {
    			userItem.setSelected(true);
    		}
    		vbox.getChildren().add(userItem);
    		//Add event for radio buttons
    	}
    	
    	vbox.getChildren().add(debugLabel);
    	
    	return vbox;
    }
}
