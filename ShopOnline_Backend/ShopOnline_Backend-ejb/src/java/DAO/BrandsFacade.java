/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.Brands;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nganl
 */
@Stateless
public class BrandsFacade extends AbstractFacade<Brands> implements BrandsFacadeLocal {

    @PersistenceContext(unitName = "ShopOnline_Backend-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BrandsFacade() {
        super(Brands.class);
    }
    
}
