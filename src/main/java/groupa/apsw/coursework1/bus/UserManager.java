package groupa.apsw.coursework1.bus;

import groupa.apsw.coursework1.ent.Address;
import groupa.apsw.coursework1.ent.SystemUser;
import groupa.apsw.coursework1.pers.AddressFacade;
import groupa.apsw.coursework1.pers.SystemUserFacade;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author alpha
 */
@Stateless
public class UserManager {

    @EJB
    public SystemUserFacade suf;

    @EJB
    public AddressFacade af;

    private List<SystemUser> users;
    private SystemUser currentUser;

    public SystemUser getCurrentUser() {
        return currentUser;
    }

    public List<SystemUser> getUsers() {
        return users;
    }

    public UserManager() {
        users = new ArrayList<>();
    }

    public List<SystemUser> mockUsers() {

        Address addr = new Address("85 Newcome road", "Portsmouth", "PO1 5DR", "UK");
        Address addr2 = new Address("2 delamere road", "Portsmouth", "PO1 5DR", "UK");
        Address addr3 = new Address("5 haselemre road", "Portsmouth", "PO1 5DR", "UK");
        Address addr4 = new Address("102 Lawrence road", "Portsmouth", "PO1 5DR", "UK");
        Address addr5 = new Address("85 Newcome road", "Portsmouth", "PO1 5DR", "UK");

        Collections.addAll(users,
            new SystemUser("testUser", "password", "Light", "Yagami", addr, "l.Yagami@gmail.com"),
            new SystemUser("testUser2", "password", "Clint", "Eastwood", addr2, "c.Eastwood@gmail.com"),
            new SystemUser("testUser3", "password", "Alan", "Turing", addr3, "a.Turing@gmail.com"),
            new SystemUser("testUser4", "password", "Marcus", "Aurelius", addr4, "M.Aurelius @gmail.com"),
            new SystemUser("testUser5", "password", "Rick", "Sanchez", addr5, "r.Sanchez@gmail.com")
        );

        for (SystemUser user : users) {
            suf.create(user);
        }
        af.create(addr);
        af.create(addr2);
        af.create(addr3);
        af.create(addr4);
        af.create(addr5);
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

    public boolean isValidUser(String username, String password) {
        TypedQuery<SystemUser> query = suf.getEntityManager().createNamedQuery("SystemUser.findByUserName", SystemUser.class);
        SystemUser su = query.setParameter("username", username).getSingleResult();
        System.out.println(su);
        if (su == null) {
            currentUser = null;
            return false;
        } else {
            if (su.getPassword().equals(password)) {
                currentUser = su;
                return true;
            }
            return false;
        }
    }

    public SystemUser updateUser(SystemUser updatedUser) {
        currentUser = suf.edit(updatedUser);
        users = suf.findAll();
        return currentUser;
    }
}
