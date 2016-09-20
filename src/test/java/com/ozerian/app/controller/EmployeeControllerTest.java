package com.ozerian.app.controller;

import com.ozerian.app.model.entity.Employee;
import com.ozerian.app.model.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
public class EmployeeControllerTest {

    private MockMvc mockMvc;
    private EmployeeService employeeService;
    private Employee employee_1;
    private Employee employee_2;
    private EmployeeController employeeController;

    @Before
    public void setUp() {
        employeeController = new EmployeeController();
        employeeService = mock(EmployeeService.class);
        employeeController.setEmployeeService(employeeService);
        employee_1 = mock(Employee.class);
        employee_2 = mock(Employee.class);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void testEmployeesActions() throws Exception {
        mockMvc.perform(get("/employees/"))
                .andExpect(status().isOk())
                .andExpect(view().name("employeesActions"))
                .andExpect(forwardedUrl("employeesActions"));
    }

    @Test
    public void testFindAllEmployees() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employee_1, employee_2));

        mockMvc.perform(get("/employees/showAll"))
                .andExpect(status().isOk())
                .andExpect(view().name("employeesList"))
                .andExpect(forwardedUrl("employeesList"))
                .andExpect(model().attribute("employees", hasSize(2)));
        verify(employeeService, times(1)).getAllEmployees();
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    public void testAddEmployeeForm() throws Exception {
        mockMvc.perform(get("/employees/addForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("addEmployeeForm"))
                .andExpect(forwardedUrl("addEmployeeForm"));
    }

    @Test
    public void testSearchEmployeeForm() throws Exception {
        mockMvc.perform(get("/employees/searchForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("searchEmployeeByNameForm"))
                .andExpect(forwardedUrl("searchEmployeeByNameForm"));
    }

    @Test
    public void testFindByName() throws Exception {
        mockMvc.perform(post("/employees/findByName").param("empName", "Employee_1"))
                .andExpect(status().isOk())
                .andExpect(view().name("employeeByName"))
                .andExpect(forwardedUrl("employeeByName"))
                .andExpect(model().attributeExists("foundEmployees"));
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        mockMvc.perform(post("/employees/delete").param("empId", String.valueOf(employee_1.getId())))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/employees/showAll"));

        verify(employeeService, times(1)).deleteEmployee(employee_1.getId());
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    public void testAddEmployee() throws Exception {
        mockMvc.perform(post("/employees/addSubmit").param("employee", "employee"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/employees/showAll"));
    }
}