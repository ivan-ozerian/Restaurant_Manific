package com.ozerian.app.model.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Class for representation of restaurant's menus.
 */
@Entity
@Table(name = "menus" )
public class Menu extends DataBaseEntity {

    @Column(name = "menu_name")
    private String menuName;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "dishes_menu",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<Dish> menuDishes;

    /**
     * Empty constructor.
     */
    public Menu() {
    }

    /**
     * Constructor with all possible fields.
     *
     * @param id       int id of the menu.
     * @param menuName String menu's name.
     */
    public Menu(int id, String menuName) {
        this.id = id;
        this.menuName = menuName;
    }

    /**
     * Get menu's name.
     *
     * @return String menu's name.
     */
    public String getMenuName() {
        return menuName;
    }


    /**
     * Set menu's name.
     *
     * @param menuName String menu's name.
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * Get dishes from menu list.
     *
     * @return List dish ingredients.
     */
    public List<Dish> getMenuDishes() {
        return menuDishes;
    }

    /**
     * Set dishes from menu list.
     *
     * @param menuDishes List of dishes from menu.
     */
    public void setMenuDishes(List<Dish> menuDishes) {
        this.menuDishes = menuDishes;
    }

    /**
     * Override equals method for Menu entity.
     *
     * @param o Object for comparison.
     * @return boolean "true" if objects are equals, "false" - if no.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;

        Menu menu = (Menu) o;

        if (menuName != null ? !menuName.equals(menu.menuName) : menu.menuName != null) return false;
        return menuDishes != null ? menuDishes.equals(menu.menuDishes) : menu.menuDishes == null;

    }

    /**
     * Override method hashCode.
     *
     * @return int hasCode value of this object.
     */
    @Override
    public int hashCode() {
        int result = menuName != null ? menuName.hashCode() : 0;
        result = 31 * result + (menuDishes != null ? menuDishes.hashCode() : 0);
        return result;
    }
}
