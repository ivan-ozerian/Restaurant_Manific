package com.ozerian.app.controller;

import com.ozerian.app.model.entity.Dish;
import com.ozerian.app.model.entity.DishCategory;
import com.ozerian.app.model.service.DishService;
import javassist.NotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/dishes")
public class DishController {

    private DishService dishService;

    private final static Logger LOGGER = LoggerFactory.getLogger(DishController.class);


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String dishesActions() {
        return "dishesActions";
    }

    @RequestMapping(value = "/showAll", method = RequestMethod.GET)
    public String dishes(Map<String, Object> model) {
        model.put("dishes", dishService.getAllDishes());
        return "dishesList";
    }

    @RequestMapping(value = "/addForm", method = RequestMethod.GET)
    public String addDishForm(Model model) {
        model.addAttribute("dish", new Dish());
        model.addAttribute("categories", DishCategory.values());
        return "addDishForm";
    }

    @RequestMapping(value = "/addSubmit", method = RequestMethod.POST)
    public String addSubmit(@ModelAttribute("dish") Dish dish) {
        dishService.addDish(dish);
        return "redirect:/dishes/showAll";
    }

    @RequestMapping(value = "/searchForm", method = RequestMethod.GET)
    public String searchDishForm() {
        return "searchDishByNameForm";
    }

    @RequestMapping(value = "/findByName", method = RequestMethod.POST)
    public String findByName(@RequestParam("dishName") String dishName, Model model) throws NotFoundException {
        List<Dish> dishes = dishService.searchDishByName(dishName);
        if (dishes.size() == 0) {
            throw new NotFoundException("Dish was not found");
        }
        model.addAttribute("foundDishes", dishes);
        return "dishByName";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteDish(@RequestParam("dishId") String id) {
        dishService.deleteDish(Integer.valueOf(id));
        return "redirect:/dishes/showAll";
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String violationException () {
        LOGGER.error("Trying to delete of dish with reference in other table");
        return "deleteDishException";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public String badRequestException () {
        LOGGER.error("Error during new dish creation (invalid or empty form's parameters)");
        return "creationDishException";
    }

    @ExceptionHandler(NotFoundException.class)
    public String notFoundException () {
        LOGGER.error("Dish wasn't found!");
        return "dishNotFoundException";
    }

    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }
}
