package application.healthSoftware;

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
		Visit v1 = new Visit();
		v1.visitID = "visit1";
		v1.patientID = "patient1";
		Visit v2 = new Visit();
		v2.visitID = "visit2";
		v2.patientID = "patient1";
		Visit v3 = new Visit();
		v3.visitID = "visit3";
		v3.patientID = "patient2";

		dataController.saveVisit(v1);
		dataController.saveVisit(v2);
		dataController.saveVisit(v3);
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
