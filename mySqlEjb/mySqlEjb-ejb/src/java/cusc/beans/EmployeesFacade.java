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

/**
 *
 * @author yup
 */
@Stateless
public class EmployeesFacade extends AbstractFacade<Employees> implements EmployeesFacadeLocal {

    @PersistenceContext(unitName = "mySqlEjb-ejbPU")
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
        Boolean result = checkUser!=null && checkUser.getUserPassword().equals(employee.getUserPassword());
        System.out.println("================================");
        System.out.println("result: " + result.toString());
        return checkUser!=null && checkUser.getUserPassword().equals(employee.getUserPassword());
    }

    @Override
    public boolean logout() {
        return false;
    }
    
}
