
package fontys.time;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mario Schipper
 */
public class AppointmentTest {
    
    ITime beginTime;
    ITime endTime;
    TimeSpan timeSpan;
    Appointment appointment;
    
    
    public AppointmentTest() {
    }
    
  
    @Before
    public void setUp() {
       beginTime = new Time(2000,1,1,1,1);
       endTime = new Time(2000,1,1,1,2);
       timeSpan = new TimeSpan(beginTime, endTime);
       
       appointment = new Appointment("Les", timeSpan);
    }
    
    @Test
    public void testGetSubject() throws Exception{
        setUp();
        //Gaat goed
        assertEquals("De naam van de subject komt overeen.", appointment.getSubject(), "Les");
        //Gaat fout
        assertEquals("De naam van de subject komt niet overeen.", appointment.getSubject(), "GeenLes"); 
    }
    
    @Test
    public void testGetTimeSpan() throws Exception {
        setUp();
        assertEquals("De TimeSpan komen niet overeen.", appointment.getTimeSpan(), timeSpan);
    }
    
    @Test
    public void testInvitees() throws Exception {
        setUp();
        Contact contactTest = new Contact("repelsteeltje");
        
        appointment.addContact(contactTest);
        Contact ccontactTest2 = appointment.invitees().next();
        assertEquals("Contacten zijn niet gelijk", ccontactTest2.getName(), contactTest.getName());
    }
    
     @Test
    public void testAddContact() throws Exception {
        setUp();
        Contact contact1 = new Contact("repelsteeltje");
        Contact contact2 = new Contact("groteBozeWolf");
        appointment.addContact(contact1);
        
        assertTrue("Contact is toegevoegd", appointment.addContact(contact2));
        assertFalse("Contact is niet toegevoegd", appointment.addContact(contact2));
    }
    
    @Test
    public void testRemoveContact() throws Exception {
        setUp();
        Contact contact1 = new Contact("repelsteeltje");
        appointment.addContact(contact1);
        appointment.removeContact(contact1);
    }
}
