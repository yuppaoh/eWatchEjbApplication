package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cusc.beans.UsersFacadeLocal;
import cusc.entities.Users;
import javax.faces.context.FacesContext;

/**
 *
 * @author ASUS
 */
public class LoginController extends HttpServlet {
    
    @EJB
    private UsersFacadeLocal usersFacade;
    
    private Users user = new Users();

//    public UserManagedBean() {
//    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
    public String login() {
        if (usersFacade.login(user)) {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("account", user.getUserName());
            return "product";
        }
        return "index";
    }

    public void logout() throws IOException {
        // this.httpServletRequest.getSession(false).invalidate();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
        session.invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
    }

//    @EJB
//    private UserSessionBeanLocal userSessionBean;
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
//        try {
//            String UserName = request.getParameter("UserName");
//            String UserPassword = request.getParameter("UserPassWord");
//
//            if (userSessionBean.checkLogin(UserName, UserPassword)) {
//                HttpSession session = request.getSession();
//                session.setAttribute("user", UserName);
//                out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
//                out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
//                out.println("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
//                out.println("<script language=JavaScript>");
//                out.println("$(document).ready(function(){");
//                out.println("swal('Welcome','Login Successfully!','success');");
//                out.println("});");
//                out.println("</script>");
//                RequestDispatcher rd = request.getRequestDispatcher("faces/index.xhtml");
//                rd.include(request, response);
////               request.getRequestDispatcher("faces/index.xhtml").forward(request, response);
//            } else {
//                out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
//                out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
//                out.println("<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>");
//                out.println("<script language=JavaScript>");
//                out.println("$(document).ready(function(){");
//                out.println("swal('Error','Username or Password incorrect!','error');");
//                out.println("});");
//                out.println("</script>");
//                RequestDispatcher rd = request.getRequestDispatcher("login_register.jsp");
//                rd.include(request, response);
//                
//            }
//        } catch (IOException | ServletException e) {
//            System.out.println(e.getMessage());
//        }
//    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
