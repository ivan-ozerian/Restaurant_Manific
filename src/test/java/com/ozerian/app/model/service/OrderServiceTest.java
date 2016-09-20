package com.ozerian.app.model.service;

import com.ozerian.app.model.dao.OrderDAO;
import com.ozerian.app.model.entity.Employee;
import com.ozerian.app.model.entity.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/application-context.xml",
        "file:src/main/webapp/WEB-INF/hibernate-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderServiceTest {

    private OrderService orderService;
    private OrderDAO orderDAO;
    private Order order;
    private List<Order> orders;
    private Employee employee;

    @Before
    public void setUp() {
        orderService = new OrderService();
        orderDAO = mock(OrderDAO.class);
        employee = mock(Employee.class);
        order = new Order(1, new Date(), employee, 1, true);
        orderService.setOrderDAO(orderDAO);
        orders = new ArrayList<>();
    }

    @Test
    public void testMockCreation() {
        assertNotNull(orderDAO);
        assertNotNull(employee);
    }

    @Test
    public void testAddOrder() {
        orderService.addOrder(order);
        verify(orderDAO, times(1)).addEntity(order);
    }

    @Test
    public void testDeleteOrder() {
        orderService.deleteOpenOrder(order.getId());
        verify(orderDAO, times(1)).deleteEntityById(order.getId());
    }

    @Test
    public void testGetAllOrders() {
        when(orderService.getOrderByStatus(true)).thenReturn(orders);
        assertEquals(new ArrayList<Order>(), orderService.getOrderByStatus(true));
        verify(orderDAO, times(1)).getOrderByStatus(true);
    }

    @Test
    public void testSearchOrder() {
        when(orderService.getOrderById(1)).thenReturn(order);
        assertEquals(order, orderService.getOrderById(1));
        verify(orderDAO, times(1)).searchEntityByID(order.getId());
    }

    @Test
    public void testSetOpenToCloseOrder() {
        orderService.closeOrder(order.getId());
        verify(orderDAO, times(1)).closeOrder(order.getId());
    }
}
