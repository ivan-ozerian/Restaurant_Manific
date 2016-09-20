package com.ozerian.app.model.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Class-model for representation of dishes from a menu.
 */
@Entity
@Table(name = "dishes")
public class Dish extends DataBaseEntity {

    @Column(name = "dish_name")
    private String dishName;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private DishCategory category;

    @Column(name = "price")
    private double price;

    @Column(name = "weight")
    private int weight;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "dish_content",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> dishIngredients;

    /**
     * Empty constructor.
     */
    public Dish() {
    }

    /**
     * Constructor with all possible fields.
     *
     * @param id       int id of the dish.
     * @param dishName String name of the dish.
     * @param category String category of the dish.
     * @param price    double dish cost.
     * @param weight   int dish weight.
     */
    public Dish(int id, String dishName, DishCategory category, double price, int weight) {
        this.id = id;
        this.dishName = dishName;
        this.category = category;
        this.price = price;
        this.weight = weight;
    }

    /**
     * Get dish name.
     *
     * @return String dish name.
     */
    public String getDishName() {
        return dishName;
    }


    /**
     * Set dish name.
     *
     * @param dishName String dish name.
     */
    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    /**
     * Get dish category.
     *
     * @return DishCategory dish category.
     */
    public DishCategory getCategory() {
        return category;
    }

    /**
     * Set dish category.
     *
     * @param category DishCategory dish category.
     */
    public void setCategory(DishCategory category) {
        this.category = category;
    }

    /**
     * Get dish price.
     *
     * @return double dish price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Set dish price.
     *
     * @param price double dish price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Get dish weight.
     *
     * @return int dish weight.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Set dish weight.
     *
     * @param weight int dish weight.
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }


    /**
     * Get dish ingredients list.
     *
     * @return List dish ingredients.
     */
    public List<Ingredient> getDishIngredients() {
        return dishIngredients;
    }

    /**
     * Set dish ingredients list.
     *
     * @param dishIngredients List dish ingredients.
     */
    public void setDishIngredients(List<Ingredient> dishIngredients) {
        this.dishIngredients = dishIngredients;
    }

    /**
     * Override equals method for Dish entity.
     *
     * @param o Object for comparison.
     * @return boolean "true" if objects are equals, "false" - if no.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dish)) return false;

        Dish dish = (Dish) o;

        if (Double.compare(dish.price, price) != 0) return false;
        if (weight != dish.weight) return false;
        if (dishName != null ? !dishName.equals(dish.dishName) : dish.dishName != null) return false;
        return category == dish.category;

    }

    /**
     * Override method hashCode.
     *
     * @return int hasCode value of this object.
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = dishName != null ? dishName.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + weight;
        return result;
    }
}
