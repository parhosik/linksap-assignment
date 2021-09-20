package com.sap.ae.controller.recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.ae.dto.recipe.RecipeDto;
import com.sap.ae.model.recipe.Recipe;
import com.sap.ae.service.recipe.IRecipeService;
import com.sap.ae.util.Message.Message;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author LinkSap - Sikandar
 *
 */
@RestController
@RequestMapping("/api/recipe")
@Getter
@Setter
public class RecipeController {

	@Autowired
	private IRecipeService service;

	
	/**
	 * Logger
	 */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	
	/**
	 * This method fetches all recipes from the database
	 * @return
	 */
	@PostMapping("/all")
	public List<Recipe> findAll() {
		List<Recipe> manipulatedList = new ArrayList<>();
		
		List<Recipe> list = (List<Recipe>) service.findAll();
		
		for (Recipe recipe : list) {
			List<String> recipeList = Arrays.asList(recipe.getIngredients());
			recipe.setIngredientsList( recipeList );
			manipulatedList.add(recipe);
		}
		
		return manipulatedList;
	}

	/**
	 * This method deletes a recipe passed as parameter
	 * @param r
	 * @return
	 */
	@PostMapping("/delete")
	public ResponseEntity<Message<String>> delete(@RequestBody @Valid Recipe recipe) {
		Message<String> message = null;
		message = service.delete(recipe);
		return ResponseEntity.status(message.getStatus()).body(message);
	}

	/**
	 * This method adds a new recipy
	 * @param recipeDto
	 * @return
	 */
	@PostMapping("/add")
	public ResponseEntity<Message<String>> add(@Valid @RequestBody RecipeDto recipeDto) {

		log.info("recording new recipe.." + new Date());
		Message<String> message = null;
		
		Recipe r = new Recipe();
		r.setIsVegeterian(recipeDto.getIsVegeterian());
		r.setRecipeTitle(recipeDto.getRecipeTitle());
		r.setSuitableForPeople(recipeDto.getSuitableForPeople());
		r.setCookingInstructions(recipeDto.getCookingInstructions());
		r.setIngredients( recipeDto.getIngredients() );
		if( recipeDto.getRecipeId() != null ) {
			r.setId(recipeDto.getRecipeId());
		}

		try {
			log.info("recipeTitle==>" + recipeDto.getRecipeTitle());
			message = service.add(r);
			log.info("Response==> " + message.getStatus() + " " + message.getMessage());

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return ResponseEntity.status(message.getStatus()).body(message);

	}
	
	
	/**
	 * Find a Recipe by different parameters
	 * @param recipeTitle
	 * @return
	 */
	@GetMapping("/search")
	public List<Recipe> search(
			@RequestParam(value = "createdDate", required = false) @DateTimeFormat(pattern = "DD-DD-YYYY HH:MM:SS") String createdDate,
			@RequestParam(value = "isVegeterian", required = false) String isVegeterian,
			@RequestParam(value = "suitableFor", required = false) Long suitableFor,
			@RequestParam(value = "cookingInstructions", required = false) String cookingInstructions ) {
		
		List<Recipe> manipulatedList = new ArrayList<>();
		
		List<Recipe> list = service.searchCriteria(createdDate, isVegeterian, suitableFor, cookingInstructions);
		
		for (Recipe recipe : list) {
			List<String> recipeList = Arrays.asList(recipe.getIngredients());
			recipe.setIngredientsList( recipeList );
			manipulatedList.add(recipe);
		}

		return manipulatedList;
		
	}

}
