package application.healthSoftware.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;

import java.util.ArrayList;

import application.healthSoftware.ScreenController;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;

public class MessageScreen implements IScreen {
	public static String ScreenID = "messageScreen";
	
	ScreenController screenController;
	
	//String to collect provider, subject and message from text fields and text areas
	String pastMessages = new String();
	//Placeholder label to contain providers and subjects for the inbox
	Label inbox = new Label("No Messages");
	
	public MessageScreen(ScreenController sc) {
		screenController = sc;
	}
	
	public void refreshData() {
		
	}
	
	public Region getLayout() {
		
		//All the stuff for the Compose message VBox
		TextField providerText = new TextField();
		TextField subjectText = new TextField();
		TextField searchMess = new TextField("Search Messages");
		TextArea sendMessageText = new TextArea();
		sendMessageText.setPrefWidth(400);
		Label composeMess = new Label("Compose New Message");
		Label provider = new Label("Provider");
		Label subject = new Label("Subject");
		Label inboxLbl = new Label("Inbox");
		Button sendMess = new Button();
		Button backButton = new Button();
		backButton.setText("Back");
		backButton.setOnMouseClicked((e) -> {
			screenController.moveToPreviousScreen();
		});
		
		
		//All the stuff for the Read message VBox 
		Label provSubjLabel = new Label();
		Label reply = new Label("Reply");
		Label forward = new Label("Forward");
		Label back = new Label("Back");
		TextArea readMessageText = new TextArea();
		readMessageText.setMaxWidth(400);
		readMessageText.setEditable(false);
		
		//Creates a list view and array to pull up message information when a message is clicked on
		ObservableList<String> providerSubject = FXCollections.observableArrayList();
		ListView<String> inboxMess = new ListView<String>(providerSubject);
		ArrayList<String> individualMess = new ArrayList<String>();
		
		//An alert that will pop up if any text boxes 
		Alert emptyTextboxes = new Alert(AlertType.NONE,"Please fill out missing information",ButtonType.OK);
		
		
		//HBox for the back, reply and forward labels
		HBox brf = new HBox();
		brf.setSpacing(10);
		brf.getChildren().addAll(back, reply, forward);
		
		//HBox for back and send message
		HBox bsm = new HBox();
		//bsm.setSpacing(10);
		bsm.getChildren().addAll(backButton, sendMess);
		
		//VBox for the read message part of the messaging page
		VBox readMessage = new VBox();
		readMessage.getChildren().addAll(provSubjLabel, readMessageText, brf);
		
		//VBox for the inbox part of the messaging page
		VBox inboxBox = new VBox();
		inboxBox.getChildren().addAll(inboxLbl, searchMess, inboxMess);
		
		//HBox for Provider label and TextField 
		HBox providerBox = new HBox();
		providerBox.getChildren().addAll(provider, providerText);
		
		//HBox for Subject label and TextField 
		HBox subjectBox = new HBox();
		subjectBox.getChildren().addAll(subject, subjectText);
		
		//VBox for compose message part of the messaging page
		VBox sendMessage = new VBox();
		sendMessage.getChildren().addAll(composeMess, providerBox, subjectBox, sendMessageText, bsm);
		
		//HBox of the entire screen
		HBox page = new HBox();
		page.getChildren().addAll(inboxBox, sendMessage);
		
		//Event for Send Message button where text from text fields are put in the inbox label and then cleared
		sendMess.setText("Send Message");
		sendMess.setOnAction(new EventHandler<>() {
			public void handle(ActionEvent event) {
				//Makes sure all text boxes are filled out and if not will not proceed to the next screen
				if(!providerText.getText().isEmpty() & !subjectText.getText().isEmpty() & !sendMessageText.getText().isEmpty() ) {
				providerSubject.add(providerText.getText() + "\n" + subjectText.getText());
				individualMess.add(sendMessageText.getText());
				providerText.clear();
				subjectText.clear();
				sendMessageText.clear();
				}
				//Shows an alert that tells user to put in any missing info
				else {
					emptyTextboxes.show();
				}
			}
		});
		
		//Event for inbox label where send message VBox is deleted and read message VBox is added
		inboxMess.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	provSubjLabel.setText(inboxMess.getSelectionModel().getSelectedItem());
            	readMessageText.setText(individualMess.get(inboxMess.getSelectionModel().getSelectedIndex()));
            	page.getChildren().remove(sendMessage);
            	page.getChildren().add(readMessage);
            }
          });
		
		//Event for back label where read message VBox is deleted and send message VBox is added
		back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
               page.getChildren().remove(readMessage);
               page.getChildren().add(sendMessage);
            }
          });
		
		return page;
		
		
		//Functionality to implement
		//Figure out search functionality
		//Make messages stay when same user logs in
		//Notification bell
		//Actually send messages to people
		//Figure out how to handle long text
		//Reply and forwarding functions
		//
	}
	
}
