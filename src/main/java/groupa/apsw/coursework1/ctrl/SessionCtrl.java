package groupa.apsw.coursework1.ctrl;

import groupa.apsw.coursework1.bus.UserManager;
import groupa.apsw.coursework1.ent.SystemUser;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author alpha
 */
@Named(value = "sessionCtrl2")
@SessionScoped
public class SessionCtrl implements Serializable {

    @EJB
    private UserManager um;
    private SystemUser su;
    private String givenUsername;
    private String givenPassword;

    public String getGivenUsername() {
        return givenUsername;
    }

    public void setGivenUsername(String givenUsername) {
        this.givenUsername = givenUsername;
    }

    public String getGivenPassword() {
        return givenPassword;
    }

    public void setGivenPassword(String givenPassword) {
        this.givenPassword = givenPassword;
    }

    public List<SystemUser> getUsers() {
        return um.getUsers();
    }

    public SystemUser getSu() {
        return su;
    }

    /**
     * Creates a new instance of SessionCtrl2
     */
    public SessionCtrl() {
    }

    public String generateData() {
        um.mockUsers();
        return "";
    }

    public String gotoEdit() {
        return "editProfileView";
    }

    public String updateUser() {
        System.out.println(su.getFirstName());
        su = um.updateUser(su);
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
        um.add(su);
        su = um.createEmptyUser();
        return "dashboardView";
    }

    public String login() {
        boolean valid = um.isValidUser(givenUsername, givenPassword);
        
        if (valid) {
            su = um.getCurrentUser();
            return "dashboardView";
        } else {
            return "";
        }
    }
}
