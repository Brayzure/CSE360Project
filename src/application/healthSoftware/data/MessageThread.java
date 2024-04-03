package application.healthSoftware.data;

import java.util.ArrayList;

public class MessageThread {
	String threadID;
	String authorID;
	User author;
	String dateCreated;
	boolean isOpen;
	boolean shouldNotifyParent;
	boolean shouldNotifyStaff;
	ArrayList<Message> messages;
	
	
	public MessageThread() {
		this.threadID = null;
		this.authorID = null;
		this.author = null;
		this.dateCreated = null;
		this.isOpen = true;
		this.shouldNotifyParent = false;
		this.shouldNotifyStaff = false;
		this.messages = new ArrayList<Message>();
	}
	
	public void createMessage(String content, User author) {
		messages.add(new Message(content, author));
	}
	
	public void resolve() {
		isOpen = false;
	}
}
