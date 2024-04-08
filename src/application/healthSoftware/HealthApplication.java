package application.healthSoftware;

import java.util.List;

import application.healthSoftware.data.MessageThread;
import application.healthSoftware.data.PatientProfile;
import application.healthSoftware.data.User;
import application.healthSoftware.data.Visit;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class HealthApplication {
	private ScreenController screenController;
	private DataController dataController;
	private Stage primaryStage;
	
	private User currentUser;
	
	private String title = "Health Software Demo";
	
	public HealthApplication() {
		// Create object that will manage our UI
		screenController = new ScreenController(this);
		dataController = DataController.getInstance();
		
		
		// Debugging steps, remove later!!
	}
	
	// Save reference to primary stage, and initialize it with defaults
	public void setStage(Stage stage) {
		primaryStage = stage;
		updateStageLayout(screenController.getCurrentScreenLayout());
	}
	
	// Update view based on layout we're provided
	public void updateStageLayout(Region layout) {
		Scene scene = new Scene(layout, 1000, 800);
		
		primaryStage.setTitle(title);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void updateCurrentUser(User u) {
		currentUser = u;
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
}
