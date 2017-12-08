package groupa.apsw.coursework1.bus;

import groupa.apsw.coursework1.ent.Appointment;
import groupa.apsw.coursework1.ent.SystemUser;
import groupa.apsw.coursework1.pers.AppointmentFacade;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author alpha
 */
@Stateless
public class AppointmentManager {

    @EJB
    public AppointmentFacade af;

    private List<Appointment> appointments;
    private Appointment currentAppointment;

    public AppointmentManager() {
        appointments = new ArrayList<>();
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Appointment getCurrentAppointment() {
        return currentAppointment;
    }

    public void setCurrentAppointment(Appointment currentAppointment) {
        this.currentAppointment = currentAppointment;
    }

    public void add(Appointment appointment) {
        af.create(appointment);
        appointments.add(appointment);
    }

    public Appointment updateAppointment(Appointment updatedAppointment) {
        currentAppointment = af.edit(currentAppointment);
        appointments = af.findAll();
        return currentAppointment;
    }

    public List<Appointment> mockAppointments(List<SystemUser> users) {
        Collections.addAll(appointments,
                new Appointment("Dinner",
                        users.get(0),
                        true,
                        new Date(2017, 12, 8, 17, 0),
                        new Date(2017, 12, 8, 18, 0)
                ),
                new Appointment("See friends",
                        users.get(1),
                        true,
                        new Date(2017, 12, 9, 17, 0),
                        new Date(2017, 12, 9, 20, 0)
                )
        );

        for (Appointment appointment : appointments) {
            af.create(appointment);
        }

        return appointments;

    }
}
