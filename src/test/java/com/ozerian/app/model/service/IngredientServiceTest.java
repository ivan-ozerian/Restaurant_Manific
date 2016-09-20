package com.ozerian.app.model.service;

import com.ozerian.app.model.dao.IngredientDAO;
import com.ozerian.app.model.entity.Ingredient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/application-context.xml",
        "file:src/main/webapp/WEB-INF/hibernate-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class IngredientServiceTest {

    private IngredientService ingredientService;
    private IngredientDAO ingredientDAO;
    private Ingredient ingredient;
    private List<Ingredient> ingredients;

    @Before
    public void setUp() {
        ingredientService = new IngredientService();
        ingredientDAO = mock(IngredientDAO.class);
        ingredient = new Ingredient(1, "Ingredient", 250);
        ingredientService.setIngredientDAO(ingredientDAO);
        ingredients = new ArrayList<>();
    }

    @Test
    public void testMockCreation() {
        assertNotNull(ingredientDAO);
    }

    @Test
    public void testAddIngredient() {
        ingredientService.addIngredient(ingredient);
        verify(ingredientDAO, times(1)).addEntity(ingredient);
    }

    @Test
    public void testDeleteIngredient() {
        ingredientService.deleteIngredientById(ingredient.getId());
        verify(ingredientDAO, times(1)).deleteEntityById(ingredient.getId());
    }

    @Test
    public void testGetAllIngredients() {
        when(ingredientService.getAllIngredients()).thenReturn(ingredients);
        assertEquals(new ArrayList<Ingredient>(), ingredientService.getAllIngredients());
        verify(ingredientDAO, times(1)).getAllEntities();
    }

    @Test
    public void testSearchIngredient() {
        when(ingredientService.searchIngredientByName("Ingredient")).thenReturn(ingredient);
        assertEquals(ingredient, ingredientService.searchIngredientByName("Ingredient"));
        verify(ingredientDAO, times(1)).searchIngredientByName(ingredient.getName());
    }

    @Test
    public void testUpdateIngredientById() {
        ingredientService.updateIngredient(1, ingredient.getId());
        verify(ingredientDAO, times(1)).updateIngredient(1, ingredient.getId());
    }
}
