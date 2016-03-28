/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

import fontys.time.Contact;
import fontys.time.ITimeSpan;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Mario Schipper
 */
public class Appointment {
    
    private String subject;
    private ITimeSpan timeSpan;
    private ArrayList<Contact> contacten; 
    
    /**
     *
     * @param subject Onderderwerp van de vergadering.
     * @param timeSpan Begin- en eindtijd.
     */
    public Appointment(String subject, ITimeSpan timeSpan){
        this.subject = subject;
        this.timeSpan = timeSpan;
        contacten = new ArrayList<Contact>();
    }
    
    /**
     *
     * @return Geeft het onderwerp terug.
     */
    public String getSubject(){
        return subject;
    }
    
    /**
     *
     * @return Geeft de timeSpan terug van dit object.
     */
    public ITimeSpan getTimeSpan(){
        return timeSpan;
    }
    
    /**
     *
     * @return Geeft een lijst terug van contacten als een iterator
     */
    public Iterator<Contact> invitees(){
        return contacten.iterator();
    }
    
    /**
     *
     * @param con Contact wat er toegevoegd moet worden
     * @return Als het contact bestaat en is gelukt zal er true terug komen.
     */
    public boolean addContact(Contact con) {
        for (Contact contact : contacten) {
            if (contact.getName().matches(con.getName())) {
                return false;
            }
        }
        return contacten.add(con);
    }
    
    /**
     *
     * @param con Contact wat er verwijderd moet worden
     */
     public void removeContact(Contact con) {
        contacten.remove(con);
    }
}
