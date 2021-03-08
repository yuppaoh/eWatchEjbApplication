/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Employees;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import session.EmployeesFacadeLocal;

/**
 *
 * @author ASUS
 */
@Named(value = "userManagedBean")
@SessionScoped
public class UserManagedBean implements Serializable {

    @EJB
    private EmployeesFacadeLocal employeesFacade;
    private Employees user = new Employees();
    private String confirmpass;

    public String getConfirmpass() {
        return confirmpass;
    }

    public void setConfirmpass(String confirmpass) {
        this.confirmpass = confirmpass;
    }

    public UserManagedBean() {
    }

    public Employees getUser() {
        return user;
    }

    public void setUser(Employees user) {
        this.user = user;
    }

    public String regis() {
        if (employeesFacade.isUsernameExist(user.getUserName())) {
            RequestContext.getCurrentInstance().execute("alert('Username is existed, please try another username !!!');");
            return "login_register";
        } else if (employeesFacade.validUsernameLength(user.getUserName())) {
            return "login_register";
        } else if (employeesFacade.validPasswordLength(user.getUserPassword())) {
            return "login_register";
        } else if (!user.getUserPassword().equals(confirmpass)) {
            RequestContext.getCurrentInstance().execute("alert('Confirm password is not match!!!');");
            return "login_register";
        } else {
            RequestContext.getCurrentInstance().execute("alert('Registration completed, please login again !!!');");
            this.employeesFacade.create(user);
            this.user = new Employees();
            return "login_register";
        }
    }

    public String login() {
        String page = "index";
        if (employeesFacade.login(user)) {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("account", user.getUserName());
            page = "product";
        } else {
            RequestContext.getCurrentInstance().execute("alert('Username or password is incorrect !!!');");
            page = "login_register";

        }

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
