package com.ozerian.app.model.service;

import com.ozerian.app.model.dao.MenuDAO;
import com.ozerian.app.model.entity.Menu;
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
public class MenuServiceTest {

    private MenuService menuService;
    private MenuDAO menuDAO;
    private Menu menu;
    private List<Menu> menus;

    @Before
    public void setUp() {
        menuService = new MenuService();
        menuDAO = mock(MenuDAO.class);
        menu = new Menu(1, "Menu");
        menuService.setMenuDAO(menuDAO);
        menus = new ArrayList<>();
    }

    @Test
    public void testMockCreation() {
        assertNotNull(menuDAO);
    }

    @Test
    public void testAddMenu() {
        menuService.addMenu(menu);
        verify(menuDAO, times(1)).addEntity(menu);
    }

    @Test
    public void testDeleteMenu() {
        menuService.deleteMenu(menu.getId());
        verify(menuDAO, times(1)).deleteEntityById(menu.getId());
    }

    @Test
    public void testGetAllMenus() {
        when(menuService.getAllMenus()).thenReturn(menus);
        assertEquals(new ArrayList<Menu>(), menuService.getAllMenus());
        verify(menuDAO, times(1)).getAllEntities();
    }

    @Test
    public void testSearchMenu() {
        when(menuService.searchMenuByName("Menu")).thenReturn(menu);
        assertEquals(menu, menuService.searchMenuByName("Menu"));
        verify(menuDAO, times(1)).searchMenuByName(menu.getMenuName());
    }

    @Test
    public void testGetMenuById() {
        when(menuService.getMenuById(1)).thenReturn(menu);
        assertEquals(menu, menuService.getMenuById(1));
        verify(menuDAO, times(1)).searchEntityByID(1);
    }
}
