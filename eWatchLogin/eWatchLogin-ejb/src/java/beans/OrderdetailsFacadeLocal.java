/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Orderdetails;
import entities.Orders;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yup
 */
@Local
public interface OrderdetailsFacadeLocal {

    void create(Orderdetails orderdetails);

    void edit(Orderdetails orderdetails);

    void remove(Orderdetails orderdetails);

    Orderdetails find(Object id);

    List<Orderdetails> findAll();

    List<Orderdetails> findRange(int[] range);

    int count();
    
    List<Orderdetails> findByOrderId(Orders query);
    
}
