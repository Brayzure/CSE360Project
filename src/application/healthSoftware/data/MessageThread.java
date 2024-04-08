package application.healthSoftware.data;

import java.io.Serializable;
import java.util.ArrayList;

public class MessageThread implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public String threadID;
	public String authorID;
	public User author;
	public String dateCreated;
	public boolean isOpen;
	public boolean shouldNotifyParent;
	public boolean shouldNotifyStaff;
	public ArrayList<Message> messages;
	
	
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
