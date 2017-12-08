/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupa.apsw.coursework1.pers;

import groupa.apsw.coursework1.ent.SystemUser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alpha
 */
@Stateless
public class SystemUserFacade extends AbstractFacade<SystemUser> {

    @PersistenceContext(unitName = "groupA.apsw_coursework1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public SystemUserFacade() {
        super(SystemUser.class);
    }
    
}
