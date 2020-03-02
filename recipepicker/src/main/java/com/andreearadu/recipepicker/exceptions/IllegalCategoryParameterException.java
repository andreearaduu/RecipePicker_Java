package com.andreearadu.recipepicker.exceptions;

public class IllegalCategoryParameterException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String message;

	public IllegalCategoryParameterException(String message) {
		super();
		this.message = message;
	}
}
