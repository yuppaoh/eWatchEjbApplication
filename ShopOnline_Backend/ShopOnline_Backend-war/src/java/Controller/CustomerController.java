package Controller;

import DAO.CustomersFacadeLocal;
import Entity.Customers;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "CustomerController")
@SessionScoped
public class CustomerController extends AbstractController implements Serializable
{

    @EJB
    private CustomersFacadeLocal facade;
    private Customers            customer;
    private String               username;
    private String               password;
    private String               fullname;
    private String               phone;
    private String               address;
    
    public String getCustomerStatusString( int status ) 
    { 
        if( status == 0 )
        {
            return "Disable";
        }
        else
        {
            return "Active";
        }
    }
    
    public List< Customers > findAll()
    {
        return facade.findAll();
    }
    
    public String create()
    {
        Customers temp = new Customers(username, password, fullname, address, phone, "1");
        this.facade.create( temp );
        super.showAlert( "Create succesful" );
        return "admin_Customer_List.xhtml?faces-redirect=true";
    }
    
    public String update()
    {
        this.facade.edit( this.customer );
        super.showAlert( "Update succesful" );
        return "admin_Customer_List.xhtml?faces-redirect=true";
    }
    
    public void remove( Customers currentCustomer )
    {
        this.facade.remove( currentCustomer );
        super.reloadPage("http://localhost:8080/ShopOnline_Backend-war/faces/admin_Customer_List.xhtml");
        super.showAlert( "Remove succesful" );
    }
    
    public String redirect( Customers customer )
    {
        this.customer = customer;
        return "admin_Customer_Update?faces-redirect=true";
    }

    public CustomerController() {}
    public Customers getCustomer() { return customer; }
    public void setCustomer(Customers customer) { this.customer = customer; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
}
