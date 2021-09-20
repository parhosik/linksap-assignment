package com.sap.ae.dto.recipe;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeDto {
	Long recipeId;
	
    @NotNull(message = "Is Vegeterian ? selection is required")
    String isVegeterian;
    
    @NotNull(message = "Recipe Title is required")
    @NotBlank(message = "Recipe Title must not be null")
    String recipeTitle;
    
    @NotNull(message = "For how many people the dish is suitable")
    @Min(value = 1L, message = "The value must be positive")
    int suitableForPeople;

    @NotNull(message = "Please write which ingredients are required")
    @NotBlank(message = "ingredients must not be null")
    String ingredients;
    
    @NotNull(message = "Please tell us how you cook this recipe ?")
    @NotBlank(message = "Cooking instructions must not be null")
    String cookingInstructions;
    
}
