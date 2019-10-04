package com.dsesolutions.welcometest.converter;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dsesolutions.welcometest.dto.MessageDto;
import com.dsesolutions.welcometest.exceptions.RestApiException;
import com.dsesolutions.welcometest.model.Message;
import com.dsesolutions.welcometest.repository.MessageRepository;

/**
 * The converter represents the mapping of message dto fields to entity fields
 * 
 * @author Abhishek Shah
 *
 */
@Component
public class MessageDtoConverter implements Converter<MessageDto, Message> {

	private final Logger log = LoggerFactory.getLogger(MessageResponseConverter.class);

	private MessageRepository messageRepository;

	public MessageDtoConverter(final MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Message convert(MessageDto messageDto) {
		log.debug("Converting request instance to database instance");
		Message message = null;
		UUID messageId = messageDto.getId();
		if (messageId == null) {
			message = new Message();
		} else {
			Optional<Message> existingMessage = this.messageRepository.findById(messageId);
			if (existingMessage.isPresent()) {
				message = existingMessage.get();
			} else {
				log.error("Message details not found to update by id: {}", messageId);
				throw new RestApiException("Message not found to update");
			}
		}
		BeanUtils.copyProperties(messageDto, message);
		log.debug("Request instance converted to the database object");
		return message;
	}

}
