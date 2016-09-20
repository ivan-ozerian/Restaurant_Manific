package com.ozerian.app.controller;

import com.ozerian.app.model.entity.Dish;
import com.ozerian.app.model.entity.DishCategory;
import com.ozerian.app.model.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping(value = "/dishes")
public class DishController {

    private DishService dishService;

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
    public String findByName(@RequestParam("dishName") String dishName, Model model) {
        model.addAttribute("foundDishes", dishService.searchDishByName(dishName));
        return "dishByName";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteDish(@RequestParam("dishId") String id) {
        dishService.deleteDish(Integer.valueOf(id));
        return "redirect:/dishes/showAll";
    }

    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }
}
