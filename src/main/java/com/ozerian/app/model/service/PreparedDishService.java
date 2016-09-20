package com.ozerian.app.model.service;

import com.ozerian.app.model.dao.PreparedDishDAO;
import com.ozerian.app.model.entity.PreparedDish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class PreparedDishService {

    private PreparedDishDAO preparedDishDAO;

    /**
     * Transactional method for addition of the prepared dish to the database
     * with transactional support.
     *
     * @param orderId int id of the order with prepared dish.
     * @param dishId  int id of the dish which is beign prepared.
     * @param cookId  int id of the cook who's prepared the dish.
     */
    @Transactional
    public void addPreparedDish(int orderId, int dishId, int cookId) {
        preparedDishDAO.addPreparedDish(orderId, dishId, cookId);
    }

    /**
     * Transactional method for getting all of the prepared dishes.
     *
     * @return List of the prepared dishes.
     */
    @Transactional
    public List<PreparedDish> getPreparedDishes() {
        return preparedDishDAO.getPreparedDishes();
    }

    /**
     * get PreparedDishDao object.
     *
     * @return PreparedDishDao preparedDishDao object.
     */
    public PreparedDishDAO getPreparedDishDAO() {
        return preparedDishDAO;
    }

    /**
     * set preparedDishDao object.
     *
     * @param preparedDishDAO PreparedDishDAO object.
     */
    @Autowired
    public void setPreparedDishDAO(PreparedDishDAO preparedDishDAO) {
        this.preparedDishDAO = preparedDishDAO;
    }
}
