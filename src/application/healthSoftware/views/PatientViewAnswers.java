package application.healthSoftware.views;

import java.util.ArrayList;
import java.util.List;

import application.healthSoftware.DataController;
import application.healthSoftware.ScreenController;
import application.healthSoftware.data.Question;
import application.healthSoftware.data.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PatientViewAnswers implements IScreen {
	public static String ScreenID = "patientViewAnswers";
	
	private ScreenController screenController;
	private DataController dataController;
	
	private List<Question> questionList = new ArrayList<Question>();
	private Question currentQuestion;
	private ScrollPane questionScrollPane = new ScrollPane();
		
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
		
		questionScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		questionScrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		
		HBox content = new HBox();
		
		// Question list on the left
		VBox questionListBox = new VBox();
		Label questionListLabel = new Label("Questions you sent");
		questionListLabel.setFont(new Font(30));
		
		VBox questionListLabelBox = new VBox(questionListLabel,questionScrollPane);
		questionListLabelBox.setSpacing(10);
		questionListLabelBox.setAlignment(Pos.TOP_CENTER);
		
		// Question details in the center
		VBox questionDetails = new VBox();
		questionListBox.setPrefWidth(300);
		questionDetails.setPrefWidth(500);
		
		// Add our questions
		for(Question q : questionList) {
			HBox row = createQuestionItem(q);
			questionListBox.getChildren().add(row);
		}
		
		questionScrollPane.setContent(questionListBox);
		
		// Define layout for question details
		Text questionLabel = new Text("Patient Asks: ");
		questionLabel.setFont(new Font(30));
		Text answerLabel = new Text("Staff Answers: ");
		answerLabel.setFont(new Font(30));
		TextArea questionText = new TextArea();
		questionText.setEditable(false);
		questionText.setWrapText(true);
		TextArea answerText = new TextArea();
		answerText.setEditable(false);
		answerText.setWrapText(true);
		VBox questionColumn = new VBox(questionLabel, questionText);
		HBox questionRow = new HBox(questionColumn);
		questionRow.setAlignment(Pos.CENTER);
		VBox answerColumn = new VBox(answerLabel, answerText);
		HBox answerRow = new HBox(answerColumn);
		answerRow.setAlignment(Pos.CENTER);

		questionDetails.getChildren().add(questionRow);
		questionDetails.getChildren().add(answerRow);
		
		if(currentQuestion != null) {
			questionText.setText(currentQuestion.question);
			answerText.setText(currentQuestion.answer);
		}
		
		content.getChildren().add(questionListLabelBox);
		content.getChildren().add(questionDetails);
		content.setSpacing(10);
		layout.getChildren().add(content);
		
		Button returnButton = new Button("Back");
		returnButton.setOnMouseClicked((e) -> {
			screenController.moveToScreen("patientHomeScreen");
		});
		
		returnButton.setAlignment(Pos.CENTER_LEFT);
		questionDetails.getChildren().add(returnButton);
		questionDetails.setSpacing(10);
		
		layout.setMargin(content, new Insets(0,0,0,10));
		layout.setAlignment(Pos.CENTER);
		return layout;
	}
	
	// Make a layout for a Question on the left
	public HBox createQuestionItem(Question q) {
		HBox row = new HBox();
		Label questionLabel = new Label(q.question);
		questionLabel.setFont(new Font(15));
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
