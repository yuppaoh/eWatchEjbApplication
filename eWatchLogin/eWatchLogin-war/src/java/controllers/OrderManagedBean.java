/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.CustomersFacadeLocal;
import beans.OrdersFacadeLocal;
import entities.Customers;
import entities.Orders;
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
@Named(value = "orderManagedBean")
@SessionScoped
public class OrderManagedBean implements Serializable {
// customerId

    @EJB
    private CustomersFacadeLocal customersFacade;
    private Customers customer;

    @EJB
    private OrdersFacadeLocal ordersFacade;
    private Orders order = new Orders();

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public OrderManagedBean() {
// customerId
        customer = new Customers();
    }

    public List<Orders> findAll() {
        return this.ordersFacade.findAll();
    }

    public List<Orders> findByCustomerId() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        Customers cid = this.customersFacade.find(session.getAttribute("customerId"));
        return this.ordersFacade.findByCustomer(cid);
    }

    public String showDetail(Orders order) {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("orderSession", order);
        return "orderdetail-list";
    }

    public void showDetailLocal(Orders order) {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("orderSession", order);
    }

    public String create() {
        this.order.setCustomerId(this.customersFacade.find(this.customer.getCustomerId()));
        this.ordersFacade.create(order);
        this.order = new Orders();
        System.out.println("in create() function");
        return "b_order";
    }

    public String edit(Orders o) {
        this.order = o;
        return "b_order_edit";
    }

    public String edit() {
        this.ordersFacade.edit(this.order);
        return "b_order";
    }

    public void delete(Orders order) {
        this.ordersFacade.remove(order);
    }

// customerId
    public CustomersFacadeLocal getCustomersFacade() {
        return customersFacade;
    }

    public void setCustomersFacade(CustomersFacadeLocal customersFacade) {
        this.customersFacade = customersFacade;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public Orders getOrder(Integer id) {
        return this.ordersFacade.find(id);
    }

}
