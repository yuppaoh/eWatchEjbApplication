/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cusc.beans;

import cusc.entities.Employees;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
//import org.primefaces.context.RequestContext;

/**
 *
 * @author yup
 */
@Stateless
public class EmployeesFacade extends AbstractFacade<Employees> implements EmployeesFacadeLocal {

    @PersistenceContext(unitName = "eWatchStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeesFacade() {
        super(Employees.class);
    }

    @Override
    public boolean login(Employees employee) {
        Employees checkUser = find(employee.getUserName());
        boolean result = checkUser!=null && checkUser.getUserPassword().equals(employee.getUserPassword());
        System.out.println("================================dd");
        System.out.println("result: " + result);
        return result;
//        return checkUser!=null && checkUser.getUserPassword().equals(employee.getUserPassword());
    }

    @Override
    public boolean logout() {
        return  false;
    }

    @Override
    public boolean isUsernameExist(String username) {
        // Biến String lưu mã lệnh SQL dùng để truy vấn CSDL
        // Truy vấn một row trong bảng "Users" có username trùng khớp với parameter username
        String jpql = "Select e From Employees e WHERE e.userName=:userName";
        
        // Khởi tạo object query để chuẩn bị truy vấn
        Query query = em.createQuery(jpql);
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

    @Override
    public boolean validUsernameLength(String username) {
//       if (!username.matches("[a-z0-9_-]{6,12}$")) {
//            RequestContext.getCurrentInstance().execute("alert('Username must be between 6 and 12 characters, no spaces, no accents and no special characters !!!');");
//            return true;
//        }
//        else
//        {
//            return false;
//        }
        return false;
    }

    @Override
    public boolean validPasswordLength(String password) {
//        if (!password.matches("[a-z0-9_-]{6,12}$")) {
//            RequestContext.getCurrentInstance().execute("alert('The password must be at least 8 characters long, but no more than 32, no spaces, no accents and no special characters !!!');");
//            return true;
//        }
//        else
//        {
//            return false;
//        }
        return false;
    }
    
}
