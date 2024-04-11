package application.healthSoftware.views;

import java.util.List;
import application.healthSoftware.DataController;
import application.healthSoftware.ScreenController;
import application.healthSoftware.data.Visit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class nurseSummary implements IScreen {
	public static String ScreenID = "nurseSummary";
	
	private ScreenController screenController;
	private DataController dataController;
	
	private ObservableList<Visit> visits = FXCollections.observableArrayList();
	
	public nurseSummary(ScreenController sc) {
		screenController = sc;
		dataController = DataController.getInstance();
	} 
	
	public void refreshData() {
		
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public Region getLayout() {
		VBox layout = new VBox();
		
		VBox content = new VBox();
		content.setAlignment(Pos.CENTER);
		content.setPrefHeight(500);
		content.setSpacing(30);
		layout.getChildren().add(content);
		
		// Title
		Label visitTitle = new Label("Prior Health History");
		visitTitle.setFont(new Font(36));
		HBox visitTitleRow = new HBox();
		visitTitleRow.setAlignment(Pos.CENTER);
		visitTitleRow.getChildren().add(visitTitle);
		content.getChildren().add(visitTitleRow);
		
		// Table setup
		TableView<Visit> table = new TableView<Visit>();
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setMaxWidth(500);
		table.setItems(visits);

		TableColumn<Visit, String> prescriptionsCol = new TableColumn<Visit, String>("Prescriptions");
		prescriptionsCol.setCellValueFactory(new PropertyValueFactory<Visit, String>("prescriptionString"));
		TableColumn<Visit, String> healthConcernsCol = new TableColumn<Visit, String>("Health Concerns");
		healthConcernsCol.setCellValueFactory(new PropertyValueFactory<Visit, String>("healthConcerns"));
		
		table.getColumns().setAll(prescriptionsCol, healthConcernsCol);
		List<Visit> visitList = getCurrentPatientVisits();
		if(visitList.size() > 1) {
			visitList.remove(visitList.size() - 1);
			table.setItems(FXCollections.observableArrayList(visitList));
		}
		content.getChildren().add(table);
		
		// Return to home screen button
		Button homeButton = new Button("Return to Home Screen");
		homeButton.setOnMouseClicked((e) -> {
			Visit current = dataController.getCurrentVisit();
			current.setState("FINDINGS");
			dataController.saveVisit(current);
			dataController.setCurrentVisit(null);
			screenController.moveToScreen("nurseHomeScreen");
		});
		HBox row = new HBox(homeButton);
		row.setAlignment(Pos.CENTER);
		content.getChildren().add(row);
		
		return layout;
	}
	
	public List<Visit> getCurrentPatientVisits() {
		Visit cVisit = dataController.getCurrentVisit();
		List<Visit> cPatientVisits = dataController.getAllVisitsForPatient(cVisit.patientID);
		return cPatientVisits;
	}
}