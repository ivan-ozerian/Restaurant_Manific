package com.ozerian.app.controller;

import com.ozerian.app.controller.editor.EmployeeEditor;
import com.ozerian.app.model.entity.Dish;
import com.ozerian.app.model.entity.Employee;
import com.ozerian.app.model.entity.Order;
import com.ozerian.app.model.entity.Position;
import com.ozerian.app.model.service.DishService;
import com.ozerian.app.model.service.EmployeeService;
import com.ozerian.app.model.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/orders")
public class OrderController {

    private static final boolean ORDER_STATUS_OPEN = true;
    private static final boolean ORDER_STATUS_CLOSE = false;
    private OrderService orderService;
    private EmployeeService employeeService;
    private DishService dishService;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Employee.class, new EmployeeEditor(employeeService));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String ordersActions() {
        return "ordersActions";
    }

    @RequestMapping(value = "/addForm", method = RequestMethod.GET)
    public String addOrderForm(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("waiters", employeeService.getEmployeesByPosition(Position.WAITER));
        return "addOrderForm";
    }

    @RequestMapping(value = "/addSubmit", method = RequestMethod.POST)
    public String addSubmit(@ModelAttribute("order") Order order) {
        order.setOrderTime(new Date());
        order.setStatus(ORDER_STATUS_OPEN);
        orderService.addOrder(order);
        return "redirect:/orders/showOpen";
    }

    @RequestMapping(value = "/showOpen", method = RequestMethod.GET)
    public String openOrders(Map<String, Object> model) {
        model.put("orders", orderService.getOrderByStatus(ORDER_STATUS_OPEN));
        return "openOrdersList";
    }

    @RequestMapping(value = "/showClose", method = RequestMethod.GET)
    public String closeOrders(Map<String, Object> model) {
        model.put("orders", orderService.getOrderByStatus(ORDER_STATUS_CLOSE));
        return "closeOrdersList";
    }

    @RequestMapping(value = "/close", method = RequestMethod.POST)
    public String makeClose(@RequestParam("orderId") String id) {
        orderService.closeOrder(Integer.valueOf(id));
        return "redirect:/orders/showClose";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteOrder(@RequestParam("orderId") String id) {
        orderService.deleteOpenOrder(Integer.valueOf(id));
        return "redirect:/orders/showOpen";
    }

    @RequestMapping(value = "/addDishForm", method = RequestMethod.POST)
    public String addDishToOrderForm(Map<String, Object> model, @RequestParam("orderId") String id) {
        model.put("orderId", id);
        model.put("dishes", dishService.getAllDishes());
        return "addDishToOrderForm";
    }

    @RequestMapping(value = "/addDishSubmit", method = RequestMethod.POST)
    public String addDishToOrder(Model model, @RequestParam("orderId") String orderId,
                                 @RequestParam("dishId") String dishId) {
        orderService.addDishToOrder(Integer.valueOf(orderId), Integer.valueOf(dishId));
        model.addAttribute("orderId", orderId);
        return "redirect:/orders/orderDishes";
    }

    @RequestMapping(value = "/deleteFromOrder", method = RequestMethod.POST)
    public String deleteDishFromOrder(Model model, @RequestParam("orderId") String orderId,
                                      @RequestParam("dishId") String dishId) {
        orderService.deleteDishFromOrder(Integer.valueOf(dishId), Integer.valueOf(orderId));
        model.addAttribute("orderId", orderId);
        return "redirect:/orders/orderDishes";
    }

    @RequestMapping(value = "/orderDishes", method = {RequestMethod.POST, RequestMethod.GET})
    public String orderDishes(Model model, @RequestParam("orderId") String orderId) {
        List<Dish> orderDishes = orderService.getOrderById(Integer.valueOf(orderId)).getOrderDishes();
        model.addAttribute("orderDishes", orderDishes);
        model.addAttribute("orderId", orderId);
        return "dishesFromOrder";
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setDishService(DishService dishService) {
        this.dishService = dishService;
    }
}
