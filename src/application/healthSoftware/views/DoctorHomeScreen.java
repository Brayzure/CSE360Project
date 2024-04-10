package application.healthSoftware.views;

import java.util.List;

import application.healthSoftware.DataController;
import application.healthSoftware.ScreenController;
import application.healthSoftware.data.*;
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

public class DoctorHomeScreen implements IScreen {
	public static String ScreenID = "doctorHomeScreen";
	
	private ScreenController screenController;
	private DataController dataController;
	
	public DoctorHomeScreen(ScreenController sc) {
		screenController = sc;
		dataController = DataController.getInstance();
	}
	
	public void refreshData() {
		
	}
	
	public Region getLayout() {
		VBox layout = new VBox();
		layout.setSpacing(30);
		
		HBox titleRow = new HBox();
		titleRow.setAlignment(Pos.CENTER);
		Label title = new Label("Doctor Home Screen");
		title.setFont(new Font(48));
		titleRow.getChildren().add(title);
		layout.getChildren().add(titleRow);
		
		VBox content = new VBox();
		content.setAlignment(Pos.CENTER);
		content.setPrefHeight(500);
		layout.getChildren().add(content);
		
		Label visitTitle = new Label("Current Visits");
		visitTitle.setFont(new Font(36));
		HBox visitTitleRow = new HBox();
		visitTitleRow.setAlignment(Pos.CENTER);
		visitTitleRow.getChildren().add(visitTitle);
		content.getChildren().add(visitTitleRow);
		
		Label visitTooltip = new Label("Click Visit to Begin");
		visitTooltip.setFont(new Font(16));
		HBox visitTooltipRow = new HBox();
		visitTooltipRow.setAlignment(Pos.CENTER);
		visitTooltipRow.getChildren().add(visitTooltip);
		content.getChildren().add(visitTooltipRow);
		
		/* for(int i = 0; i < 5; i++) {
			HBox row = new HBox();
			row.setAlignment(Pos.CENTER);
			row.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			row.setMaxWidth(500);
			row.setPrefHeight(50);
			row.getChildren().add(new Label("Visit " + i));
			row.setOnMouseClicked((e) -> {
				screenController.moveToScreen("visitExamFindings");
			});
			
			content.getChildren().add(row);
		} */
		
		List<Visit> visitList = dataController.getAllVisitsWithState("FINDINGS");
		if(visitList.isEmpty()) {
			HBox row = new HBox();
			row.setAlignment(Pos.CENTER);
			row.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			row.setMaxWidth(500);
			row.setPrefHeight(50);
			row.getChildren().add(new Label("No Visits"));
			row.setOnMouseClicked((e) -> {
				screenController.moveToScreen("visitExamFindings");
			});
			content.getChildren().add(row);
		}
		else {
			for(Visit visits: visitList) {
				PatientProfile visitingPatient = dataController.getPatientProfile(visits.patientID);
				
				HBox row = new HBox();
				row.setAlignment(Pos.CENTER);
				row.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
				row.setMaxWidth(500);
				row.setPrefHeight(50);
				row.getChildren().add(new Label("Patient: " + visitingPatient.lastName + ", " + visitingPatient.firstName));
				row.setOnMouseClicked((e) -> {
					dataController.setCurrentVisit(visits);
					screenController.moveToScreen("visitExamFindings");
				});
				
				content.getChildren().add(row);
			}
		}
		
		VBox messageContent = new VBox();
		messageContent.setAlignment(Pos.CENTER);
		
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
		
		messageContent.getChildren().add(row2);
		layout.getChildren().add(messageContent);
		
		return layout;
	}
}
