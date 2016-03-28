package com.techcrux.rest.messenger.resources;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.techcrux.rest.messenger.entities.Message;
import com.techcrux.rest.messenger.service.MessageService;

@RequestMapping(value = "/messages-spring", /*consumes = { "application/json" }, */produces = { "application/json" })
@Controller
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResourceSpringRestImpl implements MessageResource {

	@Autowired
	private MessageService msgService;

	@Override
	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Message> getMessages() {
		return msgService.getAllMessages();
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/{messageId}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Message getMessage(@PathVariable("messageId") Long msgId) {
		return msgService.getMessage(msgId);
	}

	@Override
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Message addMessage(@RequestBody Message message) {
		message.setCreated(new Date());
		return msgService.addMessage(message);
	}

	@Override
	@RequestMapping(method = RequestMethod.PUT, value = "/{msgId}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Message updateMessage(@PathVariable("msgId") Long messageId, @RequestBody Message message) {
		message.setId(messageId);
		return msgService.updateMessage(message);
	}

	@Override
	@RequestMapping(method = RequestMethod.DELETE, value = "/{msgId}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Message deleteMessage(@PathVariable("msgId") Long messageId) {
		return msgService.deleteMessage(messageId);
	}

}
