package Controller;

import DAO.EmployeesFacadeLocal;
import Entity.Employees;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named(value = "LoginController")
@SessionScoped
public class LoginController extends AbstractController implements Serializable
{

    @EJB
    private EmployeesFacadeLocal employeesFacade;
    private String UserName;
    private String UserPassword;
    
    public LoginController() {}
    public String getUserName() { return UserName; }
    public void setUserName(String UserName) { this.UserName = UserName; }
    public String getUserPassword() { return UserPassword; }
    public void setUserPassword(String UserPassword) { this.UserPassword = UserPassword; }
    
    public String login()
    {
        if( checkLogin(UserName , UserPassword) )
        {   
            return "admin_Customer_List?faces-redirect=true";
        }
        else
        {
            RequestContext.getCurrentInstance().execute( "alert('Username or password is not correct');" );
            return "admin_Login.xhtml";
        }
    }
    
    public String logout()
    {
        super.destroySession( "CurrentEmployee" );
        return "admin_Login?faces-redirect=true";
    }
    
    public boolean checkLogin( String username , String password )
    {
        boolean accountComfirm = false;
        List< Employees > employeesList = this.employeesFacade.findAll();
        
        for( Employees currentElm : employeesList )
        {
            if( currentElm.getUserName().equals( username ) 
            &&  currentElm.getUserPassword().equals( password ) )
            {
                accountComfirm = true;
                super.createSession( "CurrentEmployee" , currentElm );
                break;
            }
            else
            {
                accountComfirm = false;
            }  
        }
        
        return accountComfirm;
    }
}
