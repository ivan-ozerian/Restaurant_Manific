package com.ozerian.app.controller;

import com.ozerian.app.model.entity.Order;
import com.ozerian.app.model.service.DishService;
import com.ozerian.app.model.service.EmployeeService;
import com.ozerian.app.model.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/web-context.xml",
        "file:src/main/webapp/WEB-INF/application-context.xml",
        "file:src/main/webapp/WEB-INF/hibernate-context.xml",
        "file:src/main/webapp/WEB-INF/security-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderControllerTest {

    private MockMvc mockMvc;
    private DishService dishService;
    private EmployeeService employeeService;
    private OrderService orderService;
    private OrderController orderController;

    @Before
    public void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/jsp/");
        viewResolver.setSuffix(".jsp");
        orderController = new OrderController();
        dishService = mock(DishService.class);
        employeeService = mock(EmployeeService.class);
        orderService = mock(OrderService.class);
        orderController.setOrderService(orderService);
        orderController.setDishService(dishService);
        orderController.setEmployeeService(employeeService);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testOrdersActions() throws Exception {
        mockMvc.perform(get("/orders/"))
                .andExpect(status().isOk())
                .andExpect(view().name("ordersActions"))
                .andExpect(forwardedUrl("/WEB-INF/views/jsp/ordersActions.jsp"));
    }

    @Test
    public void testFindOpenOrders() throws Exception {
        when(orderService.getOrderByStatus(true)).thenReturn(Arrays.asList(new Order(), new Order()));

        mockMvc.perform(get("/orders/showOpen"))
                .andExpect(status().isOk())
                .andExpect(view().name("openOrdersList"))
                .andExpect(forwardedUrl("/WEB-INF/views/jsp/openOrdersList.jsp"))
                .andExpect(model().attribute("orders", hasSize(2)));
        verify(orderService, times(1)).getOrderByStatus(true);
        verifyNoMoreInteractions(orderService);
    }

    @Test
    public void testFindCloseOrders() throws Exception {
        when(orderService.getOrderByStatus(false)).thenReturn(Arrays.asList(new Order(), new Order()));

        mockMvc.perform(get("/orders/showClose"))
                .andExpect(status().isOk())
                .andExpect(view().name("closeOrdersList"))
                .andExpect(forwardedUrl("/WEB-INF/views/jsp/closeOrdersList.jsp"))
                .andExpect(model().attribute("orders", hasSize(2)));
        verify(orderService, times(1)).getOrderByStatus(false);
        verifyNoMoreInteractions(orderService);
    }

    @Test
    public void testCloseOrder() throws Exception {
        mockMvc.perform(post("/orders/close").param("orderId", "1"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/orders/showClose"));

        verify(orderService, times(1)).closeOrder(1);
        verifyNoMoreInteractions(orderService);
    }

    @Test
    public void testAddOrderForm() throws Exception {
        mockMvc.perform(get("/orders/addForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("addOrderForm"))
                .andExpect(forwardedUrl("/WEB-INF/views/jsp/addOrderForm.jsp"));
    }

    @Test
    public void testDeleteOrder() throws Exception {
        mockMvc.perform(post("/orders/delete").param("orderId", "1"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/orders/showOpen"));

        verify(orderService, times(1)).deleteOpenOrder(1);
        verifyNoMoreInteractions(orderService);
    }

    @Test
    public void testAddOrder() throws Exception {
        mockMvc.perform(post("/orders/addSubmit").param("order", "order"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/orders/showOpen"));
    }
}