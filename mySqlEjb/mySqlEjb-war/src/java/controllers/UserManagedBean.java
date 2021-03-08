/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import cusc.beans.UsersFacadeLocal;
import cusc.entities.Users;
import java.io.IOException;
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
@Named(value = "userManagedBean")
@SessionScoped
public class UserManagedBean implements Serializable {

    @EJB
    private UsersFacadeLocal usersFacade;
    
    private Users user = new Users();

    public UserManagedBean() {
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
    public List<Users> findAll(){
        return this.usersFacade.findAll();
    }
    
    public String login() {
        String page = "index";
        if (usersFacade.login(user)) {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("account", user.getUserName());
            System.out.println("login true");
            page = "product";
        }else{
            page = "index";
        }
        System.out.println("============================================");
        System.out.println("page: " + page);
        return page;
    }

    public void logout() throws IOException {
        // this.httpServletRequest.getSession(false).invalidate();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
        session.invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }
    
}
