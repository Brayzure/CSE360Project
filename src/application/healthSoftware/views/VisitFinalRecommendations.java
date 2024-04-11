package application.healthSoftware.views;

import application.healthSoftware.DataController;
import application.healthSoftware.ScreenController;
import application.healthSoftware.data.Visit;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class VisitFinalRecommendations implements IScreen {
	public static String ScreenID = "visitFinalRecommendations";
	
	private ScreenController screenController;
	private DataController dataController;
	
	private String examRecommendations = "";
	
	public VisitFinalRecommendations(ScreenController sc) {
		screenController = sc;
		dataController = DataController.getInstance();
	}
	
	public void refreshData() {
		
	}
	
	public Region getLayout() {
		VBox layout = new VBox();
		
		// Title
		HBox titleRow = new HBox();
		titleRow.setAlignment(Pos.CENTER);
		Label title = new Label("Final Recommendations");
		title.setFont(new Font(48));
		titleRow.getChildren().add(title);
		layout.getChildren().add(titleRow);
		
		// Intermediate layout
		VBox content = new VBox();
		content.setAlignment(Pos.CENTER);
		content.setPrefHeight(500);
		content.setSpacing(15);
		layout.getChildren().add(content);

		content.getChildren().add(makeCenteredInputElement("Final Recommendations"));
		
		VBox examRecommendationsVBox = (VBox) content.getChildren().get(0);
		HBox examRecommendationsHBox = (HBox) examRecommendationsVBox.getChildren().get(1);
		TextArea examRecommendationsInput = (TextArea) examRecommendationsHBox.getChildren().get(0);
		
		// Update local variable
		examRecommendationsInput.textProperty().addListener((observable, oldValue, newValue) -> {
			examRecommendations = newValue;
		});
		
		// Final button, completes a visit
		Button registerButton = new Button("Next");
		registerButton.setOnMouseClicked((e) -> {
			Visit cVisit = dataController.getCurrentVisit();
			cVisit.setRecommendations(examRecommendations);
			cVisit.setState("COMPLETE");
			dataController.saveVisit(cVisit);
			dataController.setCurrentVisit(null);
			screenController.moveToScreen("doctorHomeScreen");
		});
		HBox row = new HBox(registerButton);
		row.setAlignment(Pos.CENTER);
		content.getChildren().add(row);
		
		return layout;
	}
	
	private VBox makeCenteredInputElement(String placeholder) {
		Label fieldTitle = new Label(placeholder);
		fieldTitle.setFont(new Font(18));
		HBox row1 = new HBox(fieldTitle);
		row1.setAlignment(Pos.CENTER);
		
		TextArea input = new TextArea();
		input.setPromptText(placeholder);
		HBox row2 = new HBox(input);
		row2.setAlignment(Pos.CENTER);
		
		VBox content = new VBox(row1, row2);
		
		return content;
	}
}
