package com.ocsc.poc.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BuisnessException extends RuntimeException {

	public BuisnessException(String exceptionMsg) {
		super(exceptionMsg);
	}
}