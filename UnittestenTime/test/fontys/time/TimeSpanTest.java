/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author Mastermouse
 */
public class TimeSpanTest {
    
    private ITime It1;
    private ITime It2;
    private TimeSpan time;
    
    
    public TimeSpanTest() {
    }
    
    
    
    @Before
    public void setUp() {
        //Begin tijd
        It1 = new Time(2000, 1, 1, 1, 1); 
        //Eind tijd
        It2 = new Time(2000, 2, 1, 1, 1);
        //Time Span
        time = new TimeSpan(It1, It2);
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void testTimeSpan() throws Exception {
        //Eerst maak ik een begin tijd aan van maand 3, vervolgens van maand 2
        ITime ItTest1 = new Time(2000, 3, 1, 1, 1);
        ITime ItTest2 = new Time(2000, 2, 1, 1, 1);
        
        //Vervolgens kijk ik of er een error komt als de eindtijd eerder is dan de begintijd.
        TimeSpan time2 = new TimeSpan(ItTest1, ItTest2);
    }
    
    @Test
    public void TestBeginTime() {
        /**
         * @return Begin tijd van deze time span
         */
        assertEquals("begin test", time.getBeginTime(), It1);
    }
    
    @Test
    public void TestEndTime() {
        /**
         * @return Het einde van deze time span
         */
        assertEquals("eind test", time.getEndTime().compareTo(It2), 0);
    }
    
    @Test
    public void testChangeLengthWith(){
        /**
         * Het einde van de tijdspan zal verhoogd worden met het aantal: [minutes] min
         * @param minutes  minutes
         */
        //Eindtijd +50
        ITime time2 = new Time(1970,2,1,1,51);
        time.changeLengthWith(50);
        //Eindtijden vergelijken
        assertEquals("Test of het verhoogd is", time.getEndTime().compareTo(time2), 0);

        try {
            time.changeLengthWith(-10);
        }
        catch (Exception ex) {
            // Er mag geen negative getal uitkomen, Staat als exeption in de methode
        }
    }
    
    @Test
    public void testMove() throws Exception {
        /**
         * De tijd zal worden aangepast (minuten)
         * @param minutes (a negative value is allowed)
         */
        //De minuten zal nu verhoogd worden naar 51 van 1 
        time.move(50);
        
        //Deze tijd is 50 minuten later dan It1
        ITime It3 = new Time(2000,1,1,1,51);
        ITime It4 = new Time(2000,2,1,1,51);
        TimeSpan time2 = new TimeSpan(It3, It4);

        //Begintijd testen
        assertEquals("begintijd", time.getBeginTime().compareTo(time2.getBeginTime()), 0);

        //Eindtijd testen
        assertEquals("Eindtijd", time.getEndTime().compareTo(time2.getEndTime()), 0);
    }
    
    
    
    
    
    
    @After
    public void tearDown() {
        //niks
    }

   
}
