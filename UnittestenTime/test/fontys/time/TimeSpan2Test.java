/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

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
public class TimeSpan2Test {
    
    private ITime It1;
    private ITime It2;
    private TimeSpan2 time;
    
    
    public TimeSpan2Test() {
    }
    
    @Before
    public void setUp() {
        //Begin tijd
        It1 = new Time(1970, 1, 1, 1, 1); 
        //Eind tijd
        It2 = new Time(1970, 2, 1, 1, 1);
        //Time Span
        time = new TimeSpan2(It1, It2);
    }
    
    
    @Test
    public void testTimeSpan() throws Exception {
        //Eerst maak ik een begin tijd aan van maand 3, vervolgens van maand 2
        ITime ItTest1 = new Time(2000, 3, 1, 1, 1);
        ITime ItTest2 = new Time(2000, 2, 1, 1, 1);
        
        //Vervolgens kijk ik of er een error komt als de eindtijd eerder is dan de begintijd.
        TimeSpan2 time2 = new TimeSpan2(ItTest1, ItTest2);
    }
    
    @Test
    //Get
    public void TestBeginTime() {
        /**
         * @return Begin tijd van deze time span
         */
        assertEquals("begin test", time.getBeginTime(), It1);
    }
    
    @Test
    //Get
    public void TestEndTime() {
        /**
         * @return Het einde van deze time span
         */
        assertEquals("eind test", time.getEndTime().compareTo(It2), 0);
    }
    
    @Test
    public void testLength(){
        /**
         *
         * @return de lengte van de time span in minuten dit moet altijd posetief zijn
         */

        setUp();
        assertEquals("Test de lengte van de periode", time.length(), It2.difference(It1));
    }

    @Test
    public void testSetBeginTime() throws Exception {
        /**
         * beginTime zal het nieuwe beginTijd worden
         * @param beginTime moet eerder zijn dan de eindtijd
         */

        ITime bt2 = new Time(1955, 1, 1, 1, 0);
        time.setBeginTime(bt2);
        assertEquals("Begintijd is niet goed", time.getBeginTime().compareTo(bt2), 0);

        ITime bt3 = new Time(2016,9,22,20,10);
        try {
            time.setBeginTime(bt3);
        }
        catch (Exception ex) {
            //Begintijd > eindtijd
        }
    }
    
    @Test
     public void testSetEndTime() throws IllegalArgumentException {
        /**
         * EndTime zal de nieuwe eindtijd worden.
         * 
         */

        ITime et2 = new Time(2000, 1, 3, 4, 4);
        time.setEndTime(et2);
        assertEquals("Eindtijd is niet goed", time.getEndTime().compareTo(et2), 0);
        ITime fout = new Time(1953, 1, 12, 05, 25);
        try {
            time.setEndTime(fout);
        }
        catch(IllegalArgumentException ex) {
        }
    }
    
    @Test
    public void testChangeLengthWith() {
        /**
         * Het einde van de tijdspan zal verhoogd worden met het aantal: [minutes] min
         * @param minutes  minutes
         */
        //Eindtijd +20
        ITime test1 = new Time(1955,2,1,1,21);
        time.changeLengthWith(20);
        //Eindtijden vergelijken
        assertEquals("Test of het verhoogd is", time.getEndTime().compareTo(test1), 0);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testChangeLengthWithNegative() {
        /**
         * Lengte van de 'timespan' proberen te veranderen met een negatief getal
         * @param minutes  minutes
         */
        time.changeLengthWith(-10);
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
        TimeSpan2 time2 = new TimeSpan2(It3, It4);

        //Begintijd testen
        assertEquals("begintijd", time.getBeginTime().compareTo(time2.getBeginTime()), 0);

        //Eindtijd testen
        assertEquals("Eindtijd", time.getEndTime().compareTo(time2.getEndTime()), 0);
    }
    
    @Test
    public void testIsPartOf() throws Exception {
        /**
         *
         * @param timeSpan
         * @return False of true
         */

        //Deze valt erin.
        ITime bt2 = new Time(1970, 1, 1, 1, 2);
        ITime et2 = new Time(1970, 1, 1, 1, 5);
        TimeSpan2 test2 = new TimeSpan2(bt2, et2);
        assertTrue("test2 is deel van time", test2.isPartOf(time));

        //Deze valt er niet helemaal in
        ITime bt3 = new Time(1994, 1, 1, 1, 2);
        ITime et3 = new Time(1994, 2, 1, 1, 5);
        TimeSpan2 test3 = new TimeSpan2(bt3, et3);
        assertFalse("test3 is geen deel van time", test3.isPartOf(time));

        //Deze valt er helemaal niet in
        ITime bt4 = new Time(2000, 1, 1, 1, 2);
        ITime et4 = new Time(2000, 2, 1, 1, 5);
        TimeSpan2 test4 = new TimeSpan2(bt4, et4);
        assertFalse("test4 is geen deel van time", test4.isPartOf(time));
    }
    
    @Test
    public void testUnionWith() throws Exception {
        /**
         *
         * @param timeSpan
         */

        ITime bt2 = new Time(1970, 1, 1, 1, 2);
        ITime et2 = new Time(1970, 1, 1, 1, 3);
        TimeSpan2 ts2 = new TimeSpan2(bt2, et2);

        //Dit is de actie
        TimeSpan2 test3 = (TimeSpan2) time.unionWith(ts2);
        //Hetzelfde maar dan omgedraait, hier moet hetzelfde uitkomen.
        TimeSpan2 test4 = (TimeSpan2) ts2.unionWith(time);

        //Hier test je de begin en eindtijd van de eerste Unionwith (ts3)
        assertEquals("De begintijd is niet gelijk aan time", test3.getBeginTime().compareTo(time.getBeginTime()), 0);
        assertEquals("De eindtijd is niet gelijk aan time", test3.getEndTime().compareTo(time.getEndTime()), 0);

        //Hier doe je precies hetzelfde als hierboven maar dan met ts4
        assertEquals("De eindtijd is niet gelijk aan time", test4.getBeginTime().compareTo(time.getBeginTime()), 0);
        assertEquals("De eindtijd is niet gelijk aan time", test4.getEndTime().compareTo(time.getEndTime()), 0);

        //Voor Null terug te krijgen
        ITime bt5 = new Time(2016, 1, 21, 1, 1);
        ITime et5 = new Time(1996, 3, 1, 1, 1);
        TimeSpan2 ts5 = new TimeSpan2(bt5, et5);
        //Er is geen Union met ts5 omdat deze 20 jaar uit elkaar liggen.
        assertNull("Er komt geen Null terug", ts2.unionWith(ts5));
    }
    
    @Test
    public void testIntersectionWith() throws Exception {
        /**
         *
         * @param timeSpan
         */

        ITime btN = new Time(2000, 1, 1, 1, 1);
        ITime etN = new Time(2000, 2, 2, 2, 2);
        TimeSpan2 testsNull = new TimeSpan2(btN, etN);

        //Geen intersertion vandaar null
        assertNull("geen Null terug", testsNull.intersectionWith(time));

        //Een goede Timespan
        ITime bt2 = new Time(1970, 1, 1, 1, 2);
        ITime et2 = new Time(1970, 2, 1, 1, 15);
        TimeSpan2 test2 = new TimeSpan2(bt2, et2);

        TimeSpan2 test3 = null;
        TimeSpan2 test4 = null;

        try {
            test3 = (TimeSpan2) time.intersectionWith(test2);
        }
        catch (NullPointerException np) {
            System.out.println("Failed bij test3 aanmaken");
        }

        try {
            test4 = (TimeSpan2) test2.intersectionWith(time);
        }
        catch (NullPointerException np) {
            System.out.println("Failed bij test4 aanmaken");
        }

        //Het juiste resultaat:
        TimeSpan2 tsVerwacht = new TimeSpan2(bt2, It2);

        if (test3 != null)
        {
            //Test of de begin en eindtijd ook echt aan de verwachtingen voldoen.
            assertEquals("Niet de juiste TimeSpan", test3.getBeginTime().compareTo(tsVerwacht.getBeginTime()), 0);
            assertEquals("Niet de juiste TimeSpan", test3.getEndTime().compareTo(tsVerwacht.getEndTime()), 0);

            //Het omgekeerde van hierboven
            assertEquals("Niet de juiste TimeSpan", test4.getBeginTime().compareTo(tsVerwacht.getBeginTime()), 0);
            assertEquals("Niet de juiste TimeSpan", test4.getEndTime().compareTo(tsVerwacht.getEndTime()), 0);
        }
    }
}
