/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

import fontys.time.Appointment;
import java.util.*;

/**
 *
 * @author Robin de Kort
 */
public class Contact {
    
    private String name;
    private List<Appointment> agenda;
    
    /**
     *
     * @param name De 'name' van het aan te maken 'contact'
     */
    public Contact(String name){
        this.name = name;
        agenda = new ArrayList();
    }
    
    /**
     *
     * @return De naam van dit 'contact'
     */
    public String getName(){
        return name;
    }
    
    /**
     * 
     * @param newApp Het toe te voegen 'appointment'
     * @return Wanneer het 'appointment' niet overlapt met andere
     * 'appointments' wordt 'true' gereturned en anders 'false'
     */
    boolean addAppointment(Appointment a){
        for (Appointment app : agenda){
            if (app.getTimeSpan().unionWith(a.getTimeSpan()) != null)
            {
                return false;
            }
        }
        agenda.add(a);
        return true;
    }
    
    /**
     * 
     * @param a Het te verwijderen 'appointment'
     */
    void removeAppointment(Appointment a){
        for (Appointment app : agenda){
            if (app == a){
                agenda.remove(a);
                return;
            }
        }
    }
    
    /**
     *
     * @return Alle 'appointments' van dit 'contact'
     */
    public Iterator<Appointment> appointments(){
        if (agenda.size() == 0){
            return null;
        }
        Iterator<Appointment> i = agenda.iterator();
        return i;
    }
}
