package application.healthSoftware.views;

import application.healthSoftware.DataController;
import application.healthSoftware.ScreenController;
import application.healthSoftware.data.PatientProfile;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

//imports paxton added, may or may not be neccessary
import javafx.scene.text.Font;
import javafx.geometry.Pos;

public class PatientPortalCreateAccount implements IScreen {
	public static String ScreenID = "patientPortalCreateAccount";
	
	/*
	 * Temporary, private fields
	 */
	private ScreenController screenController;
	private DataController dataController;
	private PatientProfile currentPatientProfile;
	private String phoneNumberValue = "";
	private String emailValue = "";
	private String providerValue = "";
	private String groupValue = "";
	private String memberValue = "";
	private String pharmacyNameValue = "";
	private String pharmacyPhoneValue = "";
	private String pharmacyAddressValue = "";
	private String immunizationsValue = "";
	
	public PatientPortalCreateAccount(ScreenController sc) {
		screenController = sc;
		dataController = DataController.getInstance();
	}
	
	public void refreshData() {
		PatientProfile p = dataController.getCurrentPatientProfile();
		currentPatientProfile = p;
		if(p == null) {
			return;
		}
		
		phoneNumberValue = p.contactInformation.phoneNumber;
		emailValue = p.contactInformation.email;
		providerValue = p.insurance.provider;
		groupValue = p.insurance.groupNumber;
		memberValue = p.insurance.memberID;
		pharmacyNameValue = p.pharmacy.name;
		pharmacyAddressValue = p.pharmacy.address;
		pharmacyPhoneValue = p.pharmacy.phoneNumber;
		immunizationsValue = p.immunizationsString;
	}
	
	public Region getLayout() {
		VBox layout = new VBox();
		layout.setSpacing(35);
		
		/*
		 * Title
		 */
		Label title = new Label("Patient Profile");
		title.setFont(new Font(40));
		HBox titleRow = new HBox(title);
		titleRow.setAlignment(Pos.CENTER);
		
		/*
		 * Input fields
		 */
		Label infoTitle = new Label("Personal Information");
		infoTitle.setFont(new Font(20));
		Label firstNameLabel = new Label("First Name");
		TextField firstNameInput = new TextField(currentPatientProfile.firstName);
		firstNameInput.setDisable(true);
		VBox firstName = new VBox(firstNameLabel, firstNameInput);
		Label lastNameLabel = new Label("Last Name");
		TextField lastNameInput = new TextField(currentPatientProfile.lastName);
		lastNameInput.setDisable(true);
		VBox lastName = new VBox(lastNameLabel, lastNameInput);
		Label birthdayLabel = new Label("Birthday");
		TextField birthdayInput = new TextField(currentPatientProfile.birthday);
		birthdayInput.setDisable(true);
		VBox birthday = new VBox(birthdayLabel, birthdayInput);
		
		VBox personalInformationSection = new VBox(infoTitle, firstName, lastName, birthday);
		personalInformationSection.setSpacing(20);
		personalInformationSection.setMaxWidth(220);
		

		/*
		 * Contact information fields
		 */
		Label contactTitle = new Label("Contact Information");
		contactTitle.setFont(new Font(20));
		Label phoneLabel = new Label("Phone Number");
		TextField phoneInput = new TextField(currentPatientProfile.contactInformation.phoneNumber);
		phoneInput.textProperty().addListener((observable, oldValue, newValue) -> {
			phoneNumberValue = newValue;
		});
		VBox phone = new VBox(phoneLabel, phoneInput);
		Label emailLabel = new Label("Email");
		TextField emailInput = new TextField(currentPatientProfile.contactInformation.email);
		emailInput.textProperty().addListener((observable, oldValue, newValue) -> {
			emailValue = newValue;
		});
		VBox email = new VBox(emailLabel, emailInput);
		
		VBox contactInformationSection = new VBox(contactTitle, phone, email);
		contactInformationSection.setSpacing(20);
		contactInformationSection.setMaxWidth(220);

		/*
		 * Insurance information fields
		 */
		Label insuranceTitle = new Label("Insurance Information");
		insuranceTitle.setFont(new Font(20));
		Label providerLabel = new Label("Provider");
		TextField providerInput = new TextField(currentPatientProfile.insurance.provider);
		providerInput.textProperty().addListener((observable, oldValue, newValue) -> {
			providerValue = newValue;
		});
		VBox provider = new VBox(providerLabel, providerInput);
		Label groupLabel = new Label("Group Number");
		TextField groupInput = new TextField(currentPatientProfile.insurance.groupNumber);
		groupInput.textProperty().addListener((observable, oldValue, newValue) -> {
			groupValue = newValue;
		});
		VBox group = new VBox(groupLabel, groupInput);
		Label memberLabel = new Label("Member ID");
		TextField memberInput = new TextField(currentPatientProfile.insurance.memberID);
		memberInput.textProperty().addListener((observable, oldValue, newValue) -> {
			memberValue = newValue;
		});
		VBox member = new VBox(memberLabel, memberInput);
		
		VBox insuranceInformationSection = new VBox(insuranceTitle, provider, group, member);
		insuranceInformationSection.setSpacing(20);
		insuranceInformationSection.setMaxWidth(220);

		/*
		 * Pharmacy information fields
		 */
		Label pharmacyTitle = new Label("Pharmacy Information");
		pharmacyTitle.setFont(new Font(20));
		Label nameLabel = new Label("Pharmacy Name");
		TextField nameInput = new TextField(currentPatientProfile.pharmacy.name);
		nameInput.textProperty().addListener((observable, oldValue, newValue) -> {
			pharmacyNameValue = newValue;
		});
		VBox name = new VBox(nameLabel, nameInput);
		Label addressLabel = new Label("Pharmacy Address");
		TextField addressInput = new TextField(currentPatientProfile.pharmacy.address);
		addressInput.textProperty().addListener((observable, oldValue, newValue) -> {
			pharmacyAddressValue = newValue;
		});
		VBox address = new VBox(addressLabel, addressInput);
		Label pharmacyPhoneLabel = new Label("Pharmacy Phone");
		TextField pharmacyPhoneInput = new TextField(currentPatientProfile.pharmacy.phoneNumber);
		pharmacyPhoneInput.textProperty().addListener((observable, oldValue, newValue) -> {
			pharmacyPhoneValue = newValue;
		});
		VBox pharmacyPhone = new VBox(pharmacyPhoneLabel, pharmacyPhoneInput);
		
		VBox pharmacyInformationSection = new VBox(pharmacyTitle, name, address, pharmacyPhone);
		pharmacyInformationSection.setSpacing(20);
		pharmacyInformationSection.setMaxWidth(220);
		
		
		HBox inputSection = new HBox(personalInformationSection, contactInformationSection, insuranceInformationSection, pharmacyInformationSection);
		inputSection.setAlignment(Pos.CENTER);
		inputSection.setSpacing(20);
		
		/*
		 * Immunizations
		 */
		Label immunizationsLabel = new Label("Immunizations");
		TextArea immunizationsInput = new TextArea(currentPatientProfile.immunizationsString);
		immunizationsInput.textProperty().addListener((observable, oldValue, newValue) -> {
			immunizationsValue = newValue;
		});
		immunizationsInput.setMaxWidth(500);
		immunizationsInput.setPrefHeight(250);
		VBox immunizations = new VBox(immunizationsLabel, immunizationsInput);
		HBox immunizationsRow = new HBox(immunizations);
		immunizationsRow.setAlignment(Pos.CENTER);
		
		/*
		 * Submit button
		 */
		Button submitButton = new Button("Submit");
		submitButton.setOnMouseClicked((e) -> {
			currentPatientProfile.updateContact(phoneNumberValue, emailValue);
			currentPatientProfile.updateInsurance(providerValue, groupValue, memberValue);
			currentPatientProfile.updatePharmacy(pharmacyNameValue, pharmacyAddressValue, pharmacyPhoneValue);
			currentPatientProfile.immunizationsString = immunizationsValue;
			dataController.savePatientProfile(currentPatientProfile);
			screenController.moveToScreen("loginScreen");
		});
		HBox submitRow = new HBox(submitButton);
		submitRow.setAlignment(Pos.CENTER);
		
		
		layout.getChildren().add(titleRow);
		layout.getChildren().add(inputSection);
		layout.getChildren().add(immunizationsRow);
		layout.getChildren().add(submitRow);
		
		return layout;
	}
}