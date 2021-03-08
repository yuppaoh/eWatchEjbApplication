//package session;
//
//import entity.Users;
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//
//@Stateless
//public class UserSessionBean implements UserSessionBeanLocal 
//{
//    
//    
//    @PersistenceContext protected EntityManager entityManager;
//    @PersistenceContext(unitName = "Final-Project-ejbPU")
//
//    // Hàm kiểm tra username & password người dùng nhập vào khi đăng nhập có khớp với data được lưu trong CSDL không
//    // Hàm nhận vào hai parameters là username & password để kiểm tra sự trùng khớp với data lưu trữ trong CSDL
//    // Nếu trùng khớp thì trả về true, ngược lại trả về false
//    @Override
//    public boolean checkLogin( String username , String password ) 
//    {
//        // Biến String lưu mã lệnh SQL dùng để truy vấn CSDL
//        // Truy vấn một row trong bảng "Users" có username & password trùng khớp với data mà người dùng nhập
//        String jpql = "Select u From Users u WHERE u.userName=:userName and u.userPassword=:userPassword";
//        
//        // Khởi tạo object query để chuẩn bị truy vấn
//        Query query = entityManager.createQuery(jpql);
//        // Gán parameters username & password (data người dùng nhập) vào câu lệnh truy vấn
//        query.setParameter("userName", username);
//        query.setParameter("userPassword", password);
//      
//        // Tiến hành truy vấn dữ liệu
//        try 
//        {
//            // Nếu truy vấn thành công, tức là username & password mà người dùng nhập vào có tồn tại trong CSDL
//            query.getSingleResult();
//            // Trả về true cho LoginController tiếp tục xử lý
//            return true;
//        } 
//        catch (Exception e) 
//        {
//            // Nếu truy vấn thất bại, tức là username & password mà người dùng nhập vào không tồn tại trong CSDL
//            // Trả về false cho LoginController tiếp tục xử lý
//            return false;
//        }
//    }
//    
//    @Override
//    public boolean insert( String username , String password )  
//    {
//        if( !isUsernameExist( username ) )
//        {
//            Users newUser = new Users();
//            newUser.setUserName( username );
//            newUser.setUserPassword( password );
//            entityManager.persist( newUser );
//            return true;
//        } else return false;
//    }
//    
//    
//    @Override
//    public boolean isUsernameExist( String username )
//    {
//        // Biến String lưu mã lệnh SQL dùng để truy vấn CSDL
//        // Truy vấn một row trong bảng "Users" có username trùng khớp với parameter username
//        String jpql = "Select u From Users u WHERE u.userName=:userName";
//        
//        // Khởi tạo object query để chuẩn bị truy vấn
//        Query query = entityManager.createQuery(jpql);
//        // Gán parameters username vào câu lệnh truy vấn
//        query.setParameter("userName", username);
//        
//        // Tiến hành truy vấn dữ liệu
//        try 
//        {
//            // Nếu truy vấn thành công, tức là parameter usernamecó tồn tại trong CSDL
//            query.getSingleResult();
//            // Trả về true cho hàm gọi để tiếp tục xử lý
//            return true;
//        } 
//        catch (Exception e) 
//        {
//            // Nếu truy vấn thất bại, tức là parameter username không tồn tại trong CSDL
//            // Trả về false cho hàm gọi để tiếp tục xử lý
//            return false;
//        }
//    }
//
//    public void persist(Object object) {
//        entityManager.persist(object);
//    }
//
//}
