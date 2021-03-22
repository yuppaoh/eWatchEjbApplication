/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.BrandsFacadeLocal;
import beans.CartsFacadeLocal;
import beans.CustomersFacadeLocal;
import beans.OrdersFacadeLocal;
import beans.ProductsFacadeLocal;
import entities.Brands;
import entities.Carts;
import entities.Customers;
import entities.Orders;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import entities.Products;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author yup
 */
@Named(value = "productManagedBean")
@RequestScoped
public class ProductManagedBean {
// backend add new product  
    @EJB
    private BrandsFacadeLocal brandsFacade;

// order product
    @EJB
    private CustomersFacadeLocal customersFacade;

    @EJB
    private OrdersFacadeLocal ordersFacade;
    private Orders order = new Orders();

    @EJB
    private CartsFacadeLocal cartsFacade;
    private Carts cart = new Carts();

    @EJB
    private ProductsFacadeLocal productsFacade;
    private Products product = new Products();

// backend add new product    
    private Brands brand;

// order product
    private Customers customer;

    public ProductManagedBean() {
// backend add new product        
        brand = new Brands();
// order product        
        customer = new Customers();
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public List<Products> findAll() {
        return this.productsFacade.findAll();
    }

    public List<Products> findByType(String query) {
        return this.productsFacade.findByType(query);
    }

    public String productDetail(Products p) {
        // set cart
        this.cart = new Carts();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        cart.setCustomerId((String) session.getAttribute("customerId"));
        cart.setProductId(p.getProductId());
        cart.setQuantity(1);
        System.out.println("Cart: " + cart);

        // set order
        try {
            Customers cust = new Customers();
            cust = this.customersFacade.find(session.getAttribute("customerId"));

            System.out.println("==========================================");
            System.out.println("cust on productDetail: " + cust);
            this.order.setCustomerId(this.customersFacade.find(cust.getCustomerId()));
            System.out.println("order.setCustomerId is ok =======================");
            System.out.println("this.order.getCustomerId(): " + this.order.getCustomerId());
            
            String relativePath = "vendors/img/product/";
            System.out.println("**********************************************");
            System.out.println("relativePath: " +relativePath);
//    String absolutePath = FacesContext.getCurrentInstance.getExternalContext().getRealPath(relativePath);
//    File file = new File(relativePath);

        } catch (Exception e) {
            System.out.println("==========================================");
            System.out.println("Exception in set order: " + e.getMessage());
        }

//        order.setOrderStatus("Watting");
        this.product = p;
        return "product-detail";
    }

    public String orderProduct() {
        System.out.println("Begin orderProduct()");
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);

            Customers cust = new Customers();
            cust = this.customersFacade.find(session.getAttribute("customerId"));

            System.out.println("==========================================");
            System.out.println("cust on orderProduct " + cust);

            this.order.setCustomerId(this.customersFacade.find(cust.getCustomerId()));
            this.ordersFacade.create(order);

            System.out.println("in function orderProduct()");

        } catch (Exception e) {
            System.out.println("Excepption in orderProduct(): " + e.getMessage());
        }
        this.order = new Orders();
        return "product-detail";
    }

    public String addCart() {
        try {
            this.cartsFacade.create(cart);
            this.cart = new Carts();
            int count = cartCount();
        } catch (Exception e) {
            RequestContext.getCurrentInstance().execute("alert('This product is already added to cart!');");
        }
        return "product-detail";
    }

    public int cartCount() {
        return this.cartsFacade.count();
    }

    public String findById(int id) {
        this.product = this.productsFacade.find(id);
        return "product-detail";
    }

    //Cart
    public Carts getCart() {
        return cart;
    }

    public void setCart(Carts cart) {
        this.cart = cart;
    }

// ==========================================    
// backend add new product
    public Brands getBrand() {
        return brand;
    }

    public void setBrand(Brands brand) {
        this.brand = brand;
    }

    public BrandsFacadeLocal getBrandsFacade() {
        return brandsFacade;
    }

    public void setBrandsFacade(BrandsFacadeLocal brandsFacade) {
        this.brandsFacade = brandsFacade;
    }

    public Products getProduct(Integer id) {
        return this.productsFacade.find(id);
    }

    public String create() {
        this.product.setBrandId(this.brandsFacade.find(this.brand.getBrandId()));
        this.productsFacade.create(product);
        return "b-home";
    }
// ==========================================    
// order product

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public CustomersFacadeLocal getCustomersFacade() {
        return customersFacade;
    }

    public void setCustomersFacade(CustomersFacadeLocal customersFacade) {
        this.customersFacade = customersFacade;
    }

//    public void orderProduct(){
//        this.order.setCustomerId(this.customersFacade.find(this.customer.getCustomerId()));
//        this.ordersFacade.create(order);
//        this.order = new Orders();
//        return "product-detail";
//    }
}
