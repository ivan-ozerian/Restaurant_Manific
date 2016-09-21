package com.ozerian.app.controller;

import com.ozerian.app.model.entity.Menu;
import com.ozerian.app.model.service.DishService;
import com.ozerian.app.model.service.MenuService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/menus")
public class MenuController {

    private MenuService menuService;
    private DishService dishService;

    private final static Logger LOGGER = LoggerFactory.getLogger(MenuController.class);


    @RequestMapping(value = "/")
    public String menusActions() {
        return "menusActions";
    }

    @RequestMapping(value = "/showAll", method = RequestMethod.GET)
    public String menus(Model model) {
        model.addAttribute("menus", menuService.getAllMenus());
        return "menusList";
    }

    @RequestMapping(value = "/addMenuForm", method = RequestMethod.GET)
    public ModelAndView addMenuForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("menu", new Menu());
        modelAndView.setViewName("addMenuForm");
        return modelAndView;
    }

    @RequestMapping(value = "/addSubmit", method = RequestMethod.POST)
    public String addMenu(@ModelAttribute("menu") Menu menu) {
        menuService.addMenu(menu);
        return "redirect:/menus/showAll";
    }

    @RequestMapping(value = "/searchForm", method = RequestMethod.GET)
    public String searchMenuForm() {
        return "searchMenuByNameForm";
    }

    @RequestMapping(value = "/findByName", method = RequestMethod.POST)
    public String findByName(@RequestParam("menuName") String menuName, Model model) {
        model.addAttribute("menu", menuService.searchMenuByName(menuName));
        return "menuByName";
    }

    @RequestMapping(value = "/deleteMenu", method = RequestMethod.POST)
    public String deleteMenu(@RequestParam("menuId") String menuId) {
        int id = Integer.valueOf(menuId);
        menuService.deleteMenu(id);
        return "redirect:/menus/showAll";
    }

    @RequestMapping(value = "addDishForm", method = RequestMethod.POST)
    public ModelAndView addDishForm(@RequestParam("menuName") String menuName,
                                    @RequestParam("menuId") String menuId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dishes", dishService.getAllDishes());
        modelAndView.addObject("menuName", menuName);
        modelAndView.addObject("menuId", menuId);
        modelAndView.setViewName("addDishToMenuForm");
        return modelAndView;
    }

    @RequestMapping(value = "/addDishSubmit", method = RequestMethod.POST)
    public String addDishSubmit(Model model,
                                @RequestParam("dishId") String dishId,
                                @RequestParam("menuId") String menuId,
                                @RequestParam("menuName") String menuName) {
        menuService.addDishToMenu(Integer.valueOf(menuId), Integer.valueOf(dishId));
        model.addAttribute("menuId", menuId);
        model.addAttribute("menuName", menuName);
        return "redirect:/menus/showMenuDishes";
    }

    @RequestMapping(value = "/showMenuDishes", method = RequestMethod.GET)
    public ModelAndView showMenuDishes(@RequestParam("menuId") String menuId,
                                       @RequestParam("menuName") String menuName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dishes", menuService.getMenuById(Integer.valueOf(menuId)).getMenuDishes());
        modelAndView.addObject("menuId", menuId);
        modelAndView.addObject("menuName", menuName);
        modelAndView.setViewName("dishesFromMenu");
        return modelAndView;
    }

    @RequestMapping(value = "/deleteDish", method = RequestMethod.POST)
    public String deleteDishFromMenu(Model model,
                                     @RequestParam("menuId") String menuId,
                                     @RequestParam("dishId") String dishId,
                                     @RequestParam("menuName") String menuName) {
        menuService.deleteDishFromMenu(Integer.valueOf(dishId), Integer.valueOf(menuId));
        model.addAttribute("menuId", menuId);
        model.addAttribute("menuName", menuName);
        return "redirect:/menus/showMenuDishes";
    }

    @ExceptionHandler(NotFoundException.class)
    public String notFoundException () {
        LOGGER.error("Menu wasn't found!");
        return "menuNotFoundException";
    }


    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }
}
