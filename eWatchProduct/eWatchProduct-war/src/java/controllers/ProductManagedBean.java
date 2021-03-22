/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.BrandsFacadeLocal;
import beans.ProductsFacadeLocal;
import entities.Brands;
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

// input
    @EJB
    private BrandsFacadeLocal brandsFacade;
    
    @EJB
    private ProductsFacadeLocal productsFacade;
    private Products product = new Products();
// input
//    private static final long serialVersionUID = 1L;    
//input
//    private Integer productId;
//    private String productName;
//    private String productImage;
//    private String productType;
//    private Float unitPrice;
//    private Integer quantity;
//    private String description;
    private Brands brand;

//input
//    public Integer getProductId() {
//        return productId;
//    }
//input
//    public void setProductId(Integer productId) {
//        this.productId = productId;
//    }
//input
//    public String getProductName() {
//        return productName;
//    }
//input
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }
//input
//    public String getProductImage() {
//        return productImage;
//    }
//input
//    public void setProductImage(String productImage) {
//        this.productImage = productImage;
//    }
//input
//    public String getProductType() {
//        return productType;
//    }
//input
//    public void setProductType(String productType) {
//        this.productType = productType;
//    }
//input
//    public Float getUnitPrice() {
//        return unitPrice;
//    }
//input
//    public void setUnitPrice(Float unitPrice) {
//        this.unitPrice = unitPrice;
//    }
//input
//    public Integer getQuantity() {
//        return quantity;
//    }
//input
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//input
//    public String getDescription() {
//        return description;
//    }
//input
//    public void setDescription(String description) {
//        this.description = description;
//    }
//input
    public Brands getBrand() {
        return brand;
    }
//input
    public void setBrand(Brands brand) {
        this.brand = brand;
    }
    
    
    public ProductManagedBean() {
//input        
        brand = new Brands();
    }

//input
    public BrandsFacadeLocal getBrandsFacade() {
        return brandsFacade;
    }

//input
    public void setBrandsFacade(BrandsFacadeLocal brandsFacade) {
        this.brandsFacade = brandsFacade;
    }
    

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }
// input    
    public Products getProduct(Integer id){
        return this.productsFacade.find(id);
    }
    
    public List<Products> findAll(){
        return this.productsFacade.findAll();
    }
    
//Thêm mới và chuyển trang
    public String create(){
//        Products p = new Products();
//        p.setProductName(productName);
//        p.setProductImage(productImage);
//        p.setProductType(productType);
//        p.setUnitPrice(unitPrice);
//        p.setQuantity(quantity);
//        p.setDescription(description);
//        p.setBrandId(this.brandsFacade.find(this.brand.getBrandId()));
        this.product.setBrandId(this.brandsFacade.find(this.brand.getBrandId()));
        this.productsFacade.create(product);
        this.product = new Products();
        return "b-home";
        
    }
    
    public String findById(int id){
        this.product =  this.productsFacade.find(id);
        return "product-detail";
    }
    
    public String productDetail(Products product){
        this.product = product;
        return "product-detail";
    }
    
    
}
