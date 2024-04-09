package application.healthSoftware.views;

import java.util.ArrayList;
import java.util.List;

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

public class StaffViewQuestions implements IScreen {
	public static String ScreenID = "staffViewQuestions";
	
	private ScreenController screenController;
	private DataController dataController;
	
	private List<Question> questionList = new ArrayList<Question>();
	private Question currentQuestion;
		
	public StaffViewQuestions(ScreenController sc) {
		screenController = sc;
		dataController = DataController.getInstance();
	}
	
	public void refreshData() {
		questionList = dataController.getAllUnansweredQuestions();
	}
		
	public Region getLayout() {
		VBox layout = new VBox();
		layout.setAlignment(Pos.CENTER);
		
		HBox content = new HBox();
		VBox questionListBox = new VBox();
		VBox questionDetails = new VBox();
		questionListBox.setPrefWidth(300);
		questionDetails.setPrefWidth(500);
		
		for(Question q : questionList) {
			HBox row = createQuestionItem(q);
			questionListBox.getChildren().add(row);
		}
		
		if(currentQuestion != null) {
			Label questionLabel = new Label("Patient Asks: " + currentQuestion.question);
			Label answerLabel = new Label("Enter your answer here:");
			TextArea answerInput = new TextArea();
			answerInput.textProperty().addListener((observable, oldValue, newValue) -> {
				currentQuestion.answer = newValue;
			});
			HBox questionRow = new HBox(questionLabel);
			questionRow.setAlignment(Pos.CENTER);
			VBox answerCol = new VBox(answerLabel, answerInput);
			answerCol.setAlignment(Pos.CENTER);

			questionDetails.getChildren().add(questionRow);
			questionDetails.getChildren().add(answerCol);
		}
		
		content.getChildren().add(questionListBox);
		content.getChildren().add(questionDetails);
		layout.getChildren().add(content);
		
		HBox buttonRow = new HBox();
		Button returnButton = new Button("Back");
		returnButton.setOnMouseClicked((e) -> {
			screenController.moveToPreviousScreen();
		});
		buttonRow.getChildren().add(returnButton);
		if(currentQuestion != null) {

			Button answerButton = new Button("Submit Answer");
			answerButton.setOnMouseClicked((e) -> {
				if(currentQuestion.answer.isEmpty()) {
					Alert error = new Alert(AlertType.ERROR);
					error.setHeaderText("Missing Information");
					error.setContentText("Please enter an answer!!");
					error.showAndWait();
					return;
				}
				else {
					dataController.saveQuestion(currentQuestion);
					currentQuestion = null;
					refreshData();
					screenController.moveToScreen(ScreenID);
				}
			});
			
			buttonRow.getChildren().add(answerButton);
		}
		buttonRow.setAlignment(Pos.CENTER);
		
		layout.getChildren().add(buttonRow);
		
		return layout;
	}
	
	public HBox createQuestionItem(Question q) {
		HBox row = new HBox();
		Label questionLabel = new Label(q.question);
		row.getChildren().add(questionLabel);
		row.setAlignment(Pos.CENTER);
		row.setUserData(q);
		row.setOnMouseClicked((e) -> {
			Question question = (Question) row.getUserData();
			currentQuestion = question;
			screenController.moveToScreen(ScreenID);
		});
		return row;
	}
}
