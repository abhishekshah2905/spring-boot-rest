package com.dsesolutions.welcometest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Abstract Handler Controller For bind Response
 */
public abstract class AbstractController {

    protected final Logger log = LoggerFactory.getLogger(getControllerClass());

    protected <T> ResponseEntity<T> generateResponse(T data) {
        return new ResponseEntity<T>(data, HttpStatus.OK);
    }

    protected abstract Class<?> getControllerClass();
}
