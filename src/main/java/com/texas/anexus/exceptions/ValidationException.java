package com.texas.anexus.exceptions;

import org.hibernate.service.spi.ServiceException;

/**
 * Handling the validation.
 * 
 * @author Deeependra karki
 * @version 1.0.0
 * @since May 18, 2018
 */
public class ValidationException extends ServiceException {

	public ValidationException(String message) {
		super(message);
	}

}
