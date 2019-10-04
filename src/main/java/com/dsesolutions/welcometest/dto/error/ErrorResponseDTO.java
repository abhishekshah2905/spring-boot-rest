package com.dsesolutions.welcometest.dto.error;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The error response model
 */
public class ErrorResponseDTO {

	private Date timestamp = new Date();

	private String message;

	private String details;

	private List<String> errors = new ArrayList<>();

	/**
	 * Default private constructor
	 */
	private ErrorResponseDTO() {
	}

	/**
	 * Parameter constructor
	 * 
	 * @param timestamp
	 * @param message
	 * @param details
	 */
	public ErrorResponseDTO(Date timestamp, String message, String details) {
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	/**
	 * Builder method
	 * 
	 * @return
	 */
	public static ErrorResponseDTO build() {
		return new ErrorResponseDTO();
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 * @return the errors
	 */
	public List<String> getErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public ErrorResponseDTO withMessage(String message) {
		this.message = message;
		return this;
	}

	public ErrorResponseDTO withDetails(String details) {
		this.details = details;
		return this;
	}

	public ErrorResponseDTO withDate(Date date) {
		this.timestamp = date;
		return this;
	}

	public ErrorResponseDTO withCurrentDate() {
		this.timestamp = new Date();
		return this;
	}

	public ErrorResponseDTO withErrors(List<String> errorList) {
		this.errors = errorList;
		return this;
	}
}