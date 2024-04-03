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
	private Map<String, MessageThread> allThreads = new LinkedHashMap<>();
	
	private Visit currentVisit;
	private PatientProfile currentPatientProfile;
	
	private DataController() {
		initialize();
	}
	
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
		
		// Load all threads into memory
		try {
			allThreads = (Map<String, MessageThread>) Serializer.deserialize(rootFolder + "threads.ser");
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
			else {
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
	
	public void setCurrentVisit(Visit v) {
		currentVisit = v;
	}
	
	public Visit getCurrentVisit() {
		return currentVisit;
	}
	
	public void setCurrentPatientProfile(PatientProfile p) {
		currentPatientProfile = p;
	}
	
	public PatientProfile getCurrentPatientProfile() {
		return currentPatientProfile;
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
	
	// Get all message threads
	public List<MessageThread> getAllMessageThreads() {
		List<MessageThread> threads = new ArrayList<MessageThread>();
		
		for(Map.Entry<String, MessageThread> entry : allThreads.entrySet()) {
			MessageThread thread = entry.getValue();
			threads.add(thread);
		}
		
		return threads;
	}
	
	// Retrieve a cached patient profile
	public MessageThread getMessageThread(String threadID) {
		return allThreads.get(threadID);
	}
	
	// Save a visit
	public void saveMessageThread(MessageThread t) {
		allThreads.put(t.threadID, t);
		
		String fileName = rootFolder + "threads.ser";
		try {
			Serializer.serialize(allThreads, fileName);
		}
		catch(IOException err) {
			err.printStackTrace();
		}
	}
}
