package com.ozerian.app.model.service;

import com.ozerian.app.model.dao.PreparedDishDAO;
import com.ozerian.app.model.entity.PreparedDish;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/application-context.xml",
        "file:src/main/webapp/WEB-INF/hibernate-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class PreparedDishesServiceTest {

    private PreparedDishService preparedDishService;
    private PreparedDishDAO preparedDishDAO;
    private List<PreparedDish> preparedDishes;

    @Before
    public void setUp() {
        preparedDishService = new PreparedDishService();
        preparedDishDAO = mock(PreparedDishDAO.class);
        preparedDishService.setPreparedDishDAO(preparedDishDAO);
        preparedDishes = new ArrayList<>();
    }

    @Test
    public void testMockCreation() {
        assertNotNull(preparedDishDAO);
    }

    @Test
    public void testAddPreparedDish() {
        preparedDishService.addPreparedDish(1, 2, 3);
        verify(preparedDishDAO, times(1)).addPreparedDish(1, 2, 3);
    }

    @Test
    public void testGetAllPreparedDishes() {
        when(preparedDishService.getPreparedDishes()).thenReturn(preparedDishes);
        assertEquals(new ArrayList<PreparedDish>(), preparedDishService.getPreparedDishes());
        verify(preparedDishDAO, times(1)).getPreparedDishes();
    }

}
