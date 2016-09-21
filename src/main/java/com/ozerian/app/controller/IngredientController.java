package com.ozerian.app.controller;

import com.ozerian.app.model.entity.Ingredient;
import com.ozerian.app.model.service.IngredientService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping(value = "/ingredients")
public class IngredientController {

    private IngredientService ingredientService;

    private final static Logger LOGGER = LoggerFactory.getLogger(IngredientController.class);


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String ingredientsActions() {
        return "ingredientsActions";
    }

    @RequestMapping(value = "/showAll", method = RequestMethod.GET)
    public String ingredients(Map<String, Object> model) {
        model.put("ingredients", ingredientService.getAllIngredients());
        return "ingredientsList";
    }

    @RequestMapping(value = "/addForm", method = RequestMethod.GET)
    public String addIngredientForm(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "addIngredientForm";
    }

    @RequestMapping(value = "/addSubmit", method = RequestMethod.POST)
    public String addSubmit(@ModelAttribute("ingredient") Ingredient ingredient) {
        ingredientService.addIngredient(ingredient);
        return "redirect:/ingredients/showAll";
    }

    @RequestMapping(value = "/searchForm", method = RequestMethod.GET)
    public String searchIngredientForm() {
        return "searchIngredientByNameForm";
    }

    @RequestMapping(value = "/findByName", method = RequestMethod.POST)
    public String findByName(@RequestParam("ingredientName") String ingredientName, Model model) {
        model.addAttribute("foundIngredient", ingredientService.searchIngredientByName(ingredientName));
        return "ingredientByName";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteEmployee(@RequestParam("ingredientId") String id) {
        ingredientService.deleteIngredientById(Integer.valueOf(id));
        return "redirect:/ingredients/showAll";
    }

    @RequestMapping(value = "/updateForm", method = RequestMethod.POST)
    public ModelAndView updateIngredientForm(@RequestParam("ingredientId") String id,
                                             @RequestParam("currentQuantity") String currentQuantity) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("updateIngredientForm");
        modelAndView.addObject("ingredientId", id);
        modelAndView.addObject("currentQuantity", currentQuantity);
        return modelAndView;
    }

    @RequestMapping(value = "/updateSubmit", method = RequestMethod.POST)
    public String updateSubmit(@RequestParam("ingredientId") String id,
                               @RequestParam("newQuantity") String newQuantity) {
        int quantity = Integer.valueOf(newQuantity);
        int ingredientId = Integer.valueOf(id);
        ingredientService.updateIngredient(quantity, ingredientId);
        return "redirect:/ingredients/showAll";
    }

    @RequestMapping(value = "/showExpiring", method = RequestMethod.GET)
    public String expiringIngredients(Map<String, Object> model) {
        model.put("ingredients", ingredientService.getExpiringIngredients());
        return "ingredientsList";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public String badRequestException () {
        LOGGER.error("Error during new ingredient creation (invalid or empty form's parameters)");
        return "creationIngredientException";
    }

    @ExceptionHandler(NotFoundException.class)
    public String notFoundException () {
        LOGGER.error("Ingredient wasn't found!");
        return "ingredientNotFoundException";
    }

    @Autowired
    public void setIngredientService(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
}
