package com.sap.ae.user;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.ae.model.recipe.Recipe;
import com.sap.ae.repository.recipe.IRecipeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeControllerTest {

	protected MockMvc mvc;
	@Autowired
	WebApplicationContext webApplicationContext;

	@MockBean
	IRecipeRepository recipeRepo;

	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@Test
	public void deleteRecipe() throws Exception {

		Recipe r = new Recipe();
		r.setId(new Long(4));
		r.setCookingInstructions("bake for 10 minutes");
		r.setIngredients("egg, half glass water, 2mg salt");
		r.setIsVegeterian("No");
		r.setSuitableForPeople(1);
		r.setRecipeTitle("test");
		
		setUp();
		String uri = "/api/recipe/delete";

		String inputJson = mapToJson(r);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
	
	@Test
	public void addRecipe() throws Exception {

		Recipe r = new Recipe();
		r.setCookingInstructions("bake for 10 minutes");
		r.setIngredients("egg, half glass water, 2mg salt");
		r.setIsVegeterian("No");
		r.setSuitableForPeople(1);
		r.setRecipeTitle("test");
		
		setUp();
		String uri = "/api/recipe/add";

		String inputJson = mapToJson(r);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

	}
	
	@Test
	public void addRecipeIfRecipeTitleMissing() throws Exception {

		Recipe r = new Recipe();
		r.setCookingInstructions("bake for 10 minutes");
		r.setIngredients("egg, half glass water, 2mg salt");
		r.setIsVegeterian("No");
		r.setSuitableForPeople(1);
		//r.setRecipeTitle("test");
		
		setUp();
		String uri = "/api/recipe/add";

		String inputJson = mapToJson(r);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);

	}
	
	@Test
	public void addRecipeIfIngredientsMissing() throws Exception {

		Recipe r = new Recipe();
		r.setCookingInstructions("bake for 10 minutes");
		//r.setIngredients("egg, half glass water, 2mg salt");
		r.setIsVegeterian("No");
		r.setSuitableForPeople(1);
		r.setRecipeTitle("test");
		
		setUp();
		String uri = "/api/recipe/add";

		String inputJson = mapToJson(r);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);

	}
	
	@Test
	public void search() throws Exception {

		Recipe r = new Recipe();
		r.setCookingInstructions("bake for 10 minutes");
		r.setIngredients("egg, half glass water, 2mg salt");
		r.setIsVegeterian("No");
		r.setSuitableForPeople(1);
		//r.setRecipeTitle("test");
		
		setUp();
		String uri = "/api/recipe/add";

		String inputJson = mapToJson(r);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);

	}
	
	

}
