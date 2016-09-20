package com.ozerian.app.model.dao;

import com.ozerian.app.model.entity.DataBaseEntity;

import java.util.List;

/**
 * This interface contains all of the main
 * methods, which allow to provided the most
 * used operations for the entities.
 *
 * @param <T>
 */
public interface EntityDAO<T extends DataBaseEntity> {


    /**
     * This method is for addition entity to the database.
     *
     * @param entity DataBaseEntity object.
     */
    public void addEntity(T entity);

    /**
     * This method is for removal entity from the database.
     *
     * @param id int id of the entity.
     */
    public void deleteEntityById(int id);

    /**
     * This method is for search of the entity.
     *
     * @param id int of the entity.
     * @return DataBaseEntity found entity.
     */
    public T searchEntityByID(int id);

    /**
     * This method is for getting all of the existing
     * int the database entity.
     *
     * @return list of the obtained entities.
     */
    public List<T> getAllEntities();
}
