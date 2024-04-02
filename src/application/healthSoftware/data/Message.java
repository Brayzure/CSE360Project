package application.healthSoftware.data;

public class Message {
String content;
String authorID;
User author;
String dateCreated;

public Message() {
	this.content = null;
	this.authorID = null;
	this.author = null;
	this.dateCreated = null;
	}

public Message(String content, User author) {
	this.content = content;
	this.author = author;
}
}

