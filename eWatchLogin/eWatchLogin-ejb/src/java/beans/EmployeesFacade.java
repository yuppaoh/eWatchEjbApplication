/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Employees;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yup
 */
@Stateless
public class EmployeesFacade extends AbstractFacade<Employees> implements EmployeesFacadeLocal {

    @PersistenceContext(unitName = "eWatchLogin-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeesFacade() {
        super(Employees.class);
    }

    @Override
    public boolean checkEmployee(Employees employee) {
        Employees emp = find(employee.getUserName());
        
        boolean result = emp!=null&&emp.getUserPassword().equals(employee.getUserPassword());
        System.out.println("================================");
        System.out.println("result: " + result);
        
        return emp!=null&&emp.getUserPassword().equals(employee.getUserPassword());
    }
    
}
