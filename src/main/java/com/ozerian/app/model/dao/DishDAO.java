package com.ozerian.app.model.dao;

import com.ozerian.app.model.entity.Dish;
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
public class DishDAO implements EntityDAO<Dish> {

    private SessionFactory sessionFactory;

    /**
     * This override method allows addition entity to the database.
     *
     * @param dish Dish dish entity.
     */
    @Override
    @Transactional
    public void addEntity(Dish dish) {
        Session session = sessionFactory.getCurrentSession();
        session.save(dish);
    }

    /**
     * This override method removes entity from the database by Id.
     *
     * @param id int id of the removal entity.
     */
    @Override
    @Transactional
    public void deleteEntityById(int id) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "delete from Dish d where d.id = :dishId";
        Query query = session.createQuery(hql);
        query.setParameter("dishId", id);
        query.executeUpdate();
    }

    /**
     * This method allows delete entity from the database by Name.
     *
     * @param name String name of the removal entity.
     */
    @Transactional
    public void deleteDishesByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "delete from Dish d where d.dishName = :dishName";
        Query query = session.createQuery(hql);
        query.setParameter("dishName", name);
        query.executeUpdate();
    }

    /**
     * This override method is for the search entity by id.
     * After search this method returns entity.
     *
     * @param id int id of the search entity.
     * @return Dish dish entity.
     */
    @Override
    @Transactional
    public Dish searchEntityByID(int id) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select d from Dish d where d.id = :dishId";
        Query query = session.createQuery(hql);
        query.setParameter("dishId", id);
        return (Dish) query.uniqueResult();
    }

    /**
     * This method searches entities by name and
     * returns the list if the found entities.
     *
     * @param name String name of the search entity.
     * @return List of the found entities.
     */
    @Transactional
    public List<Dish> searchDishByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select d from Dish d where d.dishName = :dishName";
        Query query = session.createQuery(hql);
        query.setParameter("dishName", name);
        return query.list();
    }

    /**
     * This override method allows getting all of the existing in the database
     * entities.
     *
     * @return List of the all existing entities.
     */
    @Override
    @Transactional
    public List<Dish> getAllEntities() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select d from Dish d";
        Query query = session.createQuery(hql);
        return query.list();
    }

    /**
     * Set SessionFactory object.
     *
     * @param sessionFactory SessionFactory object.
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
