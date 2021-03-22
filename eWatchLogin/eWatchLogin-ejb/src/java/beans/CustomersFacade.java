/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Customers;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.primefaces.context.RequestContext;

/**
 *
 * @author yup
 */
@Stateless
public class CustomersFacade extends AbstractFacade<Customers> implements CustomersFacadeLocal {

    @PersistenceContext(unitName = "eWatchLogin-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomersFacade() {
        super(Customers.class);
    }
    
    @Override
    public boolean checkCustomer(Customers customer) {
        System.out.println("in checkCustomer");
        Customers cust = find(customer.getCustomerId());
        
        System.out.println("cust: " + cust);
        
        boolean result = cust!=null&&cust.getCustomerPassword().equals(customer.getCustomerPassword());
        System.out.println("================================");
        System.out.println("result: " + result);
        
        return cust!=null&&cust.getCustomerPassword().equals(customer.getCustomerPassword());
    }

    @Override
    public boolean isUsernameExist(String username) {
        System.out.println("***********************************");
        System.out.println("CustomerId: " + username);
        // Biến String lưu mã lệnh SQL dùng để truy vấn CSDL
        // Truy vấn một row trong bảng "Users" có username trùng khớp với parameter username
        String jpql = "SELECT c FROM Customers c WHERE c.customerId = :customerId";
        
        System.out.println("****************************** Select ok");
        
        // Khởi tạo object query để chuẩn bị truy vấn
        Query query = em.createQuery(jpql);
        System.out.println("****************************** query ok");
        
        // Gán parameters username vào câu lệnh truy vấn
        query.setParameter("customerId", username);
        System.out.println("****************************** setParameter ok");
        
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
        if (!username.matches("[a-z0-9_-]{6,12}$")) {
            RequestContext.getCurrentInstance().execute("alert('Username must be between 6 and 12 characters, no spaces, no accents and no special characters !!!');");
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean validPasswordLength(String password) {
        if (!password.matches("[a-z0-9_-]{6,12}$")) {
            RequestContext.getCurrentInstance().execute("alert('Password must be between 6 and 12 characters, no spaces, no accents and no special characters !!!');");
            return true;
        }
        else
        {
            return false;
        }
    }
    
}
