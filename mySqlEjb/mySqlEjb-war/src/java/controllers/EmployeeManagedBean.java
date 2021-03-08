/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import cusc.beans.EmployeesFacadeLocal;
import cusc.entities.Employees;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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

    public Employees getEmployee() {
        return employee;
    }

    public void setEmployee(Employees employee) {
        this.employee = employee;
    }
    
    
    public EmployeeManagedBean() {
    }
    
    
    public List<Employees> findAll(){
        return this.employeesFacade.findAll();
    }
    
    public String login() {
        String page = "index";
        if (employeesFacade.login(employee)) {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("account", employee.getUserName());
            System.out.println("login true");
            page = "product";
        }else{
            page = "index";
        }
        System.out.println("============================================");
        System.out.println("page: " + page);
        return page;
    }

    public void logout() throws IOException {
        // this.httpServletRequest.getSession(false).invalidate();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
        session.invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }
    
}
