package com.ozerian.app.controller;

import com.ozerian.app.model.entity.Dish;
import com.ozerian.app.model.service.DishService;
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
public class DishControllerTest {

    private MockMvc mockMvc;
    private DishService dishService;
    private Dish dish_1;
    private Dish dish_2;
    private DishController dishController;

    @Before
    public void setUp() {
        dishController = new DishController();
        dishService = mock(DishService.class);
        dishController.setDishService(dishService);
        dish_1 = mock(Dish.class);
        dish_2 = mock(Dish.class);
        mockMvc = MockMvcBuilders.standaloneSetup(dishController).build();
    }

    @Test
    public void testDishesActions() throws Exception {
        mockMvc.perform(get("/dishes/"))
                .andExpect(status().isOk())
                .andExpect(view().name("dishesActions"))
                .andExpect(forwardedUrl("dishesActions"));
    }

    @Test
    public void testFindAllDishes() throws Exception {
        when(dishService.getAllDishes()).thenReturn(Arrays.asList(dish_1, dish_2));

        mockMvc.perform(get("/dishes/showAll"))
                .andExpect(status().isOk())
                .andExpect(view().name("dishesList"))
                .andExpect(forwardedUrl("dishesList"))
                .andExpect(model().attribute("dishes", hasSize(2)));
        verify(dishService, times(1)).getAllDishes();
        verifyNoMoreInteractions(dishService);
    }

    @Test
    public void testAddDishForm() throws Exception {
        mockMvc.perform(get("/dishes/addForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("addDishForm"))
                .andExpect(forwardedUrl("addDishForm"));
    }

    @Test
    public void testSearchDishForm() throws Exception {
        mockMvc.perform(get("/dishes/searchForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("searchDishByNameForm"))
                .andExpect(forwardedUrl("searchDishByNameForm"));
    }

    @Test
    public void testFindByName() throws Exception {
        mockMvc.perform(post("/dishes/findByName").param("dishName", "Dish_1"))
                .andExpect(status().isOk())
                .andExpect(view().name("dishNotFoundException"))
                .andExpect(forwardedUrl("dishNotFoundException"));
    }

    @Test
    public void testDeleteDish() throws Exception {
        mockMvc.perform(post("/dishes/delete").param("dishId", String.valueOf(dish_1.getId())))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/dishes/showAll"));

        verify(dishService, times(1)).deleteDish(dish_1.getId());
        verifyNoMoreInteractions(dishService);
    }

    @Test
    public void testAddDish() throws Exception {
        mockMvc.perform(post("/dishes/addSubmit").param("dish", "dish"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/dishes/showAll"));

        verify(dishService, times(1)).addDish(new Dish());
        verifyNoMoreInteractions(dishService);
    }
}