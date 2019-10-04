package com.dsesolutions.welcometest.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.dsesolutions.welcometest.dto.MessageDto;
import com.dsesolutions.welcometest.exceptions.RestApiException;
import com.dsesolutions.welcometest.model.Message;
import com.dsesolutions.welcometest.repository.MessageRepository;
import com.dsesolutions.welcometest.service.MessageService;

/**
 * The service layer implementation for the message content delivery to the
 * respective business clients
 * 
 * @author Abhishek Shah
 *
 */
@Service
public class MessageServiceImpl implements MessageService {

	private final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

	private MessageRepository messageRepository;

	private ConversionService conversionService;

	public MessageServiceImpl(final MessageRepository messageRepository, final ConversionService conversionService) {
		this.messageRepository = messageRepository;
		this.conversionService = conversionService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<MessageDto> getAllMessages() {
		log.debug("Fetching message details");
		List<Message> messages = this.messageRepository.findAll();
		log.debug("{} No. of messages found to return: {}", messages.size());
		return messages.stream().map(message -> this.conversionService.convert(message, MessageDto.class))
				.collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public MessageDto saveMessage(MessageDto messageDto) {
		log.debug("Creating/Updating message details");
		Message message = this.conversionService.convert(messageDto, Message.class);
		Message savedMessage = this.messageRepository.save(message);
		log.debug("Message details successfully created/updated");
		return this.conversionService.convert(savedMessage, MessageDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public MessageDto getMessageById(UUID messageId) {
		log.debug("Fetching message details by: {}", messageId);
		final Optional<Message> messageById = this.messageRepository.findById(messageId);
		if (!messageById.isPresent()) {
			log.error("Message details not found for: {}", messageId);
			throw new RestApiException("Message not found");
		}
		Message message = messageById.get();
		log.debug("Message details found for: {}", messageId);
		return this.conversionService.convert(message, MessageDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void deleteMessage(UUID messageId) {
		log.debug("Trying to delete message details by: {}", messageId);
		final Optional<Message> messageById = this.messageRepository.findById(messageId);
		if (!messageById.isPresent()) {
			log.error("Message details not found to delete by id: {}", messageId);
			throw new RestApiException("Message not found");
		}
		this.messageRepository.delete(messageById.get());
		log.debug("Message details deleted successfully by: {}", messageId);
	}

}
