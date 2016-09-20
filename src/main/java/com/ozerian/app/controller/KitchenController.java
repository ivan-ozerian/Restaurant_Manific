package com.ozerian.app.controller;

import com.ozerian.app.model.entity.Position;
import com.ozerian.app.model.service.EmployeeService;
import com.ozerian.app.model.service.OrderService;
import com.ozerian.app.model.service.PreparedDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/kitchen")
public class KitchenController {

    private static final boolean ORDER_STATUS_OPEN = true;
    private OrderService orderService;
    private EmployeeService employeeService;
    private PreparedDishService preparedDishService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String kitchenActions() {
        return "kitchenActions";
    }

    @RequestMapping(value = "/chooseOrder", method = RequestMethod.GET)
    public ModelAndView chooseOrderForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("chooseOrderForm");
        modelAndView.addObject("orders", orderService.getOrderByStatus(ORDER_STATUS_OPEN));
        return modelAndView;
    }

    @RequestMapping(value = "/chooseDishAndCook", method = RequestMethod.POST)
    public ModelAndView chooseDishAndCookForm(@RequestParam("orderId") String orderId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("chooseDishAndCook");
        modelAndView.addObject("orderId", orderId);
        modelAndView.addObject("cooks", employeeService.getEmployeesByPosition(Position.COOK));
        modelAndView.addObject("dishes", orderService.getOrderById(Integer.valueOf(orderId)).getOrderDishes());
        return modelAndView;
    }

    @RequestMapping(value = "/showAll")
    public ModelAndView showAll() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("preparedDishes", preparedDishService.getPreparedDishes());
        modelAndView.setViewName("preparedDishesList");
        return modelAndView;
    }

    @RequestMapping(value = "/addSubmit", method = RequestMethod.POST)
    public String addSubmit(@RequestParam("cookId") String cookId,
                                  @RequestParam("dishId") String dishId,
                                  @RequestParam("orderId") String orderId) {
        preparedDishService.addPreparedDish(Integer.valueOf(orderId), Integer.valueOf(dishId), Integer.valueOf(cookId));
        return "redirect:/kitchen/showAll";
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setPreparedDishService(PreparedDishService preparedDishService) {
        this.preparedDishService = preparedDishService;
    }
}
