package groupa.apsw.coursework1.ctrl;

import groupa.apsw.coursework1.bus.AppointmentManager;
import groupa.apsw.coursework1.bus.UserManager;
import groupa.apsw.coursework1.ent.Appointment;
import groupa.apsw.coursework1.ent.SystemUser;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author alpha
 */
@Named(value = "sessionCtrl2")
@SessionScoped
public class SessionCtrl implements Serializable {

    @EJB
    private UserManager um;
    @EJB
    private AppointmentManager am;
    private SystemUser su;
    private Appointment appointment;

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
    private String givenUsername;
    private String givenPassword;
    
    private ScheduleModel appointmentModel;
    private ScheduleModel lazyAppointmentModel;
    
   

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
    
    public List<Appointment> getAppointments(){
        return am.getAppointments();
    }

    public SystemUser getSu() {
        return su;
    }

    public String generateData() {
        um.mockUsers();
        am.mockAppointments(um.getUsers());
        return "";
    }

    public String gotoEdit() {
        return "editProfileView";
    }

    public String gotoDashboard() {
        return "dashboardView";
    }
    
    public String updateUser() {
        System.out.println(su.getFirstName());
        su = um.updateUser(su);
        return "dashboardView";
    }
    
    public String addAppointment(){
        appointment.setOwner(su);
        am.add(appointment);
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
    
    public String gotoAddAppointment() {
        return "createAppointmentView";
    }
    
    public String gotoLookup() {
        return "lookupView";
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
