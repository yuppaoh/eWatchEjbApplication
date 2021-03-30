/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.OrderdetailsFacadeLocal;
import beans.OrdersFacadeLocal;
import beans.ProductsFacadeLocal;
import entities.Orderdetails;
import entities.Orders;
import entities.Products;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author yup
 */
@Named(value = "orderdetailController")
@RequestScoped
public class OrderdetailController {

    @EJB
    private ProductsFacadeLocal productsFacade;
    
    @EJB
    private OrdersFacadeLocal ordersFacade;
    
    @EJB
    private OrderdetailsFacadeLocal orderdetailsFacade;
    private Orderdetails orderdetail = new Orderdetails();
    private Orders order = new Orders();
    private Products product = new Products();

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    
    public Orderdetails getOrderdetail() {
        return orderdetail;
    }

    public void setOrderdetail(Orderdetails orderdetail) {
        this.orderdetail = orderdetail;
    }

    public OrderdetailController() {
//        order = new Orders();
    }
    
    public List<Orderdetails> findAll(){
        return this.orderdetailsFacade.findAll();
    }
    
    public List<Orderdetails> findByOrderId(Orders order){
        return this.orderdetailsFacade.findByOrderId(order);
    }    
    public void delete(Orderdetails orderdetail){
        this.orderdetailsFacade.remove(orderdetail);
    }
    
}
