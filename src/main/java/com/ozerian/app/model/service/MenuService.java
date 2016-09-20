package com.ozerian.app.model.service;

import com.ozerian.app.model.dao.MenuDAO;
import com.ozerian.app.model.entity.Dish;
import com.ozerian.app.model.entity.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MenuService {

    private MenuDAO menuDAO;

    private DishService dishService;

    private final static Logger LOGGER = LoggerFactory.getLogger(MenuService.class);

    /**
     * Transactional method for getting all menus from the
     * database.
     *
     * @return List of all Menu.
     */
    @Transactional
    public List<Menu> getAllMenus() {
        return menuDAO.getAllEntities();
    }

    /**
     * Transactional method for addition entity to the database
     * with transactional support.
     *
     * @param menu Menu added menu object.
     */
    @Transactional
    public void addMenu(Menu menu) {
        menuDAO.addEntity(menu);
    }

    /**
     * Transactional method for delete menu by id
     * from the database with transactional support.
     *
     * @param id int id of deleted menu.
     */
    @Transactional
    public void deleteMenu(int id) {
        menuDAO.deleteEntityById(id);
    }

    /**
     * Transactional method for search of menu by it's name
     * in the database with transactional support.
     *
     * @param name String name of found menu.
     * @return List of found Menu.
     */
    @Transactional
    public Menu searchMenuByName(String name) {
        return menuDAO.searchMenuByName(name);
    }

    /**
     * Transactional method for delete the dish (by id) from the
     * menu by id with transactional support.
     *
     * @param id     int id of the dish for removal.
     * @param menuId int id of the menu from which dish will be deleted.
     */
    @Transactional
    public void deleteDishFromMenu(int id, int menuId) {
        menuDAO.deleteDishFromMenu(id, menuId);
    }


    /**
     * Transactional method for addition the dish (by id) to the
     * menu by id with transactional support. This method already contains
     * a check for content the same dishes in menu.
     *
     * @param dishId int id of the dish for addition.
     * @param menuId int id of the menu into which the dish will be added.
     */
    @Transactional
    public void addDishToMenu(int menuId, int dishId) {
        Set<Dish> dishesFromMenu = new HashSet<>(getMenuById(menuId).getMenuDishes());

        if (!dishesFromMenu.contains(dishService.getDishById(dishId))) {
            menuDAO.addDishToMenu(menuId, dishId);
        } else {
            LOGGER.error("This menu already contains this dish!");
        }
    }

    /**
     * Transactional method for obtaining found Menu by
     * it's id with transactional support.
     *
     * @param id int id number of Menu.
     * @return Menu found Menu object.
     */
    @Transactional
    public Menu getMenuById(int id) {
        return menuDAO.searchEntityByID(id);
    }

    /**
     * get MenuDao object.
     *
     * @return MenuDao menuDao object.
     */
    public MenuDAO getMenuDAO() {
        return menuDAO;
    }

    /**
     * set menuDao object.
     *
     * @param menuDAO menuDAO object.
     */
    @Autowired
    public void setMenuDAO(MenuDAO menuDAO) {
        this.menuDAO = menuDAO;
    }

    /**
     * Get DishService object.
     *
     * @return DishService dishService object.
     */
    public DishService getDishService() {
        return dishService;
    }

    /**
     * Set DishService object.
     *
     * @param dishService DishService object.
     */
    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }
}

