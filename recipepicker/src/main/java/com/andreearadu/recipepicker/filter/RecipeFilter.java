package com.andreearadu.recipepicker.filter;

import java.util.Collection;
import java.util.Map;

import com.andreearadu.recipepicker.model.Category;

public class RecipeFilter {
	private Map<String, Object> myFiltersMap;

	public RecipeFilter(Map<String, Object> myFiltersMap) {
		super();
		this.myFiltersMap = myFiltersMap;
	}

	public void setRecipeName(String recipeName) {
		this.myFiltersMap.put("recipeName",recipeName );
	}
	
	public String getRecipeName() {
		return (String) this.myFiltersMap.get("recipeName");
	}
	
	public void setCategory(Category category) {
		this.myFiltersMap.put("category", category);
	}
	
	public Category getCategory() {
		return (Category) this.myFiltersMap.get("category");
	}
	

	public void setStartCookingTime(Integer startCookingTime) {
		this.myFiltersMap.put("startCookingTime", startCookingTime);
	}
	
	public Integer getStartCookingTime() {
		return  (Integer) this.myFiltersMap.get("startCookingTime");
	}
	
	
	public void setEndCookingTime(Integer endCookingTime) {
		this.myFiltersMap.put("endCookingTime", endCookingTime);
	}
	
	public Integer getEndCookingTime() {
		return  (Integer) this.myFiltersMap.get("endCookingTime");
	}
	
	public void setIngredientsName(Collection<String> ingredientsName) {
		this.myFiltersMap.put("ingredientsName", ingredientsName);
	}
	
	@SuppressWarnings("unchecked")
	public Collection<String>  getIngredientsName() {
		return (Collection<String>) this.myFiltersMap.get("ingredientsName");
	}
}
