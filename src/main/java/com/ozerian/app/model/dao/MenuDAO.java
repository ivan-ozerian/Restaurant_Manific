package com.ozerian.app.model.dao;

import com.ozerian.app.model.entity.Dish;
import com.ozerian.app.model.entity.Menu;
import com.ozerian.app.model.service.DishService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * This class implements interface EntityDAO.
 * There are a lot of methods for accessing to database and obtaining appropriate results
 * after queries from the methods.
 */
public class MenuDAO implements EntityDAO<Menu> {

    private SessionFactory sessionFactory;

    private DishService dishService;

    /**
     * This override method allows obtaining entity from the database anr returns it.
     *
     * @param menu Menu menu entity.
     */
    @Override
    public void addEntity(Menu menu) {
        Session session = sessionFactory.getCurrentSession();
        session.save(menu);
    }

    /**
     * This override method removes entity from the database by Id.
     *
     * @param id int dishId of the removal entity.
     */
    @Override
    public void deleteEntityById(int id) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "delete from Menu m where m.id = :menuId";
        Query query = session.createQuery(hql);
        query.setParameter("menuId", id);
        query.executeUpdate();
    }

    /**
     * This override method is for the search entity by dishId.
     * After search this method returns entity.
     *
     * @param id int dishId of the search entity.
     * @return Menu menu entity.
     */
    @Override
    public Menu searchEntityByID(int id) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select m from Menu m where m.id = :menuId";
        Query query = session.createQuery(hql);
        query.setParameter("menuId", id);
        return (Menu) query.uniqueResult();
    }

    /**
     * This method is for the search menu by it's dishId.
     * After search this method returns Menu object.
     *
     * @param name String name of the search entity.
     * @return Menu menu entity.
     */
    public Menu searchMenuByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select m from Menu m where m.menuName like :name";
        Query query = session.createQuery(hql);
        query.setParameter("name", name);
        return (Menu) query.uniqueResult();
    }

    /**
     * This method is for removal some dish from the menu.
     *
     * @param dishId     int dishId of the removal dish.
     * @param menuId int dishId of the menu from which we remove the dish.
     */
    public void deleteDishFromMenu(int dishId, int menuId) {
        Session session = sessionFactory.getCurrentSession();
        Dish dish = dishService.getDishById(dishId);
        Menu menu = searchEntityByID(menuId);
        menu.getMenuDishes().remove(dish);
        session.save(menu);
    }

    /**
     * This override method allows getting all of the existing in the database
     * entities.
     *
     * @return List of the all existing entities.
     */
    @Override
    public List<Menu> getAllEntities() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select m from Menu m";
        Query query = session.createQuery(hql);
        return query.list();
    }

    /**
     * This method is for addition some dish into the menu.
     *
     * @param dishId int dishId of the added dish.
     * @param menuId int dishId of the menu into which we add the dish.
     */
    public void addDishToMenu(int menuId, int dishId) {
        Session session = sessionFactory.getCurrentSession();
        Dish dish = dishService.getDishById(dishId);
        Menu menu = searchEntityByID(menuId);
        menu.getMenuDishes().add(dish);
        session.save(menu);
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
