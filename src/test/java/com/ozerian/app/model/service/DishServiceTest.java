package com.ozerian.app.model.service;

import com.ozerian.app.model.dao.DishDAO;
import com.ozerian.app.model.entity.Dish;
import com.ozerian.app.model.entity.DishCategory;
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
public class DishServiceTest {

    private DishService dishService;
    private DishDAO dishDAO;
    private Dish dish;
    private List<Dish> dishes;

    @Before
    public void setUp() {
        dishService = new DishService();
        dishDAO = mock(DishDAO.class);
        dish = new Dish(1, "Dish", DishCategory.MAIN, 100.00, 300);
        dishService.setDishDAO(dishDAO);
        dishes = new ArrayList<>();
    }

    @Test
    public void testMockCreation() {
        assertNotNull(dishDAO);
    }

    @Test
    public void testAddDish() {
        dishService.addDish(dish);
        verify(dishDAO, times(1)).addEntity(dish);
    }

    @Test
    public void testDeleteDish() {
        dishService.deleteDish(dish.getId());
        verify(dishDAO, times(1)).deleteEntityById(dish.getId());
    }

    @Test
    public void testGetAllDishes() {
        when(dishService.getAllDishes()).thenReturn(dishes);
        assertEquals(new ArrayList<Dish>(), dishService.getAllDishes());
        verify(dishDAO, times(1)).getAllEntities();
    }

    @Test
    public void testSearchDish() {
        when(dishService.searchDishByName("Dish")).thenReturn(dishes);
        assertEquals(new ArrayList<Dish>(), dishService.searchDishByName("Dish"));
        verify(dishDAO, times(1)).searchDishByName(dish.getDishName());
    }

    @Test
    public void testGetDishById() {
        when(dishService.getDishById(1)).thenReturn(dish);
        assertEquals(dish, dishService.getDishById(1));
        verify(dishDAO, times(1)).searchEntityByID(1);
    }
}
