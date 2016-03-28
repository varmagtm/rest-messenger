package com.techcrux.rest.messenger.resources;

import java.util.List;

import com.techcrux.rest.messenger.entities.Message;

public interface MessageResource {
	
	public List<Message> getMessages();
	
	public Message getMessage(Long msgId);
	
	public Message addMessage(Message message);
	
	public Message updateMessage(Long messageId, Message message);
	
	public Message deleteMessage(Long messageId);

}
