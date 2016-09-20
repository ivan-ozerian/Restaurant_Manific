package com.ozerian.app.controller;

import com.ozerian.app.model.entity.Ingredient;
import com.ozerian.app.model.service.IngredientService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/web-context.xml",
        "file:src/main/webapp/WEB-INF/application-context.xml",
        "file:src/main/webapp/WEB-INF/hibernate-context.xml",
        "file:src/main/webapp/WEB-INF/security-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class IngredientControllerTest {

    private MockMvc mockMvc;
    private IngredientService ingredientService;
    private Ingredient ingredient_1;
    private Ingredient ingredient_2;
    private IngredientController ingredientController;

    @Before
    public void setUp() {
        ingredientController = new IngredientController();
        ingredientService = mock(IngredientService.class);
        ingredientController.setIngredientService(ingredientService);
        ingredient_1 = mock(Ingredient.class);
        ingredient_2 = mock(Ingredient.class);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    public void testIngredientsActions() throws Exception {
        mockMvc.perform(get("/ingredients/"))
                .andExpect(status().isOk())
                .andExpect(view().name("ingredientsActions"))
                .andExpect(forwardedUrl("ingredientsActions"));
    }

    @Test
    public void testFindAllIngredients() throws Exception {
        when(ingredientService.getAllIngredients()).thenReturn(Arrays.asList(ingredient_1, ingredient_2));

        mockMvc.perform(get("/ingredients/showAll"))
                .andExpect(status().isOk())
                .andExpect(view().name("ingredientsList"))
                .andExpect(forwardedUrl("ingredientsList"))
                .andExpect(model().attribute("ingredients", hasSize(2)));
        verify(ingredientService, times(1)).getAllIngredients();
        verifyNoMoreInteractions(ingredientService);
    }

    @Test
    public void testAddIngredientsForm() throws Exception {
        mockMvc.perform(get("/ingredients/addForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("addIngredientForm"))
                .andExpect(forwardedUrl("addIngredientForm"));
    }

    @Test
    public void testSearchIngredientsForm() throws Exception {
        mockMvc.perform(get("/ingredients/searchForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("searchIngredientByNameForm"))
                .andExpect(forwardedUrl("searchIngredientByNameForm"));
    }

    @Test
    public void testFindByName() throws Exception {
        mockMvc.perform(post("/ingredients/findByName").param("ingredientName", "Ingredients_1"))
                .andExpect(status().isOk())
                .andExpect(view().name("ingredientByName"))
                .andExpect(forwardedUrl("ingredientByName"));
    }

    @Test
    public void testDeleteIngredient() throws Exception {
        mockMvc.perform(post("/ingredients/delete").param("ingredientId", String.valueOf(ingredient_1.getId())))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/ingredients/showAll"));

        verify(ingredientService, times(1)).deleteIngredientById(ingredient_1.getId());
        verifyNoMoreInteractions(ingredientService);
    }

    @Test
    public void testAddIngredient() throws Exception {
        mockMvc.perform(post("/ingredients/addSubmit").param("ingredient", "ingredient"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/ingredients/showAll"));

        verify(ingredientService, times(1)).addIngredient(new Ingredient());
        verifyNoMoreInteractions(ingredientService);
    }

    @Test
    public void testShowExpiringIngredients() throws Exception {
        when(ingredientService.getExpiringIngredients()).thenReturn(Arrays.asList(ingredient_1, ingredient_2));

        mockMvc.perform(get("/ingredients/showExpiring"))
                .andExpect(status().isOk())
                .andExpect(view().name("ingredientsList"))
                .andExpect(forwardedUrl("ingredientsList"))
                .andExpect(model().attribute("ingredients", hasSize(2)));
        verify(ingredientService, times(1)).getExpiringIngredients();
        verifyNoMoreInteractions(ingredientService);
    }
}