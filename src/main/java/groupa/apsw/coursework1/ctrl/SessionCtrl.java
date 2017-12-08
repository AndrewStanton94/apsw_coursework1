package groupa.apsw.coursework1.ctrl;

import groupa.apsw.coursework1.bus.UserManager;
import groupa.apsw.coursework1.ent.Address;
import groupa.apsw.coursework1.ent.SystemUser;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author alpha
 */
@Named(value = "sessionCtrl")
@SessionScoped
public class SessionCtrl implements Serializable {

    @EJB
    private UserManager um;
    private SystemUser su;
    private List<SystemUser> users;
    private String givenUserName;
    private String givenPassword;
    private SystemUser currentUser;

    public SystemUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(SystemUser currentUser) {
        this.currentUser = currentUser;
    }

    public String getGivenUserName() {
        return givenUserName;
    }

    public void setGivenUserName(String givenUserName) {
        this.givenUserName = givenUserName;
    }

    public String getGivenPassword() {
        return givenPassword;
    }

    public void setGivenPassword(String givenPassword) {
        this.givenPassword = givenPassword;
    }

    public List<SystemUser> getUsers() {
        return users;
    }

    public SystemUser getSu() {
        return su;
    }

    /**
     * Creates a new instance of SessionCtrl2
     */
    public SessionCtrl() {
        users = new ArrayList<>();
    }

    public String generateData() {
        users = um.mockUsers();
        return "";
    }

    public String gotoEdit() {
        return "editProfileView";
    }

    public String gotoView() {
        return "dashboardView";
    }

    public String gotoRegistration() {
        su = um.createEmptyUser();
        return "registerView";
    }

    public String gotoLogin() {
        return "loginView";
    }

    public String registerUser() {
        users.add(su);
        su = um.createEmptyUser();
        return "dashboardView";
    }

    public String login() {
        SystemUser su = um.mockUsers().get(0);
        boolean nameMatches = su.getUsername().equalsIgnoreCase(givenUserName);
        boolean passwordMatches = su.getPassword().equals(givenPassword);
        boolean allowAccess = nameMatches && passwordMatches;
        if (allowAccess) {
            return "dashboardView";
        } else {
            return "";
        }
    }
}
