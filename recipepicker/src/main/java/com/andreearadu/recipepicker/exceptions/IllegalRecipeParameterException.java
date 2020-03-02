package com.andreearadu.recipepicker.exceptions;

public class IllegalRecipeParameterException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	public IllegalRecipeParameterException(String message) {
		super();
		this.message = message;
	}

}
