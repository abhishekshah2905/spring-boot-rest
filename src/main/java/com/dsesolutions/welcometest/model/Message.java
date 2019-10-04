package com.dsesolutions.welcometest.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * The database entity object of Message details
 * 
 * @author Abhishek Shah
 *
 */
@Entity(name = "t_message")
public class Message extends AbstractEntity {

	private static final long serialVersionUID = -6778568680909343080L;

	@Column
	private String subject;

	@Column
	private String sender;

	@Column
	private String to;

	@Column
	private String content;

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * @param sender the sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
