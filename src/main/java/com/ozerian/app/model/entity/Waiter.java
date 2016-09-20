package com.ozerian.app.model.entity;

import javax.persistence.*;
import java.util.List;

/**
 * This class is for representation of a Waiter entity,
 * which is a child class for Employee with the inheritance
 * strategy - single table.
 */
@Entity
@Table(name = "employees")
@DiscriminatorColumn(name = "type")
@DiscriminatorValue(value = "waiter")
public class Waiter extends Employee {

    @OneToMany
    @JoinColumn(name = "waiter_id")
    private List<Order> orders;

    /**
     * Get List of waiter's orders.
     *
     * @return List of the waiter's orders.
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * Set of the List with waiter's orders.
     *
     * @param orders List of the waiter's orders.
     */
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
