/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bankieren.Bank;
import bankieren.IBank;
import centrale.Centrale;
import centrale.ICentrale;
import internettoegang.Balie;
import internettoegang.IBalie;
import java.io.IOException;
import java.rmi.RemoteException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mastermouse
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
    public void setUp() throws IOException {
        ICentrale centrale = new Centrale();
        try {
            bank = new Bank("Test bank", centrale);
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
