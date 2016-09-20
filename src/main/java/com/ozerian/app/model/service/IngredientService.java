package com.ozerian.app.model.service;

import com.ozerian.app.model.dao.IngredientDAO;
import com.ozerian.app.model.entity.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class IngredientService {

    private IngredientDAO ingredientDAO;

    /**
     * Transactional method for addition entity to the database
     * with transactional support.
     *
     * @param ingredient Ingredient added ingredient object.
     */
    @Transactional
    public void addIngredient(Ingredient ingredient) {
        ingredientDAO.addEntity(ingredient);
    }

    /**
     * Transactional method for search of the ingredients by its' name
     * with transactional support.
     *
     * @param name String name of the ingredient.
     * @return List of the found Ingredient.
     */
    @Transactional
    public Ingredient searchIngredientByName(String name) {
        return ingredientDAO.searchIngredientByName(name);
    }

    /**
     * Transactional method for getting all of the expiring
     * ingredients (which are less than 10 units) with transactional support.
     *
     * @return List of found Employee.
     */
    @Transactional
    public List<Ingredient> getExpiringIngredients() {
        return ingredientDAO.getExpiringIngredients();
    }

    /**
     * Transactional method for getting all ingredients from the
     * database.
     *
     * @return List of all Ingredient.
     */
    @Transactional
    public List<Ingredient> getAllIngredients() {
        return ingredientDAO.getAllEntities();
    }

    /**
     * Transactional method for delete ingredient by id
     * from the database with transactional support.
     *
     * @param id int id of deleted ingredient.
     */
    @Transactional
    public void deleteIngredientById(int id) {
        ingredientDAO.deleteEntityById(id);
    }

    /**
     * Transactional method for updating the quantity of some
     * ingredient (which will be found by id) with transactional support.
     *
     * @param quantity int new value of ingredient quantity.
     * @param id       int id of updated ingredient.
     */
    @Transactional
    public void updateIngredient(int quantity, int id) {
        ingredientDAO.updateIngredient(quantity, id);
    }

    /**
     * get EmployeeDao object.
     *
     * @return EmployeeDao employeeDao object.
     */
    public IngredientDAO getIngredientDAO() {
        return ingredientDAO;
    }

    /**
     * set IngredientDao object.
     *
     * @param ingredientDAO IngredientDAO object.
     */
    @Autowired
    public void setIngredientDAO(IngredientDAO ingredientDAO) {
        this.ingredientDAO = ingredientDAO;
    }
}
