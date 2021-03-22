/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.BrandsFacadeLocal;
import entities.Brands;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author yup
 */
@Named(value = "brandManagedBean")
@SessionScoped
public class BrandManagedBean implements Serializable {

    @EJB
    private BrandsFacadeLocal brandsFacade;
    
    private Brands brand = new Brands();

    public Brands getBrand() {
        return brand;
    }

    public void setBrand(Brands brand) {
        this.brand = brand;
    }
    
    public BrandManagedBean() {
    }
    
    public List<Brands> findAll(){
        return this.brandsFacade.findAll();
    }
}
