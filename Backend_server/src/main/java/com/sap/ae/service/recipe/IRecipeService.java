package com.sap.ae.service.recipe;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sap.ae.model.recipe.Recipe;
import com.sap.ae.util.Message.Message;

/**
 * Recipe Service Interface
 * @author LinkSap - Sikandar
 *
 */
@Service
public interface IRecipeService {

	/**
	 * Method used for adding a new recipe
	 * @param r
	 * @return
	 */
	public Message<String> add( Recipe r );
	
	
	/**
	 * Method used for retreiving all recipies
	 * @return
	 */
	public List<Recipe> findAll();
	
	/**
	 * Method used for deleting a recipe
	 * @param r
	 * @return
	 */
	public Message<String> delete( Recipe r );
	
	public List<Recipe> searchCriteria( String createdDate, String isVegeterian, Long suitableFor, String cookingInstructions );
	
}
