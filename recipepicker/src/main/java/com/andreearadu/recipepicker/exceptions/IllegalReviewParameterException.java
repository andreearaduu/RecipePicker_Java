package com.andreearadu.recipepicker.exceptions;

public class IllegalReviewParameterException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	public IllegalReviewParameterException(String message) {
		super();
		this.message = message;
	}

}
