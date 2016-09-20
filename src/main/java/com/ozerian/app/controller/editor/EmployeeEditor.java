package com.ozerian.app.controller.editor;

import com.ozerian.app.model.entity.Employee;
import com.ozerian.app.model.service.EmployeeService;

import java.beans.PropertyEditorSupport;

public class EmployeeEditor extends PropertyEditorSupport {

    private EmployeeService employeeService;

    public EmployeeEditor(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        Employee employee = employeeService.getEmployeeById(Integer.valueOf(id));
        setValue(employee);
    }
}
