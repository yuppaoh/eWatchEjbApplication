/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Carts;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yup
 */
@Stateless
public class CartsFacade extends AbstractFacade<Carts> implements CartsFacadeLocal {

    @PersistenceContext(unitName = "eWatchLogin-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CartsFacade() {
        super(Carts.class);
    }
    
    @Override
    public List<Carts> findByUsername(String query){
        List<Carts> carts = em.createQuery("SELECT c FROM Carts c WHERE c.customerId = :customerId")
                .setParameter("customerId", query).getResultList();
        return carts;
    }
    
}
