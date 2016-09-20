package com.ozerian.app.model.service;

import com.ozerian.app.model.dao.OrderDAO;
import com.ozerian.app.model.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class OrderService {

    private OrderDAO orderDAO = new OrderDAO();

    /**
     * Transactional method for addition entity to the database
     * with transactional support.
     *
     * @param order Order added menu object.
     */
    @Transactional
    public void addOrder(Order order) {
        orderDAO.addEntity(order);
    }

    /**
     * Transactional method for getting orders by appropriate status with
     * the transactional support.
     *
     * @param status boolean status value ("close" or "open").
     * @return List of the found Order.
     */
    @Transactional
    public List<Order> getOrderByStatus(boolean status) {
        return orderDAO.getOrderByStatus(status);
    }

    /**
     * Transactional method for transfer of the opened order in closed status
     * with the transactional support.
     *
     * @param orderId int order id value for transfer to close status
     */
    @Transactional
    public void closeOrder(int orderId) {
        orderDAO.closeOrder(orderId);
    }

    /**
     * Transactional status for removal order by it's id with
     * transactional support.
     *
     * @param orderId int id of the deleted order.
     */
    @Transactional
    public void deleteOpenOrder(int orderId) {
        orderDAO.deleteEntityById(orderId);
    }

    /**
     * Transactional method for removal dish from the order
     * with transactional support.
     *
     * @param dishId int id of the deleted dish.
     */
    @Transactional
    public void deleteDishFromOrder(int dishId, int orderId) {
        orderDAO.deleteDishFromOrder(dishId, orderId);
    }

    /**
     * Transactional method for addition dish to the order
     * with transactional support.
     *
     * @param orderId int id of the order for dish addition.
     * @param dishId  int id of the added dish.
     */
    @Transactional
    public void addDishToOrder(int orderId, int dishId) {
        orderDAO.addDishToOrder(orderId, dishId);
    }

    /**
     * Transactional method for obtaining found Order by
     * it's id with transactional support.
     *
     * @param id int id number of Order.
     * @return Order found Order object.
     */
    @Transactional
    public Order getOrderById(int id) {
        return orderDAO.searchEntityByID(id);
    }

    /**
     * get OrderDao object.
     *
     * @return OrderDao orderDao object.
     */
    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    /**
     * set orderDao object.
     *
     * @param orderDAO orderDAO object.
     */
    @Autowired
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }
}
