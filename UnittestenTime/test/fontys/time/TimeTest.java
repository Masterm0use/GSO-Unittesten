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
public class TimeTest {
    
    public TimeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
//    private Time timeInstance;
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDayInWeek method, of class Time.
     */
    @Test
    public void testGetDayInWeek() {
        System.out.println("getDayInWeek");
        Time timeInstance = new Time(2016, 3, 20, 14, 46);
        DayInWeek expResult = DayInWeek.SUN;
        DayInWeek result = timeInstance.getDayInWeek();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getYear method, of class Time.
     */
    @Test
    public void testGetYear() {
        System.out.println("getYear");
        Time timeInstance = new Time(2016, 3, 20, 14, 46);
        int expResult = 2016;
        int result = timeInstance.getYear();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getMonth method, of class Time.
     */
    @Test
    public void testGetMonth() {
        System.out.println("getMonth");
        Time timeInstance = new Time(2016, 3, 20, 14, 46);
        int expResult = 3;
        int result = timeInstance.getMonth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getDay method, of class Time.
     */
    @Test
    public void testGetDay() {
        System.out.println("getDay");
        Time timeInstance = new Time(2016, 3, 20, 14, 46);
        int expResult = 20;
        int result = timeInstance.getDay();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getHours method, of class Time.
     */
    @Test
    public void testGetHours() {
        System.out.println("getHours");
        Time timeInstance = new Time(2016, 3, 20, 14, 46);
        int expResult = 14;
        int result = timeInstance.getHours();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getMinutes method, of class Time.
     */
    @Test
    public void testGetMinutes() {
        System.out.println("getMinutes");
        Time timeInstance = new Time(2016, 3, 20, 14, 46);
        int expResult = 46;
        int result = timeInstance.getMinutes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of plus method, of class Time.
     */
    @Test
    public void testPlus() {
        System.out.println("plus");
        Time timeInstance = new Time(2016, 3, 20, 14, 46);
        int minutes = 2;
        int expResult = 48;
        ITime result = timeInstance.plus(minutes);
        assertEquals(expResult, result.getMinutes());
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class Time.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Time timeInstance = new Time(2016, 3, 20, 14, 46);
        ITime t = new Time(2000, 3, 20, 14, 46);
        int expResult = -1;
        int result = timeInstance.compareTo(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of difference method, of class Time.
     */
    @Test
    public void testDifference() {
        System.out.println("difference");
        Time timeInstance = new Time(2016, 3, 20, 14, 46);
        ITime time = new Time (2016, 3, 20, 14, 40);
        int expResult = 6;
        int result = timeInstance.difference(time);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
}
