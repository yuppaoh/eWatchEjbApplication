/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.CustomersFacadeLocal;
import entities.Customers;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author yup
 */
@Named(value = "customerManagedBean")
@SessionScoped
public class CustomerManagedBean implements Serializable {

    @EJB
    private CustomersFacadeLocal customersFacade;
    private Customers customer = new Customers();
    private String confirmpass;

    public CustomerManagedBean() {
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public String getConfirmpass() {
        return confirmpass;
    }

    public void setConfirmpass(String confirmpass) {
        this.confirmpass = confirmpass;
    }

    public String goHome() {
        return "home";
    }

    public String checkLogin() throws IOException {
        String page = "login";
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        if (session.getAttribute("customerId") != null) {
//            fc.getExternalContext().redirect("home.xhtml");
            page = "home?faces-redirect=true";
        }
        return page;
    }

    public String login() {
        String page = "home";
        System.out.println("==================================");
        System.out.println("in login function");
        System.out.println("customer: " + customer);
        System.out.println("password: " + customer.getCustomerPassword());

        if (customersFacade.checkCustomer(customer)) {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("customerId", customer.getCustomerId());
            session.setAttribute("customerName", customer.getCustomerName());
            page = "home?faces-redirect=true";

            System.out.println("==================================");
            System.out.println("in if function");
        } else {
            System.out.println("==================================");
            System.out.println("in else function");

            RequestContext.getCurrentInstance().execute("alert('Username or password is incorrect !!!');");
            page = "login";
        }
        System.out.println("==================================");
        System.out.println("page return: " + page);

        return page;
    }
    
     public void logout() throws IOException {
        // this.httpServletRequest.getSession(false).invalidate();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
        session.invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
//        return "home?faces-redirect=true";
    }

    public String register() {
        String page = "login";
        try {
            if (customersFacade.isUsernameExist(customer.getCustomerId())) {
                System.out.println("======================CustomerId is exist");

                RequestContext.getCurrentInstance().execute("alert('Username is existed, please try another username !!!');");
                page = "login";
            } else if (customersFacade.validUsernameLength(customer.getCustomerId())) {
                System.out.println("i======================Length of CustomerId is valid");

                page = "login";
            } else if (customersFacade.validPasswordLength(customer.getCustomerPassword())) {
                System.out.println("i======================Length of CustomerPassword is valid");

                page = "login";
            } else if (!customer.getCustomerPassword().equals(confirmpass)) {
                System.out.println("i======================Comfirm Password is valid");

                RequestContext.getCurrentInstance().execute("alert('Confirm password is not match!!!');");
                page = "login";
            } else {
                System.out.println("i======================Account will be created for Customer");

                RequestContext.getCurrentInstance().execute("alert('Registration completed, please login again !!!');");
                this.customersFacade.create(customer);
                this.customer = new Customers();
                page = "login?faces-redirect=true";
            }
        } catch (Exception e) {
            System.out.println("===============================");
            System.out.println("go to Catch");
        }

        return page;

    }

    public String create() {
        String page = "index";

        // Kiem tra CustomerId
//        String jpql = "SELECT c FROM Customers c WHERE c.customerId = :customerId";
//        Query query = em.createQuery(jpql);
//        
        this.customersFacade.create(customer);
        this.customer = new Customers();

        page = "login_home";
        System.out.println("========================");
        System.out.println("at the create function");
        System.out.println("page: " + page);

        return page;
    }
    
// backend
    public List<Customers> findAll(){
        return this.customersFacade.findAll();
    }

}
