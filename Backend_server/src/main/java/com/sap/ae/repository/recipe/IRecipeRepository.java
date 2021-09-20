package com.sap.ae.repository.recipe;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sap.ae.model.recipe.Recipe;

/**
 * A spring CrudRepository handling all crud operations itself.
 * @author LinkSap - Sikandar
 *
 */
public interface IRecipeRepository extends CrudRepository<Recipe, Long> {
	
	@Query(value="select * from recipe a where (:createdDate is null or DATE_FORMAT(a.created_date,'%d-%m-%Y %h:%i') = :createdDate ) "
			+ " and (:isVegeterian is null or a.is_vegeterian = :isVegeterian)"
			+ " and (:suitableFor is null or a.suitable_for_people = :suitableFor)"
			+ " and (:cookingInstructions is null or a.cooking_instructions like %:cookingInstructions% )"
			, nativeQuery=true)
	List<Recipe> searchCriteria( String createdDate, String isVegeterian, Long suitableFor, String cookingInstructions );
	
}
