package com.dsesolutions.welcometest.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.dsesolutions.welcometest.dto.MessageDto;
import com.dsesolutions.welcometest.model.Message;

/**
 * The converter represents the mapping of message entity fields to dto fields
 * 
 * @author Abhishek Shah
 *
 */
@Component
public class MessageResponseConverter implements Converter<Message, MessageDto> {

	private final Logger log = LoggerFactory.getLogger(MessageResponseConverter.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MessageDto convert(Message message) {
		log.debug("Converting database object to response dto");
		MessageDto messageDto = new MessageDto();
		BeanUtils.copyProperties(message, messageDto);
		log.debug("Database object converted to the response dto");
		return messageDto;
	}

}
