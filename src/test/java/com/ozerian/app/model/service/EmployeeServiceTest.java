package com.ozerian.app.model.service;

import com.ozerian.app.model.dao.EmployeeDAO;
import com.ozerian.app.model.entity.Employee;
import com.ozerian.app.model.entity.Position;
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
public class EmployeeServiceTest {

    private EmployeeService employeeService;
    private EmployeeDAO employeeDAO;
    private Employee employee;
    private List<Employee> employees;

    @Before
    public void setUp() {
        employeeService = new EmployeeService();
        employeeDAO = mock(EmployeeDAO.class);
        employee = new Employee(1, "Employee", "Employee", new Date(), "123-15-15", 8000, Position.COOK);
        employeeService.setEmployeeDAO(employeeDAO);
        employees = new ArrayList<>();
    }

    @Test
    public void testMockCreation() {
        assertNotNull(employeeDAO);
    }

    @Test
    public void testAddEmployee() {
        employeeService.addEmployee(employee);
        verify(employeeDAO, times(1)).addEntity(employee);
    }

    @Test
    public void testDeleteEmployee() {
        employeeService.deleteEmployee(employee.getId());
        verify(employeeDAO, times(1)).deleteEntityById(employee.getId());
    }

    @Test
    public void testGetAllEmployees() {
        when(employeeService.getAllEmployees()).thenReturn(employees);
        assertEquals(new ArrayList<Employee>(), employeeService.getAllEmployees());
        verify(employeeDAO, times(1)).getAllEntities();
    }

    @Test
    public void testSearchEmployee() {
        when(employeeService.searchEmployeeByName("Employee")).thenReturn(employees);
        assertEquals(new ArrayList<Employee>(), employeeService.searchEmployeeByName("Employee"));
        verify(employeeDAO, times(1)).searchEmployeeByName(employee.getName());
    }

    @Test
    public void testGetEmployeeById() {
        when(employeeService.getEmployeeById(1)).thenReturn(employee);
        assertEquals(employee, employeeService.getEmployeeById(1));
        verify(employeeDAO, times(1)).searchEntityByID(1);
    }
}
