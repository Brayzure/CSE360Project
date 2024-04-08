package application.healthSoftware.data;

import java.io.Serializable;

public class Message implements Serializable {
	public String content;
	public String authorID;
	public User author;
	public String dateCreated;
	
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

