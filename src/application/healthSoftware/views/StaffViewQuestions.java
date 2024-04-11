package application.healthSoftware.views;

import java.util.ArrayList;
import java.util.List;

import application.healthSoftware.DataController;
import application.healthSoftware.ScreenController;
import application.healthSoftware.data.Question;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class StaffViewQuestions implements IScreen {
	public static String ScreenID = "staffViewQuestions";
	
	private ScreenController screenController;
	private DataController dataController;
	
	private List<Question> questionList = new ArrayList<Question>();
	private Question currentQuestion;
	private ScrollPane questionScrollPane = new ScrollPane();
	
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
		
		questionScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		questionScrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		
		//HBox that holds questions on the left and place to send messages on right
		HBox content = new HBox();
		
		//VBox that stores the current questions on the left side
		VBox questionListBox = new VBox();
		Label questionListLabel = new Label("Remaining Questions");
		questionListLabel.setFont(new Font(30));
		
		VBox questionListLabelBox = new VBox(questionListLabel,questionScrollPane);
		questionListLabelBox.setSpacing(10);
		//VBox that contains the question labels and text area
		VBox questionDetails = new VBox();
		questionListBox.setPrefWidth(300);
		questionListBox.setSpacing(10);
		questionDetails.setPrefWidth(500);
		
		for(Question q : questionList) {
			HBox row = createQuestionItem(q);
			questionListBox.getChildren().add(row);
		}
		
		questionScrollPane.setContent(questionListBox);
		
		Label questionLabel = new Label("Patient Asks: ");
		questionLabel.setFont(new Font(20));
		questionLabel.setWrapText(true);
		Label answerLabel = new Label("Enter your answer here:");
		answerLabel.setFont(new Font(15));
		TextArea answerInput = new TextArea();
		answerInput.setWrapText(true);
		
		//Adds question text to HBox
		HBox questionRow = new HBox(questionLabel);
		questionRow.setAlignment(Pos.CENTER);
		
		//Adds answer label and answer text area to VBox
		VBox answerCol = new VBox(answerLabel, answerInput);
		answerCol.setAlignment(Pos.CENTER);
		answerCol.setSpacing(10);
		
		//Adds question text, answer label, and answer text to VBox
		questionDetails.getChildren().add(questionRow);
		questionDetails.getChildren().add(answerCol);
		questionDetails.setSpacing(10);
		
		//Question needs to be clicked on to pull all this up
		if(currentQuestion != null) {
			questionLabel.setText("Patient Asks: " + currentQuestion.question);
			answerInput.textProperty().addListener((observable, oldValue, newValue) -> {
				currentQuestion.answer = newValue;
			});
			
			
		}
		
		//Adds stuff for the respond to question HBox
		questionListBox.setAlignment(Pos.TOP_CENTER);
		content.getChildren().add(questionListLabelBox);
		content.getChildren().add(questionDetails);
		content.setSpacing(20);
		
		//adds respond question HBox to screen
		layout.getChildren().add(content);
		
		//Making back button for when hospital views patient message
		HBox buttonRow = new HBox();
		Button returnButton = new Button("Back");
		returnButton.setOnMouseClicked((e) -> {
			currentQuestion = null;
			screenController.moveToPreviousScreen();
		});
		//Adds back button to button HBox
		buttonRow.getChildren().add(returnButton);
		
		//Question needs to be clicked on to pull all this up
		if(currentQuestion != null) {
			//Submits answer to patient question
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
			
			//Adds Submit Answer button to button HBox
			buttonRow.getChildren().add(answerButton);
		}
		
		//Aligns button HBox to center of screen
		buttonRow.setAlignment(Pos.CENTER_LEFT);
		buttonRow.setSpacing(355);
		
		//sets button row to be at the bottom of the page vbox
		questionDetails.getChildren().add(buttonRow);
		layout.setSpacing(5);
		layout.setMargin(content, new Insets(0,0,0,10));
		return layout;
	}
	public HBox createQuestionItem(Question q) {
		HBox row = new HBox();
		Label questionLabel = new Label(q.question);
		row.getChildren().add(questionLabel);
		questionLabel.setFont(new Font(15));
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
