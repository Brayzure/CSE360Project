package application.healthSoftware.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;

import java.util.ArrayList;
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
	//String newMessage;
	Group messageThreadGroup = new Group();
	Text messageText;
	MessageThread currThread;
	MultipleSelectionModel<MessageThread> selectionModel;
	List<String> messString = new ArrayList<String>();
	
	
	public MessageScreen(ScreenController sc) {
		screenController = sc;
		dataController = DataController.getInstance();
	}
	
	public void refreshData() {
		
	}
	
	
	
	public Region getLayout() {
		
		//All the stuff for the Compose message VBox
		TextField providerText = new TextField();
		TextField subjectText = new TextField();
		TextArea sendMessageText = new TextArea();
		sendMessageText.setPrefWidth(400);
		Label composeMess = new Label("Compose New Message");
		Label provider = new Label("Provider");
		Label subject = new Label("Subject");
		Label inboxLbl = new Label("Inbox");
		Button sendMessageButton = new Button();
		Button backButton = new Button();
		backButton.setText("Back");
		
		
		//All the stuff for the Read message VBox 
		Label provSubjLabel = new Label();
		Label reply = new Label("Reply");
		Label back = new Label("Back");
		TextArea readMessageText = new TextArea();
		readMessageText.setMaxWidth(400);
		readMessageText.setEditable(false);
		Button resolve = new Button();
		resolve.setText("Resolve");
		
		
		
		List<MessageThread> mess =  dataController.getAllMessageThreads();
		ObservableList<MessageThread> providerSubject = FXCollections.observableArrayList(mess);
		ListView<MessageThread> inboxMessageThreads = new ListView<MessageThread>(providerSubject);
		
		
		ObservableList<MessageThread> mess2 = FXCollections.observableArrayList();
		ListView<MessageThread> messageThread = new ListView<MessageThread>();
		messageThread.setFocusTraversable(false);
		
		//An alert that will pop up if any text boxes 
		Alert emptyTextboxes = new Alert(AlertType.NONE,"Please fill out missing information",ButtonType.OK);
		
		
		//HBox for the back, reply and forward labels
		HBox br = new HBox();
		br.setSpacing(10);
		br.getChildren().addAll(back, resolve, reply);
		
		//HBox for back and send message
		HBox bsm = new HBox();
		//bsm.setSpacing(10);
		bsm.getChildren().addAll(backButton, sendMessageButton);
		
		//VBox for the read message part of the messaging page
		VBox readMessage = new VBox();
		readMessage.getChildren().addAll(provSubjLabel, messageThread, br);
		
		//VBox for the inbox part of the messaging page
		VBox inboxBox = new VBox();
		inboxBox.getChildren().addAll(inboxLbl, inboxMessageThreads);
		
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
		
		backButton.setOnMouseClicked((e) -> {
			screenController.moveToPreviousScreen();
		});
		
		//Event for Send Message button where text from text fields are put in the inbox label and then cleared
		sendMessageButton.setText("Send Message");
		sendMessageButton.setOnAction(new EventHandler<>() {
			public void handle(ActionEvent event) {
				if(!providerText.getText().isEmpty() & !subjectText.getText().isEmpty() & !sendMessageText.getText().isEmpty() ) {
				MessageThread thread = new MessageThread();
				String newMessage = providerText.getText() + "\n" + subjectText.getText() + "\n" + sendMessageText.getText() + "\n";
				thread.createMessage(newMessage, dataController.getCurrentUser());
				dataController.saveMessageThread(thread);
				//providerSubject.addAll(dataController.getAllMessageThreads());
				inboxMessageThreads.getItems().addAll(dataController.getAllMessageThreads());
				sendMessageText.clear();
				providerText.clear();
				subjectText.clear();
				}
				else {
					emptyTextboxes.show();
				
				}
			}
		});
		
		inboxMessageThreads.setCellFactory(new Callback<ListView<MessageThread>, ListCell<MessageThread>>(){
			@Override
			public ListCell<MessageThread> call(ListView<MessageThread> m){
				ListCell<MessageThread> message = new ListCell<MessageThread>() {
				@Override
				protected void updateItem(MessageThread currThread, boolean empty) {
					super.updateItem(currThread, empty);
					if(currThread != null && currThread.isOpen == true && !empty) {
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
            	/*MessageThread selectedThread = inboxMessageThreads.getSelectionModel().getSelectedItem();
            	readMessageText.setText(""); 
            	for (Message message : selectedThread.messages) {
                    readMessageText.appendText(message.content + "\n");
                }*/
            	selectionModel = inboxMessageThreads.getSelectionModel();
            	 currThread = selectionModel.getSelectedItem();
            	 mess2.setAll(currThread);
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
		
		resolve.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	//set private variable to store current message thread on 
            	//display thread  and at same time set current message to message thread that I'm pulling up
            	currThread.resolve();
               page.getChildren().remove(readMessage);
               page.getChildren().add(sendMessage);
            }
          });
		
		reply.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
            	//make reply page
            	//currThread.createMessage(, dataController.getCurrentUser());
            	
            }
          });
		
		return page;
	}
	
}
