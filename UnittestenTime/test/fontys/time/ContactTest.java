/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Robin
 */
public class ContactTest {
    
    Contact contact;
    TimeSpan ts;
    Appointment appointment;
    Appointment appointment2;
    Appointment appointment3;
    
    public ContactTest() {
    }
    
    @Before
    public void setUp() {
        contact = new Contact("Robin");
        TimeSpan ts = new TimeSpan(new Time(2016, 03, 24, 17, 13), 
                                   new Time(2016, 03, 24, 18, 01));
        appointment = new Appointment("GSO huiswerk maken", ts);
        ts = new TimeSpan(new Time(2016, 03, 24, 19, 30), 
                          new Time(2016, 03, 24, 20, 00));
        appointment2 = new Appointment("test", ts);
        ts = new TimeSpan(new Time(2016, 03, 24, 17, 30), 
                          new Time(2016, 03, 24, 20, 00));
        appointment3 = new Appointment("test", ts);
    }

    /**
     * Test of getName method, of class Contact.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "Robin";
        String result = contact.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of addAppointment method, of class Contact.
     */
    @Test
    public void testAddAppointment() {
        System.out.println("addAppointment");
        boolean expResult = true;
        boolean result = contact.addAppointment(appointment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of addAppointment method, of class Contact.
     */
    @Test
    public void testAddAppointment2() {
        System.out.println("addAppointment");
        boolean expResult = false;
        contact.addAppointment(appointment2);
        boolean result = contact.addAppointment(appointment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of addAppointment method, of class Contact.
     */
    @Test
    public void testAddAppointmentFail() {
        System.out.println("addAppointment");
        boolean expResult = false;
        contact.addAppointment(appointment3);
        boolean result = contact.addAppointment(appointment);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAppointment method, of class Contact.
     */
    @Test
    public void testRemoveAppointment() {
        System.out.println("removeAppointment");
//        Appointment a = null;
//        Contact instance = null;
        contact.addAppointment(appointment);
        contact.removeAppointment(appointment);
        assertEquals(contact.appointments(), null);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
    /**
     * Test of removeAppointment method, of class Contact.
     */
    @Test
    public void testRemoveAppointmentFail() {
        System.out.println("removeAppointment");
//        Appointment a = null;
//        Contact instance = null;
        contact.removeAppointment(appointment);
        assertEquals(contact.appointments(), null);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
    /**
     * Test of removeAppointment method, of class Contact.
     */
    @Test
    public void testRemoveAppointmentFail2() {
        System.out.println("removeAppointment");
//        Appointment a = null;
//        Contact instance = null;
        contact.addAppointment(appointment);
        contact.removeAppointment(appointment2);
        assertEquals(contact.appointments().hasNext(), true);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
    /**
     * Test of appointments method, of class Contact.
     */
    @Test
    public void testAppointments() {
        System.out.println("appointments");
//        Contact instance = null;
        Iterator<Appointment> expResult = null;
        Iterator<Appointment> result = contact.appointments();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
}
