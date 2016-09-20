package com.ozerian.app.model.dao;

import com.ozerian.app.model.entity.Ingredient;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * This class implements interface EntityDAO.
 * There are a lot of methods for accessing to database and obtaining appropriate results
 * after queries from the methods.
 */
public class IngredientDAO implements EntityDAO<Ingredient> {

    private SessionFactory sessionFactory;

    /**
     * This override method allows obtaining entity from the database anr returns it.
     *
     * @param ingredient ingredient ingredient entity.
     */
    @Override
    public void addEntity(Ingredient ingredient) {
        Session session = sessionFactory.getCurrentSession();
        session.save(ingredient);
    }

    /**
     * This override method removes entity from the database by Id.
     *
     * @param id int id of the removal entity.
     */
    @Override
    public void deleteEntityById(int id) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "delete from Ingredient i where i.id = :ingredientId";
        Query query = session.createQuery(hql);
        query.setParameter("ingredientId", id);
        query.executeUpdate();
    }

    /**
     * This override method is for the search entity by id.
     * After search this method returns entity.
     *
     * @param id int id of the search entity.
     * @return Ingredient ingredient entity.
     */
    @Override
    public Ingredient searchEntityByID(int id) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select i from Ingredient i where i.id = :ingredientId ";
        Query query = session.createQuery(hql);
        query.setParameter("ingredientId", id);
        return (Ingredient) query.uniqueResult();
    }

    /**
     * This method searches entities by name and
     * returns the list if the found entities.
     *
     * @param name String name of the search entity.
     * @return List of the found entities.
     */
    public Ingredient searchIngredientByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select i from Ingredient i where i.name like :ingredientName ";
        Query query = session.createQuery(hql);
        query.setParameter("ingredientName", name);
        return (Ingredient) query.uniqueResult();
    }

    /**
     * This override method allows getting all of the existing in the database
     * entities.
     *
     * @return List of the all existing entities.
     */
    @Override
    public List<Ingredient> getAllEntities() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select i from Ingredient i";
        Query query = session.createQuery(hql);
        return query.list();
    }

    /**
     * This method allows obtaining of expiring ingredients,
     * which are less than 10 units.
     *
     * @return List of expiring ingredients.
     */
    public List<Ingredient> getExpiringIngredients() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select i from Ingredient i where i.quantity < 10";
        Query query = session.createQuery(hql);
        return query.list();
    }

    /**
     * This method allows update the quantity of some ingredient by it's id.
     *
     * @param quantity int new quantity value of ingredient.
     * @param id       int id of updating ingredient.
     */
    public void updateIngredient(int quantity, int id) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "update Ingredient i set i.quantity = :quantity where i.id = :ingredientId";
        Query query = session.createQuery(hql);
        query.setParameter("ingredientId", id);
        query.setParameter("quantity", quantity);
        query.executeUpdate();
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
}
