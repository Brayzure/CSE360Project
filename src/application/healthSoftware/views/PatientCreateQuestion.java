package application.healthSoftware.views;

import application.healthSoftware.DataController;
import application.healthSoftware.ScreenController;
import application.healthSoftware.data.Question;
import application.healthSoftware.data.User;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PatientCreateQuestion implements IScreen {
	public static String ScreenID = "patientCreateQuestion";
	
	private ScreenController screenController;
	private DataController dataController;
	
	private String question;
		
	public PatientCreateQuestion(ScreenController sc) {
		screenController = sc;
		dataController = DataController.getInstance();
	}
	
	public void refreshData() {
		question = "";
	}
		
	public Region getLayout() {
		VBox layout = new VBox();
		layout.setAlignment(Pos.CENTER);
		
		// Question input
		Label questionLabel = new Label("Question: ");
		questionLabel.setFont(new Font(30));
		TextArea questionInput = new TextArea();
		questionInput.setWrapText(true);
		questionInput.setMaxWidth(500);
		questionInput.textProperty().addListener((observable, oldValue, newValue) -> {
			question = newValue;
		});
		
		VBox inputLine = new VBox(questionLabel, questionInput);
		inputLine.setAlignment(Pos.CENTER);
		inputLine.setSpacing(10);
		
		layout.getChildren().add(inputLine);
		
		// Send question button, with logic
		Button sendQuestionButton = new Button("Ask Question");
		sendQuestionButton.setOnMouseClicked((e) -> {
			// Ensure question has values
			if(question.isEmpty()) {
				Alert error = new Alert(AlertType.ERROR);
				error.setHeaderText("Missing Information");
				error.setContentText("Please enter a question to ask the medical staff!");
				error.showAndWait();
				return;
			}
			
			Question q = new Question(question);
			User currentUser = dataController.getCurrentUser();
			q.patientID = currentUser.patientProfile.patientID;
			dataController.saveQuestion(q);
			
			screenController.moveToScreen("patientHomeScreen");
		});
		
		HBox row = new HBox(sendQuestionButton);
		row.setAlignment(Pos.CENTER);
		row.setSpacing(20);
		
		layout.getChildren().add(row);
		layout.setSpacing(10);
		
		return layout;
	}
}