/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bankieren.Bank;
import bankieren.IBank;
import bankieren.IKlant;
import bankieren.IRekening;
import bankieren.Klant;
import bankieren.Money;
import centrale.Centrale;
import centrale.ICentrale;
import fontys.util.InvalidSessionException;
import fontys.util.NumberDoesntExistException;
import internettoegang.Bankiersessie;
import internettoegang.IBankiersessie;
import java.io.IOException;
import java.rmi.RemoteException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Mastermouse
 */
public class TestBankSessie {
    private Bank internalBank;
    private IBank bank;
    private IKlant klant1;
    private IKlant klant2;
    private Money money;
    private IBankiersessie bankiersessie;
    int rek1;
    int rek2;
    
    public TestBankSessie() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws RemoteException, IOException{
        
        ICentrale centrale = new Centrale();
        internalBank = new Bank("TestBank", centrale);
        bank = internalBank;
        klant1 = new Klant("John Cena", "RektCity");
        klant2 = new Klant("Lala", "TeletubiesSecretCity");
        rek1 = bank.openRekening(klant1.getNaam(), klant1.getPlaats());
        rek2 = bank.openRekening(klant1.getNaam(), klant1.getPlaats());
        bankiersessie = new Bankiersessie(rek1, bank);     
    }
    
    @After
    public void tearDown() {
    }
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Test
    public void isGeldigTest() throws RemoteException{
        boolean geldig = bankiersessie.isGeldig();

        assertEquals("Session was invalid before it expired", true, geldig);
    }
    
    @Test
    public void TransferTestTooMuch() throws InvalidSessionException, NumberDoesntExistException, RemoteException {
        money = new Money(1000000000, Money.EURO);
        
        boolean success = bankiersessie.maakOver("TestBank", rek2, money);

        assertEquals("Transfer above limit succeeded", false, success);
    }
    
    @Test
    public void TransferTestSuccess() throws InvalidSessionException, NumberDoesntExistException, RemoteException {
        money = new Money(100, Money.EURO);
        
        boolean success = bankiersessie.maakOver("TestBank", rek2, money);

        assertEquals("Transfer within limit failed", true, success);
    }
    
    @Test
    public void TransferTestIllegal() throws InvalidSessionException, NumberDoesntExistException, RemoteException {
        exception.expect(RuntimeException.class);
        money = new Money(-100, Money.EURO);
     
        boolean success = bankiersessie.maakOver("TestBank", rek2, money);

        assertEquals("Transfer with illegal arguments succeeded", false, success);
    }
    
    @Test
    public void GetRekeningTest() throws InvalidSessionException, RemoteException {
        IRekening testRekening = bankiersessie.getRekening();
        
        assertEquals("Accounts didn't match", testRekening.getNr(), rek1);
    }
    
     
    @Test
    public void isGeldigExpiredTest() throws RemoteException{
        try{
            Thread.sleep(600000);
            boolean geldig = bankiersessie.isGeldig();
            
            assertEquals("Session was still valid after it expired", false, geldig);
        }
        catch(Exception ex){
            assertTrue("Unexpected Exception", false);
        }
    }
    
}
