package com.dsesolutions.welcometest.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsesolutions.welcometest.dto.MessageDto;
import com.dsesolutions.welcometest.service.MessageService;

/**
 * 
 * @author Abhishek Shah
 *
 */
@RestController
@RequestMapping("/api/messages")
public class MessageController extends AbstractController {

	private MessageService messageService;

	/**
	 * Dependency injection by constructor based. We can also use @Authowired
	 * annotation to achieve the same thing
	 * 
	 * @param messageService
	 */
	public MessageController(final MessageService messageService) {
		this.messageService = messageService;
	}

	/**
	 * The rest end point implementation to fetch all messages
	 * 
	 * @return the list of messages
	 */
	@GetMapping
	public ResponseEntity<?> getAllMessages() {
		log.info("Get request to fetch all messages");
		List<MessageDto> messages = this.messageService.getAllMessages();
		log.info("{} No. of messages found to return in response", messages.size());
		return generateResponse(messages);
	}

	/**
	 * The rest end point implementation to fetch particular message details
	 * 
	 * @param messageId to fetch message details
	 * @return message details
	 */
	@GetMapping("/{messageId}")
	public ResponseEntity<?> getMessage(@PathVariable UUID messageId) {
		log.info("Get request to fetch message details of: {}", messageId);
		MessageDto message = this.messageService.getMessageById(messageId);
		log.info("Returning the response with message details of: {}", messageId);
		return generateResponse(message);
	}

	/**
	 * The rest end point implementation to save message details
	 * 
	 * @param messageDto to save/update
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> saveMessage(@RequestBody MessageDto messageDto) {
		log.info("Trying to create/update message details");
		MessageDto createdMessages = this.messageService.saveMessage(messageDto);
		log.info("Message has been created/updated successfully");
		return generateResponse(createdMessages);
	}

	/**
	 * The rest end point implementation to delete the particular message details
	 * 
	 * @param messageId to detail from the application
	 * @return
	 */
	@DeleteMapping("/{messageId}")
	public ResponseEntity<?> deletMessage(@PathVariable UUID messageId) {
		log.info("Get request to detele message details of: {}", messageId);
		this.messageService.deleteMessage(messageId);
		log.info("successfully message deleted for {}:", messageId);
		return generateResponse(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Class<?> getControllerClass() {
		return this.getClass();
	}

}
