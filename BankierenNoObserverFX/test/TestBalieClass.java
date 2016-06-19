/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bank.bankieren.Bank;
import bank.bankieren.IBank;
import bank.internettoegang.Balie;
import bank.internettoegang.IBalie;
import java.rmi.RemoteException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Robin de Kort
 */
public class TestBalieClass {
    
    private IBalie balie;
    private IBank bank;
    
    public TestBalieClass() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        try {
            bank = new Bank("Test Rabobank");
            balie = new Balie(bank);
        } catch (Exception ex) {
            
        }
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void createAccountTest() throws RemoteException {
        String result = balie.openRekening("John Cena", "RektCity", "1234567");
        assertTrue(true);
    }
    
    @Test 
    public void createAccountNoName() throws RemoteException {
        String result = balie.openRekening("", "RektCity", "1234567");
        assertNull("Account was created with no name", result);
    }
    
    @Test 
    public void createAccountNoCity() throws RemoteException {
        String result = balie.openRekening("John Cena", "", "1234567");
        assertNull("Account was created with no name", result);
    }
    
    @Test 
    public void createAccountNoPassword() throws RemoteException {
        String result = balie.openRekening("John Cena", "RektCity", "");
        assertNull("Account was created with no name", result);
    }
    
    @Test 
    public void createAccountShortPassword() throws RemoteException {
        String result = balie.openRekening("John Cena", "RektCity", "1");
        assertNull("Account was created with no name", result);
    }
    
    @Test 
    public void createAccountLongPassword() throws RemoteException {
            String result = balie.openRekening("John Cena", "RektCity", "1234567");
            assertNull("Account was created with no name", result);
    }
}
    
