package com.ozerian.app.model.dao;

import com.ozerian.app.model.entity.Dish;
import com.ozerian.app.model.entity.Employee;
import com.ozerian.app.model.entity.Order;
import com.ozerian.app.model.entity.PreparedDish;
import com.ozerian.app.model.service.DishService;
import com.ozerian.app.model.service.EmployeeService;
import com.ozerian.app.model.service.OrderService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.List;


/**
 * This class for manipulations with PreparedDish entity. There are a lot of methods
 * for accessing to database and obtaining appropriate results
 * after queries from the methods.
 */
public class PreparedDishDAO {

    private SessionFactory sessionFactory;

    private OrderService orderService;

    private DishService dishService;

    private EmployeeService employeeService;

    /**
     * This method adds new Prepared dish entity to the database.
     *
     * @param orderId int id of the order with prepared dish.
     * @param dishId  int id of the dish from the database.
     * @param cookId  int id of the cook, who has prepared the dish.
     */
    public void addPreparedDish(int orderId, int dishId, int cookId) {
        PreparedDish preparedDish = new PreparedDish();
        Session session = sessionFactory.getCurrentSession();
        Order order = orderService.getOrderById(orderId);
        preparedDish.setOrder(order);
        Dish dish = dishService.getDishById(dishId);
        preparedDish.setDish(dish);
        Employee cook = employeeService.getEmployeeById(cookId);
        preparedDish.setCook(cook);
        preparedDish.setPreparedTime(new Date());
        session.save(preparedDish);
    }

    /**
     * This method allows getting all of the existing in the database
     * prepared dishes.
     *
     * @return List of the all existing in the database prepared dishes.
     */
    public List<PreparedDish> getPreparedDishes() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select pd from PreparedDish pd";
        Query query = session.createQuery(hql);
        return query.list();
    }

    /**
     * Get SessionFactory object.
     *
     * @return SessionFactory sessionFactory object.
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Set SessionFactory object.
     *
     * @param sessionFactory SessionFactory object.
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Get OrderService object.
     *
     * @return OrderService orderService object.
     */
    public OrderService getOrderService() {
        return orderService;
    }

    /**
     * Set OrderService object.
     *
     * @param orderService OrderService object.
     */
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
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
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }

    /**
     * Get EmployeeService object.
     *
     * @return EmployeeService employeeService object.
     */
    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    /**
     * Set EmployeeService object.
     *
     * @param employeeService EmployeeService object.
     */
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
