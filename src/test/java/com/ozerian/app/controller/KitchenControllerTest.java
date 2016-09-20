package com.ozerian.app.controller;

import com.ozerian.app.model.service.EmployeeService;
import com.ozerian.app.model.service.OrderService;
import com.ozerian.app.model.service.PreparedDishService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/web-context.xml",
        "file:src/main/webapp/WEB-INF/application-context.xml",
        "file:src/main/webapp/WEB-INF/hibernate-context.xml",
        "file:src/main/webapp/WEB-INF/security-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class KitchenControllerTest {

    private MockMvc mockMvc;
    private OrderService orderService;
    private EmployeeService employeeService;
    private PreparedDishService preparedDishService;
    private KitchenController kitchenController;

    @Before
    public void setUp() {
        kitchenController = new KitchenController();
        orderService = mock(OrderService.class);
        employeeService = mock(EmployeeService.class);
        preparedDishService = mock(PreparedDishService.class);
        kitchenController.setOrderService(orderService);
        kitchenController.setEmployeeService(employeeService);
        kitchenController.setPreparedDishService(preparedDishService);
        mockMvc = MockMvcBuilders.standaloneSetup(kitchenController).build();
    }

    @Test
    public void testKitchenActions() throws Exception {
        mockMvc.perform(get("/kitchen/"))
                .andExpect(status().isOk())
                .andExpect(view().name("kitchenActions"))
                .andExpect(forwardedUrl("kitchenActions"));
    }

    @Test
    public void testChooseOrderForm() throws Exception {

        mockMvc.perform(get("/kitchen/chooseOrder"))
                .andExpect(status().isOk())
                .andExpect(view().name("chooseOrderForm"))
                .andExpect(forwardedUrl("chooseOrderForm"));
        verify(orderService, times(1)).getOrderByStatus(true);
        verifyNoMoreInteractions(orderService);
    }

    @Test
    public void testFindAllPreparedDIshes() throws Exception {

        mockMvc.perform(get("/kitchen/showAll"))
                .andExpect(status().isOk())
                .andExpect(view().name("preparedDishesList"))
                .andExpect(forwardedUrl("preparedDishesList"));
        verify(preparedDishService, times(1)).getPreparedDishes();
        verifyNoMoreInteractions(preparedDishService);
    }

}