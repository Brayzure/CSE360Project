package application.healthSoftware.views;

import application.healthSoftware.DataController;
import application.healthSoftware.ScreenController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
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

public class NurseHomeScreen implements IScreen {
	public static String ScreenID = "nurseHomeScreen";
	
	private ScreenController screenController;
	private DataController dataController;
	
	public NurseHomeScreen(ScreenController sc) {
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
		Label title = new Label("Nurse Home Screen");
		title.setFont(new Font(48));
		titleRow.getChildren().add(title);
		layout.getChildren().add(titleRow);
		
		// Intermediate layout
		VBox content = new VBox();
		content.setAlignment(Pos.CENTER);
		content.setPrefHeight(500);
		content.setSpacing(50);
		layout.getChildren().add(content);
		
		// Begin Visit button
		HBox row = new HBox();
		row.setAlignment(Pos.CENTER);
		row.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		row.setMaxWidth(200);
		row.setPrefHeight(50);
		
		row.setOnMouseClicked((e) -> {
			screenController.moveToScreen("visitPatientLookup");
		});
		
		row.getChildren().add(new Label("Begin Visit"));
		
		// Questions button
		HBox row2 = new HBox();
		row2.setAlignment(Pos.CENTER);
		row2.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		row2.setMaxWidth(200);
		row2.setPrefHeight(50);
		
		row2.setOnMouseClicked((e) -> {
			screenController.moveToScreen("staffViewQuestions");
		});
		
		int length = dataController.getAllUnansweredQuestions().size();
		row2.getChildren().add(new Label("View Questions (" + String.valueOf(length) + " unanswered)"));
		content.getChildren().add(row);
		content.getChildren().add(row2);		
		
		return layout;
	}
}
