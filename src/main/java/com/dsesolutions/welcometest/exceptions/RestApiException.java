package com.dsesolutions.welcometest.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * The common rest Api exception to handle the errors
 */
public class RestApiException extends RuntimeException {

	private static final long serialVersionUID = 8950485025347642885L;

	private List<String> errorList = new ArrayList<>();

	public RestApiException(String message) {
		super(message);
	}

	public RestApiException(List<String> errorList) {
		this.errorList = errorList;
	}

	List<String> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}
}
