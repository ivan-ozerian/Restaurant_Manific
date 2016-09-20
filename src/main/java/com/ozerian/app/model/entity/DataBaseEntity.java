package com.ozerian.app.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Abstract class with id field, getter and setter.
 * Through this class inheritance of entities implements.
 */
@MappedSuperclass
public class DataBaseEntity {

    /**
     * Entity id.
     */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    protected int id;

    /**
     * Get entity id.
     *
     * @return int entity id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set entity id.
     *
     * @param id int entity id.
     */
    public void setId(int id) {
        this.id = id;
    }

}
