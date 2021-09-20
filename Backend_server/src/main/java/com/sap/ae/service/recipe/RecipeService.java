package com.sap.ae.service.recipe;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.ae.model.recipe.Recipe;
import com.sap.ae.repository.recipe.IRecipeRepository;
import com.sap.ae.util.Message.Message;

/**
 * Recipe Service
 * @author LinkSap - Sikandar
 *
 */
@Service
public class RecipeService implements IRecipeService {

	@Autowired
	private IRecipeRepository repo;

	/**
	 * Adding new Recipe
	 */
	@Override
	public Message<String> add(Recipe r) {
		Message<String> messages = new Message<>();

		repo.save(r);

		messages.setData("1").setMessage("Recipe saved successfully.").setStatus(200).setCode("successful");

		return messages;
	}

	
	/**
	 * Method used to retrieve all recipies
	 */
	@Override
	public List<Recipe> findAll() {
		return (List<Recipe>) repo.findAll();
	}

	
	/**
	 * Method used to delete a recipy
	 */
	@Override
	public Message<String> delete(Recipe r) {
		Message<String> messages = new Message<>();
		
		repo.delete(r);

		messages.setData("1").setMessage("Recipe Deleted successfully.").setStatus(200).setCode("successful");

		return messages;
	}

	@Override
	public List<Recipe> searchCriteria(String createdDate, String isVegeterian, Long suitableFor, String cookingInstructions ) {
		return repo.searchCriteria(createdDate, isVegeterian, suitableFor, cookingInstructions );
	}

}
