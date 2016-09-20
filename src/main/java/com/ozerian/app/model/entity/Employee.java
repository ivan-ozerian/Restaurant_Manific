package com.ozerian.app.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Class for representation of employees of a restaurant.
 */
@Entity
@Table(name = "employees")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@DiscriminatorValue(value = "employee")
public class Employee extends DataBaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birthdate")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date birthDate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "salary")
    private int salary;

    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    private Position position;


    /**
     * Empty constructor.
     */
    public Employee() {
    }

    /**
     * Constructor with all possible fields.
     *
     * @param id          int id of the employee.
     * @param name        String employee's name.
     * @param surname     String employee's surname.
     * @param birthDate   Date employee's date of the birth.
     * @param phoneNumber String employee's phone number.
     * @param salary      int employee's salary.
     * @param position    String employee's position.
     */
    public Employee(int id, String name, String surname, Date birthDate, String phoneNumber, int salary, Position position) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.position = position;
    }

    /**
     * Get employee's name.
     *
     * @return String employee's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set employee's name.
     *
     * @param name String employee's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get employee's surname.
     *
     * @return String employee's surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Set employee's surname.
     *
     * @param surname String employee's surname.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Get employee's date of birth.
     *
     * @return Date employee's date of birth.
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Set employee's date of birth.
     *
     * @param birthDate Date employee's date of birth.
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Get employee's phone number.
     *
     * @return String employee's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set employee's phone number.
     *
     * @param phoneNumber String employee's phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get employee's salary.
     *
     * @return int employee's salary.
     */
    public int getSalary() {
        return salary;
    }

    /**
     * Set employee's salary.
     *
     * @param salary int employee's salary.
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * Get employee's position.
     *
     * @return Position employee's position.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Set employee's position.
     *
     * @param position Position employee's position.
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Override equals method for Employee entity.
     *
     * @param o Object for comparison.
     * @return boolean "true" if objects are equals, "false" - if no.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        if (!super.equals(o)) return false;

        Employee employee = (Employee) o;

        if (salary != employee.salary) return false;
        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (surname != null ? !surname.equals(employee.surname) : employee.surname != null) return false;
        if (birthDate != null ? !birthDate.equals(employee.birthDate) : employee.birthDate != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(employee.phoneNumber) : employee.phoneNumber != null)
            return false;
        return position == employee.position;

    }

    /**
     * Override method hashCode.
     *
     * @return int hasCode value of this object.
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + salary;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
