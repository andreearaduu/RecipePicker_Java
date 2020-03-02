package com.andreearadu.recipepicker.exceptions;

public class InvalidCookingTimeParameterException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	public InvalidCookingTimeParameterException(String message) {
		super();
		this.message = message;
	}
	
}
