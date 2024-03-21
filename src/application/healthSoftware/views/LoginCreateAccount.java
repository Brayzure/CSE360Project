package application.healthSoftware.views;

import application.healthSoftware.DataController;
import application.healthSoftware.ScreenController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class LoginCreateAccount implements IScreen {
	public static String ScreenID = "loginCreateAccount";
	
	private ScreenController screenController;
	private DataController dataController;
	
	public LoginCreateAccount(ScreenController sc) {
		screenController = sc;
		dataController = DataController.getInstance();
	}
	
	public void refreshData() {
		
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
		
		
		// Create username controls
		Label userNameLabel = new Label("Username: ");
		TextField userNameInput = new TextField();
		userNameInput.setMaxWidth(200);
		//Create password controls
		Label passWordLabel = new Label("Password: ");
		TextField passWordInput = new TextField();
		passWordInput.setMaxWidth(200);
		
		HBox inputLine = new HBox(userNameLabel, userNameInput);
		HBox passwordInputLine = new HBox(passWordLabel, passWordInput);
		inputLine.setAlignment(Pos.CENTER);
		passwordInputLine.setAlignment(Pos.CENTER);
		VBox inputSection = new VBox(inputLine, passwordInputLine);
		inputSection.setPadding(new Insets(15, 12, 15, 12));
		inputSection.setSpacing(50);
		inputSection.setAlignment(Pos.CENTER_LEFT);
		content.getChildren().add(inputSection);
		
		Button registerButton = new Button("Create Account");
		registerButton.setOnMouseClicked((e) -> {
			screenController.moveToScreen("loginScreen");
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
