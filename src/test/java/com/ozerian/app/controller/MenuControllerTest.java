package com.ozerian.app.controller;

import com.ozerian.app.model.entity.Menu;
import com.ozerian.app.model.service.DishService;
import com.ozerian.app.model.service.MenuService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

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
public class MenuControllerTest {

    private MockMvc mockMvc;
    private DishService dishService;
    private MenuService menuService;
    private MenuController menuController;

    @Before
    public void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/jsp/");
        viewResolver.setSuffix(".jsp");
        menuController = new MenuController();
        dishService = mock(DishService.class);
        menuService = mock(MenuService.class);
        menuController.setDishService(dishService);
        menuController.setMenuService(menuService);
        mockMvc = MockMvcBuilders.standaloneSetup(menuController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testMenusActions() throws Exception {
        mockMvc.perform(get("/menus/"))
                .andExpect(status().isOk())
                .andExpect(view().name("menusActions"))
                .andExpect(forwardedUrl("/WEB-INF/views/jsp/menusActions.jsp"));
    }

    @Test
    public void testFindAllMenus() throws Exception {
        when(menuService.getAllMenus()).thenReturn(Arrays.asList(new Menu(), new Menu()));

        mockMvc.perform(get("/menus/showAll"))
                .andExpect(status().isOk())
                .andExpect(view().name("menusList"))
                .andExpect(forwardedUrl("/WEB-INF/views/jsp/menusList.jsp"))
                .andExpect(model().attribute("menus", hasSize(2)));
        verify(menuService, times(1)).getAllMenus();
        verifyNoMoreInteractions(menuService);
    }

    @Test
    public void testAddMenuForm() throws Exception {
        mockMvc.perform(get("/menus/addMenuForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("addMenuForm"))
                .andExpect(forwardedUrl("/WEB-INF/views/jsp/addMenuForm.jsp"));
    }

    @Test
    public void testSearchMenuForm() throws Exception {
        mockMvc.perform(get("/menus/searchForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("searchMenuByNameForm"))
                .andExpect(forwardedUrl("/WEB-INF/views/jsp/searchMenuByNameForm.jsp"));
    }

    @Test
    public void testFindByName() throws Exception {
        mockMvc.perform(post("/menus/findByName").param("menuName", "Menu_1"))
                .andExpect(status().isOk())
                .andExpect(view().name("menuNotFoundException"))
                .andExpect(forwardedUrl("/WEB-INF/views/jsp/menuNotFoundException.jsp"));
    }

    @Test
    public void testDeleteMenu() throws Exception {
        mockMvc.perform(post("/menus/deleteMenu").param("menuId", "1"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/menus/showAll"));

        verify(menuService, times(1)).deleteMenu(1);
        verifyNoMoreInteractions(menuService);
    }

    @Test
    public void testAddMenu() throws Exception {
        mockMvc.perform(post("/menus/addSubmit").param("menu", "menu"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/menus/showAll"));

        verify(menuService, times(1)).addMenu(new Menu());
        verifyNoMoreInteractions(menuService);
    }
}