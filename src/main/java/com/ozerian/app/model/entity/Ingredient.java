package com.ozerian.app.model.entity;

import javax.persistence.*;

/**
 * Class for representation of ingredients.
 */
@Entity
@Table(name = "ingredients")
@SecondaryTable(name = "storage",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "ingredient_id"))
public class Ingredient extends DataBaseEntity {

    @Column(name = "name", table = "ingredients")
    private String name;

    @Column(name = "quantity", table = "storage")
    private int quantity;

    /**
     * Empty constructor.
     */
    public Ingredient() {
    }

    /**
     * Constructor with all possible fields.
     *
     * @param id       int id of an ingredient.
     * @param name     String ingredient's name.
     * @param quantity int ingredient's quantity.
     */
    public Ingredient(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    /**
     * Get ingredient's name.
     *
     * @return String ingredient's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set ingredient's name.
     *
     * @param name String ingredient's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get ingredient's quantity.
     *
     * @return int ingredient's quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Set ingredient's quantity.
     *
     * @param quantity int ingredient's quantity.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Override equals method for Ingredient entity.
     *
     * @param o Object for comparison.
     * @return boolean "true" if objects are equals, "false" - if no.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient)) return false;

        Ingredient that = (Ingredient) o;

        if (quantity != that.quantity) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    /**
     * Override method hashCode.
     *
     * @return int hasCode value of this object.
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + quantity;
        return result;
    }
}
