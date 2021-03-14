/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.ProductsFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import entities.Products;
import java.util.List;

/**
 *
 * @author yup
 */
@Named(value = "productManagedBean")
@SessionScoped
public class ProductManagedBean implements Serializable {

    @EJB
    private ProductsFacadeLocal productsFacade;

    private Products product = new Products();
    
    public ProductManagedBean() {
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }
    
    public List<Products> findAll(){
        return this.productsFacade.findAll();
    }
    
    public String productDetail(Products p){
        this.product = p;
        return "product-detail";
    }
    
    public String findById(int id){
        this.product =  this.productsFacade.find(id);
        return "product-detail";
    }
    
}
