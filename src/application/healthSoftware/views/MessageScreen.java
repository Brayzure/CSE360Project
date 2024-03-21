package application.healthSoftware.views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
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
		
		//All the stuff for the Compose message Vbox
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
		
		
		//All the stuff for the Read message Vbox w/ placeholder labels
		Label messageTitle = new Label("Message Subject");
		Label sender = new Label("Sender");
		Label reply = new Label("Reply");
		Label forward = new Label("Forward");
		Label back = new Label("Back");
		TextArea readMessageText = new TextArea();
		readMessageText.setText("This is where the messages will be");
		readMessageText.setMaxWidth(400);
		readMessageText.setEditable(false);
		
		//Hbox for the back, reply and forward labels
		HBox brf = new HBox();
		brf.setSpacing(10);
		brf.getChildren().addAll(back, reply, forward);
		
		//HBox for back and send message
		HBox bsm = new HBox();
		//bsm.setSpacing(10);
		bsm.getChildren().addAll(backButton, sendMess);
		
		//Vbox for the read message part of the messaging page
		VBox readMessage = new VBox();
		readMessage.getChildren().addAll(messageTitle, sender, readMessageText, brf);
		
		//Vbox for the inbox part of the messaging page
		VBox inboxBox = new VBox();
		inboxBox.getChildren().addAll(inboxLbl, searchMess, inbox);
		
		//Hbox for Provider label and textfield 
		HBox providerBox = new HBox();
		providerBox.getChildren().addAll(provider, providerText);
		
		//Hbox for Subject label and textfield 
		HBox subjectBox = new HBox();
		subjectBox.getChildren().addAll(subject, subjectText);
		
		//Vbox for compose message part of the messaging page
		VBox sendMessage = new VBox();
		sendMessage.getChildren().addAll(composeMess, providerBox, subjectBox, sendMessageText, bsm);
		
		//Hbox of the entire screen
		HBox page = new HBox();
		page.getChildren().addAll(inboxBox, sendMessage);
		
		//Event for Send Message button where text from text fields are put in the inbox label and then cleared
		sendMess.setText("Send Message");
		sendMess.setOnAction(new EventHandler<>() {
			public void handle(ActionEvent event) {
				pastMessages = pastMessages + providerText.getText() + "\n" + subjectText.getText() + "\n";
				inbox.setText(pastMessages);
				providerText.clear();
				subjectText.clear();
				sendMessageText.clear();
			}
		});
		
		//Event for inbox label where send message vbox is deleted and read message vbox is added
		inbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
               page.getChildren().remove(sendMessage);
               page.getChildren().add(readMessage);
            }
          });
		
		//Event for back lable where read message vbox is deleted and send message vbox is added
		back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
               page.getChildren().remove(readMessage);
               page.getChildren().add(sendMessage);
            }
          });
		
		return page;
		
		
		//Functionality to implement
		//Put inbox messages in sroll pane
		//Figure out search functionality
		//Figure out how to get info from clicking on indivudual messages and show it in read message vbox
	}
	
}
