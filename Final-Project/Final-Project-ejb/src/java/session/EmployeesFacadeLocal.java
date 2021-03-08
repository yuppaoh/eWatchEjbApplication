/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Employees;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ASUS
 */
@Local
public interface EmployeesFacadeLocal {

    void create(Employees users);

    void edit(Employees users);

    void remove(Employees users);

    Employees find(Object id);

    List<Employees> findAll();

    List<Employees> findRange(int[] range);

    int count();

    boolean login(Employees user);

    boolean logout();

    public boolean isUsernameExist(String username);

    public boolean validUsernameLength(String username);

    public boolean validPasswordLength(String password);
    
    
}
