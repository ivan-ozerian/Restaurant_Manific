package com.ozerian.app.model.entity;

import javax.persistence.*;
import java.util.List;

/**
 * This class is for representation of a Cook entity,
 * which is a child class for Employee with the inheritance
 * strategy - single table.
 */
@Entity
@Table(name = "employees")
@DiscriminatorColumn(name = "type")
@DiscriminatorValue(value = "cook")
public class Cook extends Employee {

    @OneToMany
    @JoinColumn(name = "cook_id")
    private List<PreparedDish> preparedDishes;

    /**
     * Get List of cook's prepared dishes.
     *
     * @return List of cook's prepared dishes.
     */
    public List<PreparedDish> getPreparedDishes() {
        return preparedDishes;
    }

    /**
     * Set List of cook's prepared dishes.
     *
     * @param preparedDishes List of cook's prepared dishes
     */
    public void setPreparedDishes(List<PreparedDish> preparedDishes) {
        this.preparedDishes = preparedDishes;
    }
}
