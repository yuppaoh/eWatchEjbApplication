/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.BrandsFacadeLocal;
import beans.CartsFacadeLocal;
import beans.CustomersFacadeLocal;
import beans.OrderdetailsFacadeLocal;
import beans.OrdersFacadeLocal;
import beans.ProductsFacadeLocal;
import entities.Brands;
import entities.Carts;
import entities.Customers;
import entities.Orderdetails;
import entities.Orders;
import javax.inject.Named;
import javax.ejb.EJB;
import entities.Products;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author yup
 */
@Named(value = "productManagedBean")
//@RequestScoped
@SessionScoped
public class ProductManagedBean implements Serializable {
// Orderdetail

    @EJB
    private OrderdetailsFacadeLocal orderdetailsFacade;
    private Orderdetails orderdetail = new Orderdetails();

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
    private Customers customer = new Customers();

    public ProductManagedBean() {
// backend add new product        
        brand = new Brands();
// order product        
        customer = new Customers();
// add to orderdetail        
//        order = new Orders();
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
        System.out.println("query: " + query);
        return this.productsFacade.findByType(query);
    }

    public String productDetail(Products p) {
        // set cart
        //this.cart = new Carts();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("session_product", p);
        System.out.println("=================================");
        System.out.println("Products: " + p);
        //cart.setCustomerId((String) session.getAttribute("customerId"));
        //System.out.println("cart.setCustomerId: " + cart.getCustomerId());
        //cart.setProductId(p.getProductId());
        //System.out.println("cart.setProductId: " + cart.getProductId());
        //cart.setQuantity(1);
        //System.out.println("Cart: " + cart);

        // set order
        try {
            Customers cust = new Customers();
            cust = this.customersFacade.find(session.getAttribute("customerId"));
            this.order.setCustomerId(this.customersFacade.find(cust.getCustomerId()));
            this.orderdetail.setProductId(p);

        } catch (Exception e) {
            System.out.println("==========================================");
            System.out.println("Exception in set order: " + e.getMessage());
        }

//        order.setOrderStatus("Watting");
        this.product = p;
        System.out.println("this.producs: " + p);

        return "product-detail?faces-redirect=true";

    }

    public String orderProduct(Products p) {
        /*        
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);

            Customers cust = new Customers();
            cust = this.customersFacade.find(session.getAttribute("customerId"));
            this.order.setCustomerId(this.customersFacade.find(cust.getCustomerId()));
            session.setAttribute("order_session", order);
            this.ordersFacade.create(order);
            
// set orderdetail
            System.out.println("444444444444444444444444444444444444444444444444");
            this.orderdetail.setOrderId(this.ordersFacade.find(this.order.getOrderId()));
            System.out.println("5555555555555555555555555555555555555555555555");
            Products pp = new Products();
            pp = (Products) session.getAttribute("session_product");
            System.out.println("=============================");
            System.out.println("pp: " +pp);

            Float pString = pp.getUnitPrice();
            System.out.println("=================================");
            System.out.println("pString: " + pString);
            this.orderdetail.setProductId((Products) session.getAttribute("session_product"));
            this.orderdetail.setProductId(pp);
            this.orderdetail.setQuantity(1);
            this.orderdetail.setUnitPrice(pp.getUnitPrice());

            //session.setAttribute("orderdetail_session", orderdetail);
            this.orderdetailsFacade.create(orderdetail);

        } catch (Exception e) {
            System.out.println("Excepption in orderProduct(): " + e.getMessage());
        }

//        this.order = new Orders();  //dua sang function add order and orderdetail to table in order page xhtml
//        return "product-order";      // redirect to product order page
         */
        return "home";      // redirect to product order page
    }

    public String orderProductCreate() {

        try {
            float m = muti();
            System.out.println("====================================");
            System.out.println("total: " + m);
            System.out.println("quantity: " + this.orderdetail.getQuantity());

            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            Customers cust = new Customers();
            Products pp = new Products();

//        try {
            if (session.getAttribute("session_product") != null) {
                cust = this.customersFacade.find(session.getAttribute("customerId"));
                this.order.setCustomerId(this.customersFacade.find(cust.getCustomerId()));
                this.order.setOrderDate(date);
                this.order.setOrderStatus("Pending...");
                this.ordersFacade.create(order);

                pp = (Products) session.getAttribute("session_product");
                this.orderdetail.setOrderId(this.ordersFacade.find(this.order.getOrderId()));
                this.orderdetail.setProductId(pp);
//                this.orderdetail.setQuantity(1);
                this.orderdetail.setUnitPrice(pp.getUnitPrice());
                this.orderdetailsFacade.create(orderdetail);

                RequestContext.getCurrentInstance().execute("alert('You have ordered product successfully! please waitting to receive your product!');");
            } else {
                RequestContext.getCurrentInstance().execute("alert('You should choose product before!');");
            }

            System.out.println("========================================");
            System.out.println("add data to order and orderdetail ok");

//        } catch (Exception e) {
//            System.out.println("========================================");
//           System.out.println("Exception in orderProductCreate(): " + e.getMessage());
//      }
        } catch (Exception e) {
            RequestContext.getCurrentInstance().execute("alert('Please input quantiy !!!');");
        }
        return "product-order";
    }

    public String addCart() {
        try {
            //==========================================
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            Products p = (Products) session.getAttribute("session_product");
            System.out.println("session_product: " + p);
            cart.setCustomerId((String) session.getAttribute("customerId"));
            System.out.println("cart.setCustomerId: " + cart.getCustomerId());

            cart.setProductId(p.getProductId());
            System.out.println("cart.setProductId: " + cart.getProductId());
            cart.setQuantity(1);
            System.out.println("Cart: " + cart);
            //==========================================

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

    public Products findProduct(int id) {
        this.product = this.productsFacade.find(id);
        return this.product;
    }

    public String listToProductDetail(int id) {
        System.out.println("id: " + id);
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
// ==========================================    
// order product
    private int value1;
    private int value2;

    public int getValue1() {
        return value1;
    }

    public void setValue1(int value1) {
        this.value1 = value1;
    }

    public int getValue2() {
        return value2;
    }

    public void setValue2(int value2) {
        this.value2 = value2;
    }

    public Orderdetails getOrderdetail() {
        return orderdetail;
    }

    public void setOrderdetail(Orderdetails orderdetail) {
        this.orderdetail = orderdetail;
    }

    public OrdersFacadeLocal getOrdersFacade() {
        return ordersFacade;
    }

    public void setOrdersFacade(OrdersFacadeLocal ordersFacade) {
        this.ordersFacade = ordersFacade;
    }

    public Orderdetails getOrderdetail(Integer id) {
        return this.orderdetailsFacade.find(id);
    }

    private int quantity = 0;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = this.product.getQuantity();
    }
    private float total = 0;

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float muti() {
        this.total = this.product.getUnitPrice() * this.orderdetail.getQuantity();
        return this.total;
    }

}
