package com.pixels.pixelsgateway.exception;

import java.io.Serializable;

public class JwtTokenMalformedException extends RuntimeException implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JwtTokenMalformedException() {
		super();
	}

	public JwtTokenMalformedException(String message) {
		super(message);
	}

}
