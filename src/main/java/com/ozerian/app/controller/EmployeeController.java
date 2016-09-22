package com.ozerian.app.controller;

import com.ozerian.app.model.entity.Employee;
import com.ozerian.app.model.entity.Position;
import com.ozerian.app.model.service.EmployeeService;
import javassist.NotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Class for requests handling regarding actions with Employee model.
 */
@Controller
@RequestMapping(value = "/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    private final static Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String employeesActions() {
        return "employeesActions";
    }

    @RequestMapping(value = "/showAll", method = RequestMethod.GET)
    public String employees(Map<String, Object> model) {
        model.put("employees", employeeService.getAllEmployees());
        return "employeesList";
    }

    @RequestMapping(value = "/addForm", method = RequestMethod.GET)
    public String addEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("positions", Position.values());
        return "addEmployeeForm";
    }

    @RequestMapping(value = "/addSubmit", method = RequestMethod.POST)
    public String addSubmit(@ModelAttribute("employee") Employee employee) {
        employeeService.addEmployee(employee);
        return "redirect:/employees/showAll";
    }

    @RequestMapping(value = "/updateForm", method = RequestMethod.POST)
    public ModelAndView updateEmployeeForm(@RequestParam("empId") String id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("updateEmployeeForm");
        modelAndView.addObject("employeeId", id);
        modelAndView.addObject("employee", employeeService.getEmployeeById(Integer.valueOf(id)));
        modelAndView.addObject("positions", Position.values());
        return modelAndView;
    }

    @RequestMapping(value = "/updateSubmit", method = RequestMethod.POST)
    public String updateSubmit(@RequestParam("employeeId") String id,
                               @RequestParam("phone") String phone,
                               @RequestParam("salary") String salary,
                               @RequestParam("position") String position) {
        Employee employee = employeeService.getEmployeeById(Integer.valueOf(id));
        employee.setPhoneNumber(phone);
        employee.setSalary(Integer.valueOf(salary));
        employee.setPosition(Position.valueOf(position));
        employeeService.updateEmployee(employee);
        return "redirect:/employees/showAll";
    }

    @RequestMapping(value = "/searchForm", method = RequestMethod.GET)
    public String searchEmployeeForm() {
        return "searchEmployeeByNameForm";
    }

    @RequestMapping(value = "/findByName", method = RequestMethod.POST)
    public String findByName(@RequestParam("empName") String employeeName, Model model) throws NotFoundException {
        List<Employee> employeesByName = employeeService.searchEmployeeByName(employeeName);
        if (employeesByName.size() == 0) {
            throw new NotFoundException("Entity is not found");
        }
        model.addAttribute("foundEmployees", employeesByName);
        return "employeeByName";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteEmployee(@RequestParam("empId") String id) {
        employeeService.deleteEmployee(Integer.valueOf(id));
        return "redirect:/employees/showAll";
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String violationException() {
        LOGGER.error("Trying to delete of employee with reference in other table");
        return "deleteEmployeeException";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public String badRequestException() {
        LOGGER.error("Error during new employee creation (invalid or empty form's parameters)");
        return "creationEmployeeException";
    }

    @ExceptionHandler(NotFoundException.class)
    public String notFoundException() {
        LOGGER.error("Employee wasn't found!");
        return "employeeNotFoundException";
    }

    //REST for all employees obtaining.
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> allEmployees() {
        return employeeService.getAllEmployees();
    }

    @RequestMapping(value = "/{employeeId}", method = RequestMethod.GET)
    @ResponseBody
    public Employee employeeById(@PathVariable("employeeId") String employeeId) {
        return employeeService.getEmployeeById(Integer.valueOf(employeeId));
    }

    @RequestMapping(value = "/{employeeName}", method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> employeeByName(@PathVariable("employeeName") String employeeName) {
        return employeeService.searchEmployeeByName(employeeName);
    }

    @RequestMapping(value = "/{employeeName}/{employeeSurname}", method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> employeeByNameAndSurname(@PathVariable("employeeName") String employeeName,
                                                   @PathVariable("employeeSurname") String employeeSurname) {
        return employeeService.searchEmployeeByNameAndSurname(employeeName, employeeSurname);
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
