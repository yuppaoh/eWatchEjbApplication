package Controller;

import DAO.BrandsFacadeLocal;
import DAO.ProductsFacadeLocal;
import Entity.Brands;
import Entity.Products;
import Helper.UploadHelper;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.Part;

@Named(value = "ProductController")
@SessionScoped
public class ProductController extends AbstractController implements Serializable
{

    @EJB
    private BrandsFacadeLocal   brandsFacade;
    @EJB(name = "facade")
    private ProductsFacadeLocal facade;
    private Products            product;
    private int                 ProductId;
    private Brands              BrandId;
    private String              ProductName;
    private String              ProductImage;
    private String              ProductType;
    private float               UnitPrice;
    private int                 Quantity;
    private String              Description;
    private Part                file;
    
    public List<Products> findAll()
    {
        return this.facade.findAll();
    }
    
    public String create()
    {
//        UploadHelper uploadHelper   = new UploadHelper();
//        this.ProductImage           = uploadHelper.processUpload( this.file );
//        Products temp = new Products(ProductName, ProductImage, ProductType, UnitPrice, Quantity, Description, BrandId);
//        this.facade.create( temp );
        super.showAlert( "Create succesful" );
        return "admin_Product_List.xhtml?faces-redirect=true";
    }
    
    public String update()
    {
        this.facade.edit( this.product );
        super.showAlert( "Update succesful" );
        return "admin_Product_List.xhtml?faces-redirect=true";
    }
    
    public void remove( Products currentProduct )
    {
        this.facade.remove( currentProduct );
        super.reloadPage("http://localhost:8080/ShopOnline_Backend-war/faces/admin_Product_List.xhtml");
        super.showAlert( "Remove succesful" );
    }
    
    public String redirect( Products product )
    {
        this.product = product;
        return "admin_Product_Update?faces-redirect=true";
    }
    
    public String convertBrandIdToBrandName( Brands BrandId )
    {
        String temp = "";
        
        for( Brands current : brandsFacade.findAll() )
        {
            if( Objects.equals(current.getBrandId(), BrandId.getBrandId()) )
            {
                temp = current.getBrandName();
                break;
            }
        }
        
        return temp;
    }
    
    public ProductController() {}
    public Products getProduct() { return product; }
    public void setProduct(Products product) { this.product = product; }
    public int getProductId() { return ProductId; }
    public void setProductId(int ProductId) { this.ProductId = ProductId; }
    public ProductsFacadeLocal getFacade() {  return facade;  }
    public void setFacade(ProductsFacadeLocal facade) {  this.facade = facade;  }
    public BrandsFacadeLocal getBrandsFacade() { return brandsFacade; }
    public void setBrandsFacade(BrandsFacadeLocal brandsFacade) { this.brandsFacade = brandsFacade; }
    public Brands getBrandId() { return BrandId; }
    public void setBrandId(Brands BrandId) { this.BrandId = BrandId; }
    public String getProductName() { return ProductName; }
    public void setProductName(String ProductName) { this.ProductName = ProductName; }
    public String getProductImage() { return ProductImage; }
    public void setProductImage(String ProductImage) { this.ProductImage = ProductImage; }
    public String getProductType() { return ProductType; }
    public void setProductType(String ProductType) { this.ProductType = ProductType; }
    public float getUnitPrice() {  return UnitPrice; }
    public void setUnitPrice(float UnitPrice) {  this.UnitPrice = UnitPrice; }
    public int getQuantity() {  return Quantity; }
    public void setQuantity(int Quantity) { this.Quantity = Quantity; }
    public String getDescription() {  return Description; }
    public void setDescription(String Description) {  this.Description = Description; }
    public Part getFile() { return file; }
    public void setFile(Part file) { this.file = file; }

  
}
