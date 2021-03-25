package Controller;

import DAO.EmployeesFacadeLocal;
import Entity.Employees;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named(value = "EmployeeController")
@SessionScoped
public class EmployeeController extends AbstractController implements Serializable
{

    @EJB
    private EmployeesFacadeLocal facade;
    private Employees       employee;
    private String          userName;
    private String          userPassword;
    private String          userRole;
    
    public List<Employees> findAll()
    {
        return this.facade.findAll();
    }
    
    public String create()
    {
        Employees temp = new Employees( userName , userPassword , userRole );
        this.facade.create( temp );
        super.showAlert( "Create succesful" );
        return "admin_Employee_List.xhtml?faces-redirect=true";
    }
    
    public String update()
    {
        this.facade.edit( this.employee );
        super.showAlert( "Update succesful" );
        return "admin_Employee_List.xhtml?faces-redirect=true";
    }
    
    public void remove( Employees currentEmployee )
    {
        this.facade.remove( currentEmployee );
        super.reloadPage("http://localhost:8080/ShopOnline_Backend-war/faces/admin_Employee_List.xhtml");
        super.showAlert( "Remove succesful" );
    }
    
    public String redirect( Employees employee )
    {
        this.employee = employee;
        return "admin_Employee_Update?faces-redirect=true";
    }
    
    public EmployeeController() {}
    public Employees getEmployee() { return employee; }
    public void setEmployee(Employees employee) { this.employee = employee; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getUserPassword() { return userPassword; }
    public void setUserPassword(String userPassword) { this.userPassword = userPassword; }
    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }
 
}
