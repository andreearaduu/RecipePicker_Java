package com.andreearadu.recipepicker.exceptions;

public class IllegalListIngredientException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String message;

	public IllegalListIngredientException(String message) {
		super();
		this.message = message;
	}

}
