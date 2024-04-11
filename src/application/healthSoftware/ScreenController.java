package application.healthSoftware;

import java.util.Map;
import java.util.Map.Entry;
import java.util.LinkedHashMap;

import application.healthSoftware.data.User;
import application.healthSoftware.views.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ScreenController {
	// Map of all the screens we can switch to
	private Map<String, IScreen> allScreens = new LinkedHashMap<>();
	// The current screen we're showing
	private IScreen currentScreen;
	// The previous screen ID (for back buttons)
	private String previousScreenID;
	// Sometimes we need to operate on the parent application
	private HealthApplication app;
	// Default screen we show on launch
	private static String defaultScreen = "loginScreen";
	// Cached instance of the data controller
	private DataController dataController;
	
	public ScreenController(HealthApplication application) {
		app = application;
		initialize();
	}
	
	private void initialize() {
		dataController = DataController.getInstance();
		
		// Populate screen map
		allScreens.put(LoginScreen.ScreenID, new LoginScreen(this));
		allScreens.put(PatientHomeScreen.ScreenID, new PatientHomeScreen(this));
		allScreens.put(DoctorHomeScreen.ScreenID, new DoctorHomeScreen(this));
		allScreens.put(NurseHomeScreen.ScreenID, new NurseHomeScreen(this));
		allScreens.put(VisitPatientLookup.ScreenID, new VisitPatientLookup(this));
		allScreens.put(VisitVitals.ScreenID, new VisitVitals(this));
		allScreens.put(PatientDetails.ScreenID, new PatientDetails(this));
		allScreens.put(PatientPortalChangeImmunization.ScreenID, new PatientPortalChangeImmunization(this));
		allScreens.put(PatientPortalChangeInfo.ScreenID, new PatientPortalChangeInfo(this));
		allScreens.put(PatientPortalCreateAccount.ScreenID, new PatientPortalCreateAccount(this));
		allScreens.put(PatientPortalCreateImmunization.ScreenID, new PatientPortalCreateImmunization(this));
		allScreens.put(MessageScreen.ScreenID, new MessageScreen(this));
		allScreens.put(VisitExamRoom.ScreenID, new VisitExamRoom(this));
		allScreens.put(VisitExamQuestions.ScreenID, new VisitExamQuestions(this));
		allScreens.put(VisitPatientOverview.ScreenID, new VisitPatientOverview(this));
		allScreens.put(VisitExamFindings.ScreenID, new VisitExamFindings(this));
		allScreens.put(VisitPrescriptions.ScreenID, new VisitPrescriptions(this));
		allScreens.put(VisitFinalRecommendations.ScreenID, new VisitFinalRecommendations(this));
		allScreens.put(LoginCreateAccount.ScreenID, new LoginCreateAccount(this));
		allScreens.put(PatientCreateQuestion.ScreenID, new PatientCreateQuestion(this));
		allScreens.put(PatientViewAnswers.ScreenID, new PatientViewAnswers(this));
		allScreens.put(StaffViewQuestions.ScreenID, new StaffViewQuestions(this));
		allScreens.put(nurseSummary.ScreenID, new nurseSummary(this));
		
		// Set default screen
		currentScreen = allScreens.get(ScreenController.defaultScreen);
		currentScreen.refreshData();
	}
	
	// Get layout of whatever our current screen is
	public Region getCurrentScreenLayout() {
		String currentScreenID = getKey(allScreens, currentScreen);
		if(currentScreenID.startsWith("login") || currentScreenID.startsWith("patientPortalCreate")) {
			return currentScreen.getLayout();
		}
		
		// If not login or account creation screen, add Log Out button to the top of the window
		Region content = currentScreen.getLayout();
		
		Button logout = new Button("Log Out");
		logout.setOnMouseClicked((e) -> {
			dataController.setCurrentUser(null);
			moveToScreen("loginScreen");
		});
		HBox titleRow = new HBox(logout);
		titleRow.setAlignment(Pos.BASELINE_RIGHT);
		VBox layout = new VBox(titleRow, content);
		
		return layout;
	}
	
	// We're showing a different screen!
	public void moveToScreen(String screenID) {
		System.out.println("Moving to Screen: " + screenID);
		if(allScreens.containsKey(screenID)) {
			String currentScreenID = getKey(allScreens, currentScreen);
			
			if(!currentScreenID.equals(screenID)) {
				previousScreenID = getKey(allScreens, currentScreen);
				currentScreen = allScreens.get(screenID);
				currentScreen.refreshData();
			}
			app.updateStageLayout(getCurrentScreenLayout());
		}
	}
	
	// We're showing the previous screen!
	public void moveToPreviousScreen() {
		moveToScreen(previousScreenID);
	}
	
	public void updateCurrentApplicationUser(User u) {
		app.updateCurrentUser(u);
	}
	
	public User getCurrentApplicationUser() {
		return app.getCurrentUser();
	}
	
	public <K, V> K getKey(Map<K, V> map, V value) {
	    for (Entry<K, V> entry : map.entrySet()) {
	        if (entry.getValue().equals(value)) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}
}
