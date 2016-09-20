package com.ozerian.app.model.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * This class is for representation of preparedDish object.
 */
@Entity
@Table(name = "prepared_dishes")
public class PreparedDish extends DataBaseEntity{

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cook_id")
    private Employee cook;

    @Column(name = "prepared_time")
    private Date preparedTime;

    /**
     * Empty constructor.
     */
    public PreparedDish() {
    }

    /**
     * Constructor with all possible fields.
     *
     * @param order int id of the order.
     * @param dish int id of the dish from order.
     * @param cook int id of the cook.
     * @param preparedTime Date time of dish readiness.
     */
    public PreparedDish(Order order, Dish dish, Employee cook, Date preparedTime) {
        this.order = order;
        this.dish = dish;
        this.cook = cook;
        this.preparedTime = preparedTime;
    }

    /**
     * Get Order object.
     *
     * @return Order order's object.
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Set order object.
     *
     * @param order Order order object.
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Get Dish object.
     *
     * @return Dish dish object.
     */
    public Dish getDish() {
        return dish;
    }

    /**
     * Set Dish object.
     *
     * @param dish Dish dish object.
     */
    public void setDish(Dish dish) {
        this.dish = dish;
    }

    /**
     * Get cook's id.
     *
     * @return int cook's id.
     */
    public Employee getCook() {
        return cook;
    }

    /**
     * Set cook's id.
     *
     * @param cook int cook's id.
     */
    public void setCook(Employee cook) {
        this.cook = cook;
    }


    /**
     * Get time of readiness.
     *
     * @return Date dish time of readiness.
     */public Date getPreparedTime() {
        return preparedTime;
    }

    /**
     * Set time of readiness.
     *
     * @param preparedTime Date time of readiness.
     */
    public void setPreparedTime(Date preparedTime) {
        this.preparedTime = preparedTime;
    }

}
