/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

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
    
    public Appointment(String subject, ITimeSpan timeSpan){
        this.subject = subject;
        this.timeSpan = timeSpan;
        contacten = new ArrayList<Contact>();
    }
    
    public String getSubject(){
        return subject;
    }
    
    public ITimeSpan getTimeSpan(){
        return timeSpan;
    }
    
    public Iterator<Contact> invitees(){
        return contacten.iterator();
    }
    
//     public boolean addContact(Contact con) {
//        for (Contact contact : contacten) {
//            TODO GET NAME FROM CONTACT
//                return false;
//            }
//        }
//        return contacten.add(con);
//    }
    
     public void removeContact(Contact c) {
        contacten.remove(c);
    }
}
