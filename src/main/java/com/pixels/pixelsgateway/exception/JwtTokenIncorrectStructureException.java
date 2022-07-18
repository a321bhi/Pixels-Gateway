package com.pixels.pixelsgateway.exception;

import java.io.Serializable;

public class JwtTokenIncorrectStructureException extends RuntimeException implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JwtTokenIncorrectStructureException() {
		super();
	}

	public JwtTokenIncorrectStructureException(String message) {
		super(message);
	}

}
