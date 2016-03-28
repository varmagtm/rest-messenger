package com.techcrux.rest.messenger.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techcrux.rest.messenger.dao.GenericDAO;
import com.techcrux.rest.messenger.entities.Message;
import com.techcrux.rest.messenger.exceptions.DataAccessException;
import com.techcrux.rest.messenger.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private GenericDAO genericDAO;

	@Override
	public List<Message> getAllMessages() {
		try {
			return genericDAO.search(Message.class, null);
		} catch (DataAccessException e) {
			return null;
		}
	}

	@Override
	public Message getMessage(long msgId) {
		try {
			return genericDAO.findByPrimaryKey(Message.class, msgId);
		} catch (DataAccessException e) {
			return null;
		}
	}

	@Override
	public Message addMessage(Message msg) {
		try {
			return genericDAO.save(msg);
		} catch (DataAccessException e) {
			return null;
		}
	}

	@Override
	public Message updateMessage(Message msg) {
		try {
			return genericDAO.merge(msg);
		} catch (DataAccessException e) {
			return null;
		}
	}

	@Override
	public Message deleteMessage(long msgId) {
		/*try {
			return genericDAO.merge(msg);
		} catch (DataAccessException e) {
			return null;
		}*/
		return null;
	}

}
