package application.healthSoftware.views;

import application.healthSoftware.DataController;
import application.healthSoftware.ScreenController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
	
	private ScreenController screenController;
	private DataController dataController;
	
	public VisitPatientLookup(ScreenController sc) {
		screenController = sc;
		dataController = DataController.getInstance();
	}
	
	public void refreshData() {
		
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

		content.getChildren().add(makeCenteredInputElement("First Name"));
		content.getChildren().add(makeCenteredInputElement("Last Name"));
		content.getChildren().add(makeCenteredInputElement("Birthday"));
		
		Button registerButton = new Button("Register Patient");
		registerButton.setOnMouseClicked((e) -> {
			screenController.moveToScreen("visitVitals");
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
