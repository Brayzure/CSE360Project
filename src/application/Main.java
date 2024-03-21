package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import application.healthSoftware.HealthApplication;

public class Main extends Application {
	// Create application that houses all of our logic
	HealthApplication app = new HealthApplication();
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {		
		// Tell application about the primary stage
		app.setStage(primaryStage);
	}
}
