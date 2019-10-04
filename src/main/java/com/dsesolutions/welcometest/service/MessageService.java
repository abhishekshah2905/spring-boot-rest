package com.dsesolutions.welcometest.service;

import java.util.List;
import java.util.UUID;

import com.dsesolutions.welcometest.dto.MessageDto;

/**
 * The service layer signatures to implementa in a way of requirements
 * 
 * @author Abhishek Shah
 *
 */
public interface MessageService {

	/**
	 * The method implementation is responsible for fetching all messages
	 * 
	 * @return the list of all messages
	 */
	List<MessageDto> getAllMessages();

	/**
	 * The method implementation is responsible for creating new message to the
	 * system
	 * 
	 * @param message details to get created into the application
	 * @return
	 */
	MessageDto saveMessage(final MessageDto message);

	/**
	 * The method implementation is responsible for fetching message details based
	 * on provided message identity
	 * 
	 * @param messageId to fetch details
	 * @return
	 */
	MessageDto getMessageById(final UUID messageId);

	/**
	 * The method implementation is responsible for deleting the message based on
	 * provided message identity
	 */
	void deleteMessage(final UUID messageId);

}
