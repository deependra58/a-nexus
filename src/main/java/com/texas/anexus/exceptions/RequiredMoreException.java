package com.texas.anexus.exceptions;

import org.hibernate.service.spi.ServiceException;

public class RequiredMoreException extends ServiceException {
	
	public RequiredMoreException(String string) {
		super(string);
	}
	
}
