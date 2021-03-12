/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cusc.controllers;

import cusc.beans.EmployeesFacadeLocal;
import cusc.entities.Employees;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author yup
 */
@Named(value = "employeeManagedBean")
@SessionScoped
public class EmployeeManagedBean implements Serializable {

    @EJB
    private EmployeesFacadeLocal employeesFacade;

    private Employees employee = new Employees();
    public Employees getEmployee() {
        return employee;
    }
    public void setEmployee(Employees employee) {
        this.employee = employee;
    }
    
    private String confirmpass;
    public String getConfirmpass() {
        return confirmpass;
    }
    public void setConfirmpass(String confirmpass) {
        this.confirmpass = confirmpass;
    }
    
    public EmployeeManagedBean() {
    }
    
    public List<Employees> findAll(){
        return this.employeesFacade.findAll();
    }
    
    public String regis() {
        if (employeesFacade.isUsernameExist(employee.getUserName())) {
            RequestContext.getCurrentInstance().execute("alert('Username is existed, please try another username !!!');");
            return "login_register";
        } else if (employeesFacade.validUsernameLength(employee.getUserName())) {
            return "login_register";
        } else if (employeesFacade.validPasswordLength(employee.getUserPassword())) {
            return "login_register";
        } else if (!employee.getUserPassword().equals(confirmpass)) {
            RequestContext.getCurrentInstance().execute("alert('Confirm password is not match!!!');");
            return "login_register";
        } else {
            RequestContext.getCurrentInstance().execute("alert('Registration completed, please login again !!!');");
            this.employeesFacade.create(employee);
            this.employee = new Employees();
            return "login_register";
        }
    }
    
    public String login() {
        String page = "";
        if (this.login(employee)) {
//            FacesContext fc = FacesContext.getCurrentInstance();
//            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
//            session.setAttribute("account", employee.getUserName());
            page = "product";
            System.out.println("------======= if --- page = " + page);
        } else {
            RequestContext.getCurrentInstance().execute("alert('Username or password is incorrect !!!');");
            page = "product_data";
            System.out.println("------======= elese if --- page = " + page);
        }
        System.out.println("------======= out of if --- page = " + page);
        return page;
    }
    
    public boolean login(Employees employee) {
        Employees checkUser = this.employeesFacade.find(employee.getUserName());
        boolean result = checkUser!=null && checkUser.getUserPassword().equals(employee.getUserPassword());
        System.out.println("================================dd");
        System.out.println("result: " + result);
        return result;
//        return checkUser!=null && checkUser.getUserPassword().equals(employee.getUserPassword());
    }
    
    public void logout() throws IOException {
        // this.httpServletRequest.getSession(false).invalidate();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
        session.invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }
    
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }
    
    public boolean isUsernameExist(String username) {
        // Biến String lưu mã lệnh SQL dùng để truy vấn CSDL
        // Truy vấn một row trong bảng "Users" có username trùng khớp với parameter username
        String jpql = "Select e From Employees e WHERE e.userName=:userName";
        
        // Khởi tạo object query để chuẩn bị truy vấn
        Query query = this.em.createQuery(jpql);
        // Gán parameters username vào câu lệnh truy vấn
        query.setParameter("userName", username);
        
        // Tiến hành truy vấn dữ liệu
        try 
        {
            // Nếu truy vấn thành công, tức là parameter usernamecó tồn tại trong CSDL
            query.getSingleResult();
            // Trả về true cho hàm gọi để tiếp tục xử lý
            return true;
        } 
        catch (Exception e) 
        {
            // Nếu truy vấn thất bại, tức là parameter username không tồn tại trong CSDL
            // Trả về false cho hàm gọi để tiếp tục xử lý
            return false;
        }
    }
    
    public boolean validUsernameLength(String username) {
       if (!username.matches("[a-z0-9_-]{6,12}$")) {
            RequestContext.getCurrentInstance().execute("alert('Username must be between 6 and 12 characters, no spaces, no accents and no special characters !!!');");
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean validPasswordLength(String password) {
        if (!password.matches("[a-z0-9_-]{6,12}$")) {
            RequestContext.getCurrentInstance().execute("alert('The password must be at least 8 characters long, but no more than 32, no spaces, no accents and no special characters !!!');");
            return true;
        }
        else
        {
            return false;
        }
    }
}
