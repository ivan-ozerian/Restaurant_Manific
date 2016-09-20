package com.ozerian.app.controller;

import com.ozerian.app.model.entity.Employee;
import com.ozerian.app.model.entity.Position;
import com.ozerian.app.model.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Class for requests handling regarding actions with Employee model.
 */
@Controller
@RequestMapping(value = "/employees")
public class EmployeeController {

    private EmployeeService employeeService;

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

    @RequestMapping(value = "/searchForm", method = RequestMethod.GET)
    public String searchEmployeeForm() {
        return "searchEmployeeByNameForm";
    }

    @RequestMapping(value = "/findByName", method = RequestMethod.POST)
    public String findByName(@RequestParam("empName") String employeeName, Model model) {
        model.addAttribute("foundEmployees", employeeService.searchEmployeeByName(employeeName));
        return "employeeByName";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteEmployee(@RequestParam("empId") String id) {
        employeeService.deleteEmployee(Integer.valueOf(id));
        return "redirect:/employees/showAll";
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}
