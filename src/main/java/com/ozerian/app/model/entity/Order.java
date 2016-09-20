package com.ozerian.app.model.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Class for representation of the order entity.
 */
@Entity
@Table(name = "orders")
public class Order extends DataBaseEntity {

    @Column(name = "order_time")
    private Date orderTime;

    @ManyToOne
    @JoinColumn(name = "waiter_id")
    private Employee waiter;

    @Column(name = "table_number")
    private int tableNumber;

    @Column(name = "status")
    private boolean status;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_content",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<Dish> orderDishes;

    /**
     * Empty constructor.
     */
    public Order() {
    }

    /**
     * Constructor with all possible fields.
     *
     * @param id          int id of an order.
     * @param orderTime   Date time of acceptance of the order.
     * @param waiter      int id of the order's waiter.
     * @param tableNumber int number of the order's table.
     * @param status      boolean status of the order ("open" or "close").
     */
    public Order(int id, Date orderTime, Employee waiter, int tableNumber, boolean status) {
        this.id = id;
        this.orderTime = orderTime;
        this.waiter = waiter;
        this.tableNumber = tableNumber;
        this.status = status;
    }

    /**
     * Get order's time.
     *
     * @return Date order's time.
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     * Set order's time.
     *
     * @param orderTime Date order's time.
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * Get waiter object.
     *
     * @return int waiter's id.
     */
    public Employee getWaiter() {
        return waiter;
    }

    /**
     * Set waiter object.
     *
     * @param waiter int waiter's id.
     */
    public void setWaiter(Employee waiter) {
        this.waiter = waiter;
    }

    /**
     * Get table's number.
     *
     * @return int table's number.
     */
    public int getTableNumber() {
        return tableNumber;
    }

    /**
     * Set table's number.
     *
     * @param tableNumber int table's number.
     */
    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    /**
     * Get order's status.
     *
     * @return boolean order's status.
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Set order's status.
     *
     * @param status boolean order's status.
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Get dishes from order list.
     *
     * @return List dishes from order.
     */
    public List<Dish> getOrderDishes() {
        return orderDishes;
    }

    /**
     * Set dishes from order list.
     *
     * @param orderDishes List of dishes from order.
     */
    public void setOrderDishes(List<Dish> orderDishes) {
        this.orderDishes = orderDishes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (tableNumber != order.tableNumber) return false;
        if (status != order.status) return false;
        if (orderTime != null ? !orderTime.equals(order.orderTime) : order.orderTime != null) return false;
        if (waiter != null ? !waiter.equals(order.waiter) : order.waiter != null) return false;
        return orderDishes != null ? orderDishes.equals(order.orderDishes) : order.orderDishes == null;

    }

    @Override
    public int hashCode() {
        int result = orderTime != null ? orderTime.hashCode() : 0;
        result = 31 * result + (waiter != null ? waiter.hashCode() : 0);
        result = 31 * result + tableNumber;
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + (orderDishes != null ? orderDishes.hashCode() : 0);
        return result;
    }
}
