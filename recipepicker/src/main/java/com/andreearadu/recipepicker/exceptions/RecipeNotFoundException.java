package com.andreearadu.recipepicker.exceptions;

public class RecipeNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RecipeNotFoundException(long id) {
		super("Recipe with id: "+id+" not found");
	}

}
