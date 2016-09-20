package com.ozerian.app.model.service;

import com.ozerian.app.model.dao.EmployeeDAO;
import com.ozerian.app.model.entity.Employee;
import com.ozerian.app.model.entity.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class is a wrapper for dao class for supporting of
 * transactions. It occurs with the help of DataSourceTransactional manager.
 */
public class EmployeeService {

    private EmployeeDAO employeeDAO;

    private final static Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    /**
     * Transactional method for obtaining found Entities by the name
     * of its position with transactional support.
     *
     * @param position String employee's position.
     * @return List of Employee.
     */
    @Transactional
    public List<Employee> getEmployeesByPosition(Position position) {
        return employeeDAO.getEmployeesByPosition(position);
    }

    /**
     * Transactional method for obtaining found Employee by
     * it's id with transactional support.
     *
     * @param id int id number of Employee.
     * @return Employee found Employee object.
     */
    @Transactional
    public Employee getEmployeeById(int id) {
        return employeeDAO.searchEntityByID(id);
    }

    /**
     * Transactional method for obtaining all Employee with
     * transactional support.
     *
     * @return List of Employee.
     */
    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEntities();
    }

    /**
     * Transactional method for addition of Employee object to
     * the database with transactional support.
     *
     * @param employee Employee added employee.
     */
    @Transactional
    public void addEmployee(Employee employee) {
        Set<Employee> allEmployees = new HashSet<>(employeeDAO.getAllEntities());  // Check if added employee exists in the database.

        if (!allEmployees.contains(employee)) {
            employeeDAO.addEntity(employee);
        } else {
            LOGGER.error("Try to insert exist employee!");
        }
    }

    /**
     * Transactional method for removal of Employee object from
     * the database with transactional support.
     *
     * @param id int id of deleted employee.
     */
    @Transactional
    public void deleteEmployee(int id) {
        employeeDAO.deleteEntityById(id);
    }

    /**
     * Transactional method for search of Employee by it's name
     * in the database with transactional support.
     *
     * @param name String name of found employee.
     * @return List of found Employees.
     */
    @Transactional
    public List<Employee> searchEmployeeByName(String name) {
        return employeeDAO.searchEmployeeByName(name);
    }

    /**
     * set EmployeeDao object.
     *
     * @param employeeDAO EmployeeDAO object.
     */
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
}
