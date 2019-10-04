package com.dsesolutions.welcometest.configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.dsesolutions.welcometest.dto.error.ErrorResponseDTO;
import com.dsesolutions.welcometest.exceptions.RestApiException;

/**
 * Global rest exception handler
 * 
 * @author Abhishek Shah
 *
 */
@RestControllerAdvice
public class RestApiErrorHandler {

	private final static String ERROR_SOMETHING_WENT_WRONG_MESSAGE = "";

	/**
	 * Customize the response for ConstraintViolationException.
	 *
	 * @param exception
	 * @param request
	 * @return ResponseEntity
	 */
	@ExceptionHandler(value = { ConstraintViolationException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final ResponseEntity<ErrorResponseDTO> constraintViolationException(ConstraintViolationException exception,
			WebRequest request) {
		ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.build().withMessage(exception.getMessage())
				.withErrors(getMessage(exception)).withDetails(request.getDescription(false));
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * @param exception
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final ResponseEntity<ErrorResponseDTO> methodArgumentException(MethodArgumentNotValidException exception,
			WebRequest request) {
		List<String> errors = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		if (!fieldErrors.isEmpty()) {
			fieldErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).forEach(errors::add);
		} else {
			exception.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
					.forEach(errors::add);
		}
		ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.build().withMessage("Validation failed").withErrors(errors)
				.withDetails(request.getDescription(false));
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * @param exception
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ HttpMessageNotReadableException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final ResponseEntity<ErrorResponseDTO> messageNotReadableException(HttpMessageNotReadableException exception,
			WebRequest request) {
		ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.build().withMessage("Invalid Json")
				.withErrors(getMessage(exception)).withDetails(request.getDescription(false));
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Customize the response for NoHandlerFoundException.
	 *
	 * @param exception
	 * @param request
	 * @return ResponseEntity
	 */
	@ExceptionHandler(value = { NoHandlerFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public final ResponseEntity<ErrorResponseDTO> handleNoHandlerFoundException(Exception exception,
			WebRequest request) {
		ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.build().withMessage(exception.getMessage())
				.withErrors(getMessage(exception)).withDetails(request.getDescription(false));
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
	}

	/**
	 * A single place to customize the response body of all Runtime Exception types.
	 *
	 * @param exception
	 * @param request
	 * @return ResponseEntity
	 */
	@ExceptionHandler(RestApiException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public final ResponseEntity<ErrorResponseDTO> exceptionHandler(RestApiException exception, WebRequest request) {
		ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.build().withMessage(ERROR_SOMETHING_WENT_WRONG_MESSAGE)
				.withErrors(getMessage(exception)).withDetails(request.getDescription(false));
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * A single place to customize the response body of all Exception types.
	 *
	 * @param exception
	 * @param request
	 * @return ResponseEntity
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public final ResponseEntity<ErrorResponseDTO> exceptionHandler(Exception exception, WebRequest request) {
		List<String> errorList = getMessage(exception);
		ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.build().withMessage(ERROR_SOMETHING_WENT_WRONG_MESSAGE)
				.withErrors(errorList).withDetails(request.getDescription(false));
		return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private List<String> getMessage(Exception exception) {
		return Collections.singletonList(exception.getMessage());
	}

}
