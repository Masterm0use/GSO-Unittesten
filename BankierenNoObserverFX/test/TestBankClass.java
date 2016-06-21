/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bankieren.Bank;
import bankieren.IBank;
import bankieren.IKlant;
import bankieren.Klant;
import bankieren.Money;
import centrale.Centrale;
import centrale.ICentrale;
import fontys.util.NumberDoesntExistException;
import java.io.IOException;
import java.rmi.RemoteException;
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

    @Before
    public void setUp() throws RemoteException, IOException {
        
        ICentrale centrale = new Centrale();
        internalBank = new Bank("TestBank", centrale);
        bank = internalBank;
        klant1 = new Klant("John Cena", "RektCity");
        klant2 = new Klant("Lala", "TeletubiesSecretCity");
        sendingAccount = bank.openRekening(klant1.getNaam(), klant1.getPlaats());
        receivingAccount = bank.openRekening(klant2.getNaam(), klant2.getPlaats());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void TransferTestTooMuch() {
        money = new Money(1000000000, Money.EURO);

        try {
            boolean success = bank.maakOver("TestBank", sendingAccount, receivingAccount, money);

            assertEquals("Niggt goed", false, success);
        } catch (Exception ex) {
            assertTrue("Unexpected Exception", false);
        }
    }

    @Test
    public void TransferTestNegative() throws NumberDoesntExistException, RemoteException {
        exception.expect(RuntimeException.class);

        money = new Money(-100000000, Money.EURO);

        boolean success = bank.maakOver("TestBank", sendingAccount, receivingAccount, money);

        assertEquals("kasjfhjdsnjfds", false, success);
    }

    @Test
    public void TransferTestUnknownSender() throws NumberDoesntExistException, RemoteException {
        exception.expect(NumberDoesntExistException.class);

        money = new Money(100000, Money.EURO);

        boolean success = bank.maakOver("TestBank", sendingAccount+100, receivingAccount, money);

        assertEquals("Het lukte", false, success);
    }

    @Test
    public void TransferTestUnknownNumber() throws RemoteException, NumberDoesntExistException {
        
        money = new Money(100000, Money.EURO);

        int testFakeAccount = 9876;
        while (testFakeAccount == sendingAccount || testFakeAccount == receivingAccount) {
            testFakeAccount++;
        }

        boolean success = bank.maakOver("TestBank", sendingAccount, testFakeAccount, money);

        assertEquals("Het lukte", false, success);
    }

    @Test
    public void TransferTestSameAccount() throws NumberDoesntExistException, RemoteException {
        exception.expect(RuntimeException.class);

        money = new Money(100000, Money.EURO);

        boolean success = bank.maakOver("TestBank", sendingAccount, sendingAccount, money);

        assertEquals("Het lukte", false, success);
    }
}
