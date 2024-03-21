package application.healthSoftware.views;

import application.healthSoftware.DataController;
import application.healthSoftware.ScreenController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class VisitExamQuestions implements IScreen {
	public static String ScreenID = "visitExamQuestions";
	
	private ScreenController screenController;
	private DataController dataController;
	
	public VisitExamQuestions(ScreenController sc) {
		screenController = sc;
		dataController = DataController.getInstance();
	}
	
	public void refreshData() {
		
	}
	
	public Region getLayout() {
		VBox layout = new VBox();
		
		HBox titleRow = new HBox();
		titleRow.setAlignment(Pos.CENTER);
		Label title = new Label("Examination Questions");
		title.setFont(new Font(48));
		titleRow.getChildren().add(title);
		layout.getChildren().add(titleRow);
		
		VBox content = new VBox();
		content.setAlignment(Pos.CENTER);
		content.setPrefHeight(500);
		content.setSpacing(15);
		layout.getChildren().add(content);

		content.getChildren().add(makeCenteredInputElement("Allergies"));
		content.getChildren().add(makeCenteredInputElement("Health Concerns"));
		
		Button registerButton = new Button("Next");
		registerButton.setOnMouseClicked((e) -> {
			screenController.moveToScreen("visitPatientOverview");
		});
		HBox row = new HBox(registerButton);
		row.setAlignment(Pos.CENTER);
		content.getChildren().add(row);
		
		return layout;
	}
	
	private HBox makeCenteredInputElement(String placeholder) {
		TextArea input = new TextArea();
		input.setPromptText(placeholder);
		HBox row = new HBox(input);
		row.setAlignment(Pos.CENTER);
		
		return row;
	}
}
