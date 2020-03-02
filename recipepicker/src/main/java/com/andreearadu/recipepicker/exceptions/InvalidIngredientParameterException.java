package com.andreearadu.recipepicker.exceptions;

public class InvalidIngredientParameterException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	public InvalidIngredientParameterException(String message) {
		super();
		this.message = message;
	}

}
