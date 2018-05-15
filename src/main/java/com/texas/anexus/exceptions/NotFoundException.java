package com.texas.anexus.exceptions;

import org.hibernate.service.spi.ServiceException;

@SuppressWarnings("serial")
public class NotFoundException extends ServiceException {

	public NotFoundException(String message) {
		super(message);
	}
}
