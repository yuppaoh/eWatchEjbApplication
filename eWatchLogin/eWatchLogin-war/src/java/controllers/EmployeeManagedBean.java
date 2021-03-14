/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.EmployeesFacadeLocal;
import entities.Employees;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author yup
 */
@Named(value = "employeeManagedBean")
@SessionScoped
public class EmployeeManagedBean implements Serializable {

    @EJB
    private EmployeesFacadeLocal employeesFacade;

    private Employees employee = new Employees();
    
    public EmployeeManagedBean() {
    }

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }
    
    
    
}
