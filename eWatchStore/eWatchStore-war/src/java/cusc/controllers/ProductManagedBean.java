/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cusc.controllers;

import cusc.beans.BrandsFacadeLocal;
import cusc.beans.ProductsFacadeLocal;
import cusc.entities.Products;
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
    private Products product = new Products();
    public Products getProduct() {
        return product;
    }
    public void setProduct(Products product) {
        this.product = product;
    }
    public ProductManagedBean() {
    }
    public List<Products> findAll(){
        return this.productsFacade.findAll();
    }
    
    public String findById(int id){
        this.product =  this.productsFacade.find(id);
        return "product-detail";
    }
    
    public Products p(int id){
        return this.productsFacade.find(id);
        
    }
    public String productDetail(Products p){
        this.product = p;
        return "product-detail";
    }
    
    
}
