package com.pixels.pixelsgateway.exception;

import java.io.Serializable;

public class JwtTokenMissingException extends RuntimeException implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JwtTokenMissingException() {
		super();
	}

	public JwtTokenMissingException(String message) {
		super(message);
	}

}
