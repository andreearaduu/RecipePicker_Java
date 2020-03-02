package com.andreearadu.recipepicker.exceptions;

public class IllegalRecipeNameException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String message;

	public IllegalRecipeNameException(String message) {
		super();
		this.message = message;
	}
}