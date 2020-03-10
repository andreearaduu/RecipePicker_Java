package com.andreearadu.recipepicker.exceptions;

public class IlleagalIngredientNameException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	public IlleagalIngredientNameException(String message) {
		super();
		this.message = message;
	}

}
