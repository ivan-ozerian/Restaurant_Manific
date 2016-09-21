package com.ozerian.app.model.dao;

import com.ozerian.app.model.entity.Employee;
import com.ozerian.app.model.entity.Position;
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
public class EmployeeDAO implements EntityDAO<Employee> {

    private SessionFactory sessionFactory;

    /**
     * This override method allows addition entity to the database.
     *
     * @param employee Employee employee entity.
     */
    @Override
    @Transactional
    public void addEntity(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.save(employee);
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
        String hql = "delete from Employee e where e.id = :employeeId";
        Query query = session.createQuery(hql);
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }

    /**
     * This override method is for the search entity by id.
     * After search this method returns entity.
     *
     * @param id int id of the search entity.
     * @return Employee employee entity.
     */
    @Override
    @Transactional
    public Employee searchEntityByID(int id) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select e from Employee e where e.id = :employeeId";
        Query query = session.createQuery(hql);
        query.setParameter("employeeId", id);
        return (Employee) query.uniqueResult();
    }

    /**
     * This method searches entities by name and
     * returns the list if the found entities.
     *
     * @param name String name of the search entity.
     * @return List of the found entities.
     */
    @Transactional
    public List<Employee> searchEmployeeByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select e from Employee e where e.name= :employeeName";
        Query query = session.createQuery(hql);
        query.setParameter("employeeName", name);
        return query.list();
    }

    /**
     * This method allows obtaining found employees by its position.
     *
     * @param position String position of the entity.
     * @return List of the found employees.
     */
    @Transactional
    public List<Employee> getEmployeesByPosition(Position position) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select e from Employee e where e.position = :employeePosition";
        Query query = session.createQuery(hql);
        query.setParameter("employeePosition", position);
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
    public List<Employee> getAllEntities() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select e from Employee e";
        return session.createQuery(hql).list();
    }

    /**
     * Update of an existing Employee.
     *
     * @param employee Employee for update.
     */
    @Transactional
    public void update(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.update(employee);
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
