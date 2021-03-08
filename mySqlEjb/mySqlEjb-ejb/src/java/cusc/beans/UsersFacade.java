/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cusc.beans;

import cusc.entities.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yup
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> implements UsersFacadeLocal {

    @PersistenceContext(unitName = "mySqlEjb-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }

    @Override
    public boolean login(Users user) {
        Users checkUser = find(user.getUserName());
        Boolean result = checkUser!=null && checkUser.getUserPassword().equals(user.getUserPassword());
        System.out.println("================================");
        System.out.println("result: " + result.toString());
        return checkUser!=null && checkUser.getUserPassword().equals(user.getUserPassword());
    }

    @Override
    public boolean logout() {
        return false;
    }
    
}
