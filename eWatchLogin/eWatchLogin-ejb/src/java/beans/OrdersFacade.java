/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Orders;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yup
 */
@Stateless
public class OrdersFacade extends AbstractFacade<Orders> implements OrdersFacadeLocal {

    @PersistenceContext(unitName = "eWatchLogin-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdersFacade() {
        super(Orders.class);
    }

    @Override
    public List<Orders> findByCustomer(String query) {
        List<Orders> orders =  em.createQuery("SELECT o FROM Orders o WHERE o.customerId = :customerId")
                .setParameter("customerId", query).getResultList();
        return orders;
    }
    
//    public List<Products> findByType(String query){
//        List<Products> products = em.createQuery("SELECT p FROM Products p WHERE p.productType = :productType")
//                .setParameter("productType", query).getResultList();
//        return products;
//    }
    
    
}
