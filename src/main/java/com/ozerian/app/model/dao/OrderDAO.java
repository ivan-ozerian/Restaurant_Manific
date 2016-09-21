package com.ozerian.app.model.dao;

import com.ozerian.app.model.entity.Dish;
import com.ozerian.app.model.entity.Order;
import com.ozerian.app.model.service.DishService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This class implements interface EntityDAO.
 * There are a lot of methods for accessing to database and obtaining appropriate results
 * after queries from the methods.
 */
public class OrderDAO implements EntityDAO<Order> {

    private SessionFactory sessionFactory;

    private DishService dishService;

    /**
     * This override method allows obtaining entity from the database anr returns it.
     *
     * @param order Order order entity.
     */
    @Override
    public void addEntity(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.save(order);
    }

    /**
     * This method allows getting orders by its' status
     * ("open" or "close").
     *
     * @param status boolean order's status.
     * @return List of orders with appropriate status.
     */
    public List<Order> getOrderByStatus(boolean status) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select o from Order o where o.status = :status";
        Query query = session.createQuery(hql);
        query.setParameter("status", status);
        return query.list();
    }

    /**
     * This override method removes entity from the database by Id.
     *
     * @param id int id of the removal entity.
     */
    @Override
    public void deleteEntityById(int id) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "delete from Order o where o.id = :orderId";
        Query query = session.createQuery(hql);
        query.setParameter("orderId", id);
        query.executeUpdate();
    }

    /**
     * This override method is for the search entity by id.
     * After search this method returns entity.
     *
     * @param id int id of the search entity.
     * @return Order order entity.
     */
    @Override
    public Order searchEntityByID(int id) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select o from Order o where o.id = :orderId";
        Query query = session.createQuery(hql);
        query.setParameter("orderId", id);
        return (Order) query.uniqueResult();
    }

    /**
     * This override method allows getting all of the existing in the database
     * entities.
     *
     * @return List of the all existing entities.
     */
    @Override
    public List<Order> getAllEntities() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select o from Order o";
        return session.createQuery(hql).list();
    }

    /**
     * This method is for closing the order. Namely it makes
     * "open" order to "close" order.
     *
     * @param orderId int id of closing order.
     */
    public void closeOrder(int orderId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "update Order o set o.status = false where o.id = :orderId";
        Query query = session.createQuery(hql);
        query.setParameter("orderId", orderId);
        query.executeUpdate();
    }

    /**
     * This method is for removal some dish from the order.
     *
     * @param dishId  int id of the removal dish.
     * @param orderId int id of the order from which we remove the dish.
     */
    public void deleteDishFromOrder(int dishId, int orderId) {
        Session session = sessionFactory.getCurrentSession();
        Order order = searchEntityByID(orderId);
        Dish dish = dishService.getDishById(dishId);
        order.getOrderDishes().remove(dish);
        session.save(order);
    }

    /**
     * This method is for addition some dish into the menu.
     *
     * @param orderId int id of the order into which we add the dish.
     * @param dishId  int id of the added dish.
     */
    public void addDishToOrder(int orderId, int dishId) {
        Session session = sessionFactory.getCurrentSession();
        Order order = searchEntityByID(orderId);
        Dish dish = dishService.getDishById(dishId);
        order.getOrderDishes().add(dish);
        session.save(order);
    }

    /**
     * Find orders by waiter's surname.
     *
     * @param surname String waiter's surname.
     * @return List of orders.
     */
    @Transactional
    public List<Order> findByWaiterSurname(String surname) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select o from Order o where o.waiter.surname =:waiterSurname";
        Query query = session.createQuery(hql);
        query.setParameter("waiterSurname", surname);
        return query.list();
    }

    /**
     * Find orders by waiter's table number.
     *
     * @param tableNumber String table number.
     * @return List of orders.
     */
    @Transactional
    public List<Order> findByTableNumber(int tableNumber) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select o from Order o where o.tableNumber =:tableNumber";
        Query query = session.createQuery(hql);
        query.setParameter("tableNumber", tableNumber);
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
}
