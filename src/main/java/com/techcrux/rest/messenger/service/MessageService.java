package com.techcrux.rest.messenger.service;

import java.util.List;

import com.techcrux.rest.messenger.entities.Message;

public interface MessageService {
	
	public List<Message> getAllMessages();
	
	public Message getMessage(long msgId);
	
	public Message addMessage(Message msg);
	
	public Message updateMessage(Message msg);
	
	public Message deleteMessage(long msgId);

}
