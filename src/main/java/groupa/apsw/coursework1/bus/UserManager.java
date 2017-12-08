package groupa.apsw.coursework1.bus;

import groupa.apsw.coursework1.ent.Address;
import groupa.apsw.coursework1.ent.SystemUser;
import groupa.apsw.coursework1.pers.SystemUserFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author alpha
 */
@Stateless
public class UserManager {

    @EJB
    public SystemUserFacade suf;

    private List<SystemUser> users;

    public List<SystemUser> getUsers() {
        return users;
    }

    public UserManager() {
        users = new ArrayList<>();
//        users = suf.findAll();
    }

    public List<SystemUser> mockUsers() {
        Address addr = new Address("85 Newcome road", "Portsmouth", "PO1 5DR", "UK");
        SystemUser su = new SystemUser("testUser", "password", "Light", "Yagami", addr, "l.Yagami@gmail.com");

        users.add(su);

        for (SystemUser user : users) {
            suf.create(user);
        }
        return users;
    }

    public SystemUser createEmptyUser() {
        SystemUser su = new SystemUser();
        su.setAddress(new Address());
        suf.create(su);
        return su;
    }

    public void add(SystemUser su) {
        users.add(su);
    }
    
    public boolean validUser(){
        return true;
    }
}
