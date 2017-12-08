/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupa.apsw.coursework1.bus;

import groupa.apsw.coursework1.ent.Address;
import groupa.apsw.coursework1.ent.SystemUser;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author alpha
 */
@Stateless
public class UserManager {
    public List<SystemUser> mockUsers() {
        List<SystemUser> users = new ArrayList<>();
        Address addr = new Address("85 Newcome road", "Portsmouth", "PO1 5DR", "UK");
        SystemUser su = new SystemUser("testUser", "password", "Light", "Yagami", addr, "l.Yagami@gmail.com");
        
        users.add(su);
        return users;
    }
    
    public SystemUser createEmptyUser(){
        SystemUser su = new SystemUser();
        su.setAddress(new Address());
        return su;
    }
}
