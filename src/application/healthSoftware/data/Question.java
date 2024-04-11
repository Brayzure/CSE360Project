package application.healthSoftware.data;

import java.io.Serializable;

import application.healthSoftware.Util;

public class Question implements Serializable {
	public String questionID;
	public String question;
	public String answer;
	public String patientID;

	// Constructor
	public Question(String q) {
		question = q;
		answer = "";
		patientID = "";
		questionID = Util.generateID();
	}
	
	// Answer the question
	public void answerQuestion(String a) {
		answer = a;
	}
}
