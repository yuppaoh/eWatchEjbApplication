/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.ProductsFacadeLocal;
import entities.Products;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author yup
 */
@Named(value = "productManagedBean")
@SessionScoped
public class ProductManagedBean implements Serializable {

    @EJB
    private ProductsFacadeLocal productsFacade;

    private Products p = new Products();
    
    public ProductManagedBean() {
    }

    public Products getP() {
        return p;
    }

    public void setP(Products p) {
        this.p = p;
    }
    
    public List<Products> findAll(){
        return this.productsFacade.findAll();
    }
    
    public String findById(int id){
        this.p =  this.productsFacade.find(id);
        return "product-detail";
    }
    
    public String productDetail(Products p){
        this.p = p;
        return "product-detail";
    }
    
    
}
