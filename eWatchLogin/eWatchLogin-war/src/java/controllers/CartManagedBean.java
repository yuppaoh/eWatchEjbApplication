/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.CartsFacadeLocal;
import entities.Carts;
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
@Named(value = "cartManagedBean")
@SessionScoped
public class CartManagedBean implements Serializable {

    @EJB
    private CartsFacadeLocal cartsFacade;
    private Carts cart = new Carts();

    public Carts getCart() {
        return cart;
    }
    public void setCart(Carts cart) {
        this.cart = cart;
    }

    public CartManagedBean() {
    }
    
    public List<Carts> findAll(){
        return this.cartsFacade.findAll();
    }
    
    public void create(){
        this.cartsFacade.create(cart);
        this.cart = new Carts();
    }
    
    public List<Carts> findByCustomerId(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        String customerId = (String) session.getAttribute("customerId");
        if(customerId!=null){
            return this.cartsFacade.findByUsername(customerId);
        }else{
            return null;
        }
        
    }
}
