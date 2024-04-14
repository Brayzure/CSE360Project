package application.healthSoftware;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import application.healthSoftware.data.*;

public class DataController {
	private static DataController instance;
	
	private static String rootFolder = System.getProperty("user.home") + "/cse360-th3/";

	private Map<String, Visit> allVisits = new LinkedHashMap<>();
	private Map<String, PatientProfile> allPatients = new LinkedHashMap<>();
	private Map<String, User> allUsers = new LinkedHashMap<>();
	private Map<String, Question> allQuestions = new LinkedHashMap<>();
	
	private Visit currentVisit;
	private PatientProfile currentPatientProfile;
	private User currentUser;
	
	private DataController() {
		initialize();
	}
	
	@SuppressWarnings("unchecked")
	private void initialize() {
		// Check if the directory we need exists, if not, create it
		File dir =  new File(DataController.rootFolder);
		if(dir.exists() == false) {
			dir.mkdir();
		}
		
		// Load all patients into memory
		try {
			allPatients = (Map<String, PatientProfile>) Serializer.deserialize(rootFolder + "patients.ser");
		}
		catch(FileNotFoundException err) {
			// File doesn't exist, that's alright!
		}
		catch(IOException err) {
			err.printStackTrace();
		}
		catch(ClassNotFoundException err) {
			err.printStackTrace();
		}
		
		// Load all visits into memory
		try {
			allVisits = (Map<String, Visit>) Serializer.deserialize(rootFolder + "visits.ser");
		}
		catch(FileNotFoundException err) {
			// File doesn't exist, that's alright!
		}
		catch(IOException err) {
			err.printStackTrace();
		}
		catch(ClassNotFoundException err) {
			err.printStackTrace();
		}
		
		// Load all users into memory
		try {
			allUsers = (Map<String, User>) Serializer.deserialize(rootFolder + "users.ser");
		}
		catch(FileNotFoundException err) {
			// File doesn't exist, that's alright!
		}
		catch(IOException err) {
			err.printStackTrace();
		}
		catch(ClassNotFoundException err) {
			err.printStackTrace();
		}
		
		// Load all questions into memory
		try {
			allQuestions = (Map<String, Question>) Serializer.deserialize(rootFolder + "questions.ser");
		}
		catch(FileNotFoundException err) {
			// File doesn't exist, that's alright!
		}
		catch(IOException err) {
			err.printStackTrace();
		}
		catch(ClassNotFoundException err) {
			err.printStackTrace();
		}
	}
	
	public static DataController getInstance() {
		if(instance == null) {
			instance = new DataController();
		}
		
		return instance;
	}
	
	// Get all visits associated with a single patient
	public List<Visit> getAllVisitsForPatient(String patientID) {
		List<Visit> visits = new ArrayList<Visit>();
		
		for(Map.Entry<String, Visit> entry : allVisits.entrySet()) {
			Visit visit = entry.getValue();
			if(visit.patientID.equals(patientID)) {
				visits.add(visit);
			}
			else {
			}
		}
		
		return visits;
	}
	
	// Get all visits associated with a single patient and a single state
	public List<Visit> getAllVisitsForPatientWithState(String patientID, String state) {
		List<Visit> visits = new ArrayList<Visit>();
		
		for(Map.Entry<String, Visit> entry : allVisits.entrySet()) {
			Visit visit = entry.getValue();
			if(visit.patientID.equals(patientID) && visit.getState().equals(state)) {
				visits.add(visit);
			}
			else {
			}
		}
		
		return visits;
	}
	
	// Get all visits that are at a certain state
	public List<Visit> getAllVisitsWithState(String state) {
		List<Visit> visits = new ArrayList<Visit>();
		
		for(Map.Entry<String, Visit> entry : allVisits.entrySet()) {
			Visit visit = entry.getValue();
			if(visit.getState().equals(state)) {
				visits.add(visit);
			}
		}
		
		return visits;
	}
	
	// Save a visit
	public void saveVisit(Visit v) {
		allVisits.put(v.visitID, v);
		
		String fileName = rootFolder + "visits.ser";
		try {
			Serializer.serialize(allVisits, fileName);
		}
		catch(IOException err) {
			err.printStackTrace();
		}
	}
	
	// Save a question
	public void saveQuestion(Question q) {
		allQuestions.put(q.questionID, q);
		
		String fileName = rootFolder + "questions.ser";
		try {
			Serializer.serialize(allQuestions, fileName);
		}
		catch(IOException err) {
			err.printStackTrace();
		}
	}
	
	// Save a patient profile
	public void savePatientProfile(PatientProfile p) {
		allPatients.put(p.patientID, p);
		
		String fileName = rootFolder + "patients.ser";
		try {
			Serializer.serialize(allPatients, fileName);
		}
		catch(IOException err) {
			err.printStackTrace();
		}
	}
	
	// Retrieve a cached patient profile
	public PatientProfile getPatientProfile(String patientID) {
		return allPatients.get(patientID);
	}
	
	// Get a single question
	public Question getQuestion(String questionID) {
		return allQuestions.get(questionID);
	}
	
	// Get all unanswered questions
	public List<Question> getAllUnansweredQuestions() {
		List<Question> questions = new ArrayList<Question>();
		
		for(Map.Entry<String, Question> entry : allQuestions.entrySet()) {
			Question q = entry.getValue();
			if(q.answer.isEmpty()) {
				questions.add(q);
			}
		}
		
		return questions;
	}
	
	// Get all answered questions for a patient
	public List<Question> getAllAnsweredQuestionsForPatient(String patientID) {
		List<Question> questions = new ArrayList<Question>();
		
		for(Map.Entry<String, Question> entry : allQuestions.entrySet()) {
			Question q = entry.getValue();
			if(!q.answer.isEmpty() && q.patientID.equals(patientID)) {
				questions.add(q);
			}
		}
		
		return questions;
	}
	
	// Retrieve a specific user by their ID
	public User getUser(String userID) {
		return allUsers.get(userID);
	}
	
	// Retrieve a specific user by their username
	public User getUserByUsername(String username) {
		for(Map.Entry<String, User> entry : allUsers.entrySet()) {
			User u = entry.getValue();
			if(u.username.equals(username)) {
				return u;
			}
		}
		return null;
	}
	
	// Save a user
	public void saveUser(User u) {
		allUsers.put(u.userID, u);
		
		String fileName = rootFolder + "users.ser";
		try {
			Serializer.serialize(allUsers, fileName);
		}
		catch(IOException err) {
			err.printStackTrace();
		}
	}
	
	// Find user based on name and birthday
	public User searchForUser(String firstName, String lastName, String birthday) {
		for(Map.Entry<String, User> entry : allUsers.entrySet()) {
			User u = entry.getValue();
			if(u.firstName.equals(firstName) && u.lastName.equals(lastName) && u.birthday.equals(birthday)) {
				return u;
			}
		}
		return null;
	}
	
	// Find patient profile based on name and birthday
	public PatientProfile searchForPatientProfile(String firstName, String lastName, String birthday) {
		for(Map.Entry<String, PatientProfile> entry : allPatients.entrySet()) {
			PatientProfile p = entry.getValue();
			if(p.firstName.equals(firstName) && p.lastName.equals(lastName) && p.birthday.equals(birthday)) {
				return p;
			}
		}
		return null;
	}
	
	// Set cached visit
	public void setCurrentVisit(Visit v) {
		currentVisit = v;
	}
	
	// Get cached visit
	public Visit getCurrentVisit() {
		return currentVisit;
	}
	
	// Set cached patient profile
	public void setCurrentPatientProfile(PatientProfile p) {
		currentPatientProfile = p;
	}
	
	// Get cached patient profile
	public PatientProfile getCurrentPatientProfile() {
		return currentPatientProfile;
	}
	
	// Set cached user
	public void setCurrentUser(User u) {
		currentUser = u;
	}
	
	// Get cached user
	public User getCurrentUser() {
		return currentUser;
	}
}
