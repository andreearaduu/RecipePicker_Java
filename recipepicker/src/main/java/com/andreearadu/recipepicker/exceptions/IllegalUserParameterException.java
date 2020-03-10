package com.andreearadu.recipepicker.exceptions;

public class IllegalUserParameterException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String message;

	public IllegalUserParameterException(String message) {
		super();
		this.message = message;
	}

}
