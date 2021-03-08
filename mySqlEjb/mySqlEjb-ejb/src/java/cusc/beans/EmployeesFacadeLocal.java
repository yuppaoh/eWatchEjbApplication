/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cusc.beans;

import cusc.entities.Employees;
import cusc.entities.Users;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yup
 */
@Local
public interface EmployeesFacadeLocal {

    void create(Employees employees);

    void edit(Employees employees);

    void remove(Employees employees);

    Employees find(Object id);

    List<Employees> findAll();

    List<Employees> findRange(int[] range);

    int count();
    
    boolean login(Employees user);
    
    boolean logout();
    
}
