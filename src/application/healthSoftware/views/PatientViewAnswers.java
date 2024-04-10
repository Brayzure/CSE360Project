package application.healthSoftware.views;

import java.util.ArrayList;
import java.util.List;

import application.healthSoftware.DataController;
import application.healthSoftware.ScreenController;
import application.healthSoftware.data.Question;
import application.healthSoftware.data.User;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class PatientViewAnswers implements IScreen {
	public static String ScreenID = "patientViewAnswers";
	
	private ScreenController screenController;
	private DataController dataController;
	
	private List<Question> questionList = new ArrayList<Question>();
	private Question currentQuestion;
		
	public PatientViewAnswers(ScreenController sc) {
		screenController = sc;
		dataController = DataController.getInstance();
	}
	
	public void refreshData() {
		User currentUser = dataController.getCurrentUser();
		currentQuestion = null;
		questionList = dataController.getAllAnsweredQuestionsForPatient(currentUser.patientProfile.patientID);
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
			Label answerLabel = new Label("Staff Answers: " + currentQuestion.answer);
			HBox questionRow = new HBox(questionLabel);
			questionRow.setAlignment(Pos.CENTER);
			HBox answerRow = new HBox(answerLabel);
			answerRow.setAlignment(Pos.CENTER);

			questionDetails.getChildren().add(questionRow);
			questionDetails.getChildren().add(answerRow);
		}
		
		content.getChildren().add(questionListBox);
		content.getChildren().add(questionDetails);
		layout.getChildren().add(content);
		
		HBox returnRow = new HBox();
		Button returnButton = new Button("Back");
		returnButton.setOnMouseClicked((e) -> {
			screenController.moveToScreen("patientHomeScreen");
		});
		returnRow.getChildren().add(returnButton);
		returnRow.setAlignment(Pos.CENTER);
		
		layout.getChildren().add(returnRow);
		
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
