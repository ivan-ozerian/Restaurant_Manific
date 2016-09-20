package com.ozerian.app.model.service;

import com.ozerian.app.model.dao.DishDAO;
import com.ozerian.app.model.entity.Dish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class is a wrapper for dao class for supporting of
 * transactions. It occurs with the help of DataSourceTransactional manager.
 */
public class DishService {

    private DishDAO dishDAO;

    private final static Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    /**
     * Transactional method for obtaining all Entities with
     * transactional support.
     *
     * @return List of Dish.
     */
    @Transactional
    public List<Dish> getAllDishes() {
        return dishDAO.getAllEntities();
    }

    /**
     * Transactional method for addition entity to the database
     * with transactional support.
     */
    @Transactional
    public void addDish(Dish dish) {
        Set<Dish> allDishes = new HashSet<>(dishDAO.getAllEntities());  // Check if added employee exists in the database.

        if (!allDishes.contains(dish)) {
            dishDAO.addEntity(dish);
        } else {
            LOGGER.error("Try to insert exist dish!");
        }
    }

    /**
     * Transactional method for delete entity from the database
     * with transactional support.
     */
    @Transactional
    public void deleteDish(int id) {
        dishDAO.deleteEntityById(id);
    }

    /**
     * Transactional method for obtaining found Entities by name
     * with transactional support.
     *
     * @return List of Dish.
     */
    @Transactional
    public List<Dish> searchDishByName(String name) {
        return dishDAO.searchDishByName(name);
    }

    /**
     * Transactional method for obtaining found Dish by
     * it's id with transactional support.
     *
     * @param id int id number of Dish.
     * @return Dish found Dish object.
     */
    @Transactional
    public Dish getDishById(int id) {
        return dishDAO.searchEntityByID(id);
    }

       /**
     * get dishDao object.
     *
     * @return DishDao dishDao object.
     */
    public DishDAO getDishDAO() {
        return dishDAO;
    }

    /**
     * set DishDao object.
     *
     * @param dishDAO DishDAO object.
     */
    @Autowired
    public void setDishDAO(DishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }
}
