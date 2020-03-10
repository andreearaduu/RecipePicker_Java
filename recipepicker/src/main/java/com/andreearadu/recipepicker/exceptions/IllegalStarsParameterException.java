package com.andreearadu.recipepicker.exceptions;

public class IllegalStarsParameterException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	public IllegalStarsParameterException(String message) {
		super();
		this.message = message;
	}

}
