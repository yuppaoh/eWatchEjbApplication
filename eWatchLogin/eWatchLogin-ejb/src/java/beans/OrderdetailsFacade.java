/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Orderdetails;
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
public class OrderdetailsFacade extends AbstractFacade<Orderdetails> implements OrderdetailsFacadeLocal {

    @PersistenceContext(unitName = "eWatchLogin-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderdetailsFacade() {
        super(Orderdetails.class);
    }

    @Override
    public List<Orderdetails> findByOrderId(Orders order){
        List<Orderdetails> orderdetails = em.createQuery("SELECT p FROM Products p WHERE p.orderId = :orderId")
                .setParameter("orderId", order).getResultList();
        return orderdetails;
    }
    
}
