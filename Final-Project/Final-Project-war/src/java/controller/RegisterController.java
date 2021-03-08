///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controller;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import javax.ejb.EJB;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import session.UserSessionBeanLocal;
//
//public class RegisterController extends HttpServlet 
//{
//    
//    // Khởi tạo object userSessionBean để gửi data lên server (userSessionBean gói ejb) để xử lý dữ liệu mà người dùng request
//    @EJB
//    private UserSessionBeanLocal userSessionBean;
//
//    @Override
//    protected void doPost( HttpServletRequest request , HttpServletResponse response )
//            throws ServletException, IOException 
//    {
//        // Lấy dữ liệu được nhập bởi người dùng trong ba filed "username", "password" & "comfirm password" 
//        // từ form login (login_register.jsp)
//        String usernameRegis = request.getParameter( "usernameRegis" );
//        String passwordRegis = request.getParameter( "passwordRegis" );
//        String comfirmRegis  = request.getParameter( "comfirmPasswordRegis" );
//        
//        // Kiểm tra tính hợp lệ của form đăng ký
//        String validateMessage = validateRegisterData( usernameRegis , passwordRegis , comfirmRegis );
//        
//        if( validateMessage.equals( "Registration information is valid" ) )
//        {
//            if( userSessionBean.insert(usernameRegis, passwordRegis) )
//            {
//                // Sau đó chuyển sang trang đăng nhập của website
//                showAlertRegister( "Registration Complete, Please login again!" , "login_register.jsp" , request , response );
////                RequestDispatcher rd = request.getRequestDispatcher( "faces/index.xhtml" );
////                rd.include( request , response);
//            }
//            else
//            {
//                showAlert( "Username is exist, Please try with another username" , "login_register.jsp" , request , response );
//            }
//        }
//        else showAlert( validateMessage , "login_register.jsp" , request , response);
//    }
//
//    private void showAlert( String message , String redirectPage , HttpServletRequest request , HttpServletResponse response ) 
//            throws ServletException, IOException
//    {
//        PrintWriter out = response.getWriter();
//        out.println( "<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>" );
//        out.println( "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>" );
//        out.println( "<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>" );
//        out.println( "<script language=JavaScript>" );
//        out.println( "$(document).ready(function(){" );
//        out.println( "swal('Error','" + message + "','error');" );
//        out.println( "});" );
//        out.println( "</script>" );
//        RequestDispatcher rd = request.getRequestDispatcher( redirectPage );
//        rd.include( request , response );
//    }
//    
//      private void showAlertRegister( String message , String redirectPage , HttpServletRequest request , HttpServletResponse response ) 
//            throws ServletException, IOException
//    {
//        PrintWriter out = response.getWriter();
//        out.println( "<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>" );
//        out.println( "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>" );
//        out.println( "<script src='https://unpkg.com/sweetalert/dist/sweetalert.min.js'></script>" );
//        out.println( "<script language=JavaScript>" );
//        out.println( "$(document).ready(function(){" );
//        out.println( "swal('Success','" + message + "','success');" );
//        out.println( "});" );
//        out.println( "</script>" );
//        RequestDispatcher rd = request.getRequestDispatcher( redirectPage );
//        rd.include( request , response );
//    }
//    
//    private String validateRegisterData( String username , String password , String comfirmPassword )    
//    {
//        if( password.equals( comfirmPassword ) )
//        {
//            if( username.matches( "[a-z0-9_-]{6,12}$" ) )
//            {
//                if( password.matches( "[a-z0-9_-]{6,12}$" ) )
//                {
//                    return "Registration information is valid";
//                }
//                else
//                {
//                    return "Password must have 6-12 characters, no spaces and no accents";
//                }
//            }
//            else
//            {
//                return "Username must have 6-12 characters, no spaces and no accents";
//            }
//        }
//        else
//        {
//            return "Comfirm password is not match!";
//        }
//    }
//}
