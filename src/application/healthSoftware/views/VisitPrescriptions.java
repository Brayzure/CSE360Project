package application.healthSoftware.views;

import application.healthSoftware.DataController;
import application.healthSoftware.ScreenController;
import application.healthSoftware.data.Prescription;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class VisitPrescriptions implements IScreen {
	public static String ScreenID = "visitPrescriptions";
	
	private ScreenController screenController;
	private DataController dataController;
	
	private ObservableList<Prescription> prescriptions = FXCollections.observableArrayList();
	
	public VisitPrescriptions(ScreenController sc) {
		screenController = sc;
		dataController = DataController.getInstance();
		
		for(int i = 0; i < 3; i++) {
			Prescription p = new Prescription();
			p.setName("Prescription " + (i + 1));
			p.setDosage("twice a day");
			p.setDuration("four weeks");
			
			prescriptions.add(p);
		}
	}
	
	public void refreshData() {
		
	}
	
	public Region getLayout() {
		VBox layout = new VBox();
		
		HBox titleRow = new HBox();
		titleRow.setAlignment(Pos.CENTER);
		Label title = new Label("Doctor Home Screen");
		title.setFont(new Font(48));
		titleRow.getChildren().add(title);
		layout.getChildren().add(titleRow);
		
		VBox content = new VBox();
		content.setAlignment(Pos.CENTER);
		content.setPrefHeight(500);
		content.setSpacing(30);
		layout.getChildren().add(content);
		
		Label visitTitle = new Label("Prescriptions");
		visitTitle.setFont(new Font(36));
		HBox visitTitleRow = new HBox();
		visitTitleRow.setAlignment(Pos.CENTER);
		visitTitleRow.getChildren().add(visitTitle);
		content.getChildren().add(visitTitleRow);
		
		TableView<Prescription> table = new TableView<Prescription>();
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.setMaxWidth(500);
		table.setItems(prescriptions);

		TableColumn<Prescription, String> nameCol = new TableColumn<Prescription, String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<Prescription, String>("name"));
		TableColumn<Prescription, String> dosageCol = new TableColumn<Prescription, String>("Dosage");
		dosageCol.setCellValueFactory(new PropertyValueFactory<Prescription, String>("dosage"));
		TableColumn<Prescription, String> durationCol = new TableColumn<Prescription, String>("Duration");
		durationCol.setCellValueFactory(new PropertyValueFactory<Prescription, String>("duration"));
		
		table.getColumns().setAll(nameCol, dosageCol, durationCol);
		content.getChildren().add(table);
		
		HBox checkRow = new HBox();
		checkRow.setMaxWidth(500);
		CheckBox skipPrescriptions = new CheckBox("No prescriptions needed");
		checkRow.getChildren().add(skipPrescriptions);
		content.getChildren().add(checkRow);

		Button registerButton = new Button("Next");
		registerButton.setOnMouseClicked((e) -> {
			screenController.moveToScreen("visitFinalRecommendations");
		});
		HBox row = new HBox(registerButton);
		row.setAlignment(Pos.CENTER);
		content.getChildren().add(row);
		
		return layout;
	}
}
