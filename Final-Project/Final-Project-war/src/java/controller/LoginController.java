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
//import javax.servlet.http.HttpSession;
//import session.UserSessionBeanLocal;
//
//public class LoginController extends HttpServlet 
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
//        try 
//        {
//            // Lấy dữ liệu được nhập bởi người dùng trong hai filed "username" & "password" từ form login (login_register.jsp)
//            String UserName     = request.getParameter( "UserName" );
//            String UserPassword = request.getParameter( "UserPassWord" );
//
//            // Gửi data về server để kiểm tra trong CSDL thông qua hàm checkLogin thuộc object userSessionBean được khởi tạo ở trên
//            // Hàm nhận vào hai parameters username & password (data người dùng nhập) để kiểm tra sự trùng khớp với data lưu trữ trong CSDL
//            // Hàm sẽ trả về true hoặc false
//            // Nếu data người dùng nhập trùng khớp với data trong CSDL thì hàm trả về true, tức đăng nhập thành công
//            if( userSessionBean.checkLogin( UserName, UserPassword ) ) 
//            {
//                // Sau khi đăng nhập thành công, lưu tên user vào session để hiển thị trong các trang khác
//                HttpSession session = request.getSession();
//                session.setAttribute( "user" , UserName );
//                
//                // Sau đó chuyển sang trang chủ của website
//                RequestDispatcher rd = request.getRequestDispatcher( "faces/index.xhtml" );
//                rd.include( request , response);
//
//            } 
//            // Nếu data người dùng nhập không trùng khớp với data trong CSDL thì hàm trả về false, tức đăng nhập thất bại
//            else 
//            {
//                // Hiển thị thông báo rằng người dùng nhập sai tài khoản hoặc mật khẩu
//                showAlert( "Your username or password is not correct, please try again!" , "login_register.jsp", request, response);
//            }
//        } 
//        catch( IOException | ServletException e ) 
//        {
//            System.out.println( e.getMessage() );
//        }
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
//}
