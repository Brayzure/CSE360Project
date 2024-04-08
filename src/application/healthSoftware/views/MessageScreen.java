package application.healthSoftware.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import application.healthSoftware.DataController;
import application.healthSoftware.ScreenController;
import application.healthSoftware.data.MessageThread;
import application.healthSoftware.data.User;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.scene.input.MouseEvent;

public class MessageScreen implements IScreen {
	public static String ScreenID = "messageScreen";
	
	ScreenController screenController;
	DataController dataController;
	User user = new User();
	String threadID = new String();
	String convertMessage;
	Group messageThreadGroup = new Group();
	Text messageText;
	
	
	public MessageScreen(ScreenController sc) {
		screenController = sc;
	}
	
	public void refreshData() {
		
	}
	
	public Region getLayout() {
		
		TextArea messageChain = new TextArea();
		ScrollPane messageThread = new ScrollPane(messageChain);
		 messageThread.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		 messageThread.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		 VBox messageThreadBox = new VBox();
		
		
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
		Button sendMessageButton = new Button();
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
		Button resolve = new Button();
		resolve.setText("Resolve");
		
		//Creates a list view and array to pull up message information when a message is clicked on
		List<MessageThread> mess =  dataController.getAllMessageThreads();
		ObservableList<MessageThread> providerSubject = FXCollections.observableArrayList(mess);
		ListView<MessageThread> inboxMessageThreads = new ListView<MessageThread>(providerSubject);
		
		
		
		//An alert that will pop up if any text boxes 
		Alert emptyTextboxes = new Alert(AlertType.NONE,"Please fill out missing information",ButtonType.OK);
		
		
		//HBox for the back, reply and forward labels
		HBox brf = new HBox();
		brf.setSpacing(10);
		brf.getChildren().addAll(back, reply, forward);
		
		//HBox for back and send message
		HBox bsm = new HBox();
		//bsm.setSpacing(10);
		bsm.getChildren().addAll(backButton, sendMessageButton);
		
		//VBox for the read message part of the messaging page
		VBox readMessage = new VBox();
		readMessage.getChildren().addAll(provSubjLabel, readMessageText, brf);
		
		//VBox for the inbox part of the messaging page
		VBox inboxBox = new VBox();
		inboxBox.getChildren().addAll(inboxLbl, searchMess, inboxMessageThreads);
		
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
		sendMessageButton.setText("Send Message");
		sendMessageButton.setOnAction(new EventHandler<>() {
			public void handle(ActionEvent event) {
				MessageThread thread = new MessageThread();
				thread.createMessage(sendMessageText.getText(), user);
				dataController.saveMessageThread(thread);
			}
		});
		
		inboxMessageThreads.setCellFactory(new Callback<ListView<MessageThread>, ListCell<MessageThread>>(){
			@Override
			public ListCell<MessageThread> call(ListView<MessageThread> m){
				ListCell<MessageThread> message = new ListCell<MessageThread>() {
				@Override
				protected void updateItem(MessageThread currThread, boolean bool) {
					super.updateItem(currThread, bool);
					if(currThread != null) {
						setText(String.valueOf(dataController.getAllMessageThreads()));
					}
				}
				};
				return message;
			}
		});
		
		//Event for inbox label where send message VBox is deleted and read message VBox is added
		inboxMessageThreads.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	//convertMessage = String.valueOf(inboxMessageThreads.getSelectionModel().getSelectedItem());
            	//messageThreadGroup.getChildren().addAll(dataController.getMessageThread(threadID));
            	//messageThread.setText(dataController.getMessageThread(convertMessage));
            	//inboxMessageThreads.getSelectionModel().getSelectedItem()
            	page.getChildren().remove(sendMessage);
            	page.getChildren().add(readMessage);
            }
            //dataController.getMessageThread(
          });
		
		//Event for back label where read message VBox is deleted and send message VBox is added
		back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
               page.getChildren().remove(readMessage);
               page.getChildren().add(sendMessage);
            }
          });
		
		resolve.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
		
		//getAllMessageThreadsForPatient specifically checks the authorID field on each thread
		
		/*As a user, I want to be able to create threads and messages, so I can communicate with other users.
		Acceptance Criteria:
		Users of any kind can create a new MessageThread, which will be listed in the sidebar of the message screen.
		Users can open a MessageThread, which will then list all messages sent in that thread, who sent them, and when they were sent.
		Users can open a MessageThread, and send their own message.
		Users can mark a MessageThread as resolved, removing it from the list on the left.
*/
	}
	
}
