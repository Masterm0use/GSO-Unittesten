/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bank.bankieren.Bank;
import bank.bankieren.IBank;
import bank.bankieren.IKlant;
import bank.bankieren.Klant;
import bank.bankieren.Money;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Mastermouse
 */
public class TestBankClass {
    
    public TestBankClass() {
    }
    
    private Bank internalBank;
    private IBank bank;
    private IKlant klant1;
    private IKlant klant2;
    private Money money;
    
    private int sendingAccount;
    private int receivingAccount;
    
       
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp(){
        internalBank = new Bank("TestBank");
         bank = internalBank;
         klant1 = new Klant("John Doe", "Awesometown");
         klant2 = new Klant("Jane Doe", "Boringtown");
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void TransferTestTooMuch() {
        int sendingAccount = bank.openRekening(klant1.getNaam(), klant1.getPlaats());
         int receivingAccount = bank.openRekening(klant2.getNaam(), klant2.getPlaats());
         
        money = new Money(1000000000, Money.EURO);
         
         try {
             boolean success = bank.maakOver(sendingAccount, receivingAccount, money);
             
             assertEquals("Transfer above limit succeeded", false, success);
         }
         catch(Exception ex) {
             assertTrue("Unexpected Exception", false);
        }
     }
    
    @Test
     public void TransferTestSuccess() {
       int sendingAccount = bank.openRekening(klant1.getNaam(), klant1.getPlaats());
         int receivingAccount = bank.openRekening(klant2.getNaam(), klant2.getPlaats());
        
         money = new Money(1000000000, Money.EURO);
         
         try {
             boolean success = bank.maakOver(sendingAccount, receivingAccount, money);
             
             assertEquals("Transfer above limit succeeded", false, success);
         }
         catch(Exception ex) {
             assertTrue("Unexpected Exception", false);
         }
     }
}
