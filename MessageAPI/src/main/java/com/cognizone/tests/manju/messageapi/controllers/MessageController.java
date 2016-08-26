package com.cognizone.tests.manju.messageapi.controllers;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cognizone.tests.manju.messageapi.model.Message;

@RestController
public class MessageController {

	private static Logger logger = LoggerFactory.getLogger("MessageController");

	private static BigInteger nextId;

	private static Map<BigInteger, Message> messagesMap;

	private static Message save(Message message) {

		if (messagesMap == null) {
			messagesMap = new HashMap<BigInteger, Message>();
			nextId = BigInteger.ONE;
		}else{
			
			nextId = (message.getId() == null)?nextId.add(BigInteger.ONE): message.getId();
			
		
		}

		message.setId(nextId);
		messagesMap.put(nextId, message);

		return message;
	}

	static {

		Message message1 = new Message();
		message1.setMessage("Sample Message");

		save(message1);
	}

	@RequestMapping(value = "/api/Messages", 
					method = RequestMethod.GET, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Message>> getAllMessages() {
		logger.info("Received Request for getAllMessages");
		
		Collection<Message> students = messagesMap.values();
		return new ResponseEntity<Collection<Message>>(students, HttpStatus.OK);
	}

	
	
	
	@RequestMapping(value = "/api/Messages/{id}", 
					method = RequestMethod.GET, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> getMessage(@PathVariable("id") BigInteger id) {
		
		logger.info("Received Request for getMessage("+id+")");
		
		Message message = messagesMap.get(id);
		if (message == null) {
			return new ResponseEntity<Message>(HttpStatus.NOT_FOUND);
		}
		
		logger.debug("Message retrived :"+ message);
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}

	
	
	
	@RequestMapping(value = "/api/Messages", 
					method = RequestMethod.POST, 
					consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> createMessage(@RequestBody Message message) {

		logger.info("Received Request for createMessage("+message.getMessage()+")");
		Message savedMessage = save(message);

		return new ResponseEntity<Message>(savedMessage, HttpStatus.CREATED);
	}

}
