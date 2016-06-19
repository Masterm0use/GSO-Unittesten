
import bank.bankieren.Bank;
import bank.bankieren.IBank;
import bank.bankieren.IKlant;
import bank.bankieren.IRekening;
import bank.bankieren.Klant;
import bank.bankieren.Money;
import bank.internettoegang.Bankiersessie;
import bank.internettoegang.IBankiersessie;
import fontys.util.InvalidSessionException;
import fontys.util.NumberDoesntExistException;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mastermouse
 */
public class TestIBankiersessie {

    private Bank internalBank;
    private IBank bank;
    private IKlant klant1;
    private IKlant klant2;
    private Money money;
    private IBankiersessie bankiersessie;
    int testRek1;
    int testRek2;

    public TestIBankiersessie() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }
    
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws RemoteException {
        internalBank = new Bank("TestBank");
        bank = internalBank;
        klant1 = new Klant("John Cena", "RektCity");
        klant2 = new Klant("Lala", "TeletubiesSecretCity");
        testRek1 = bank.openRekening(klant1.getNaam(), klant1.getPlaats());
        testRek2 = bank.openRekening(klant1.getNaam(), klant1.getPlaats());
        bankiersessie = new Bankiersessie(testRek1, bank);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void isGeldigTest() throws RemoteException {
        boolean geldig = bankiersessie.isGeldig();

        assertEquals("Session was invalid before it expired", true, geldig);
    }

    @Test
    public void waitExpiredTest() throws RemoteException{
        try{
            Thread.sleep(600000);
            boolean geldig = bankiersessie.isGeldig();
            
            assertEquals("Session was still valid after it expired", false, geldig);
        }
        catch(Exception ex){
            assertTrue("Unexpected Exception", false);
        }
    }
     
    @Test
    public void TransferTestTooMuch() throws InvalidSessionException, NumberDoesntExistException, RemoteException {
        money = new Money(1000000000, Money.EURO);

        boolean success = bankiersessie.maakOver(testRek2, money);

        assertEquals("Transfer above limit succeeded", false, success);
    }

    @Test
    public void TransferTestSuccess() throws InvalidSessionException, NumberDoesntExistException, RemoteException {
        money = new Money(100, Money.EURO);

        boolean success = bankiersessie.maakOver(testRek2, money);

        assertEquals("Transfer within limit failed", true, success);
    }

    @Test
    public void TransferTestIllegal() throws InvalidSessionException, NumberDoesntExistException, RemoteException {
        exception.expect(RuntimeException.class);
        money = new Money(-100, Money.EURO);

        boolean success = bankiersessie.maakOver(testRek2, money);

        assertEquals("Transfer with illegal arguments succeeded", false, success);
    }

    @Test
    public void GetRekeningTest() throws InvalidSessionException, RemoteException {
        IRekening testRekening = bankiersessie.getRekening();

        assertEquals("Accounts didnt match", testRekening.getNr(), testRek1);
    }
}
