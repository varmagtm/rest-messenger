package com.techcrux.rest.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techcrux.rest.messenger.entities.Message;
import com.techcrux.rest.messenger.service.MessageService;

@Path("/messages")
@Component
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResourceJaxrsImpl implements MessageResource {

	@Autowired
	private MessageService msgService;

	@Override
	@GET
	public List<Message> getMessages() {
		return msgService.getAllMessages();
	}

	@Override
	@Path("/{messageId}")
	@GET
	public Message getMessage(@PathParam("messageId") Long msgId) {
		return msgService.getMessage(msgId);
	}

	@Override
	@POST
	public Message addMessage(Message message) {
		return msgService.addMessage(message);
	}

	@Override
	@PUT
	@Path("/{msgId}")
	public Message updateMessage(@PathParam("msgId") Long messageId, Message message) {
		message.setId(messageId);
		return msgService.updateMessage(message);
	}

	@Override
	@DELETE
	@Path("/{msgId}")
	public Message deleteMessage(@PathParam("msgId") Long messageId) {
		return msgService.deleteMessage(messageId);
	}

}
