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
import fontys.util.NumberDoesntExistException;
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

    public class BankTests {

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
        public void setUp() {
            internalBank = new Bank("BankTest");
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
                boolean success = bank.maakOver(sendingAccount, receivingAccount, money);

                assertEquals("Niggt goed", false, success);
            } catch (Exception ex) {
                assertTrue("Unexpected Exception", false);
            }
        }

        @Test
        public void TransferTestNegative() throws NumberDoesntExistException {
            exception.expect(RuntimeException.class);

            money = new Money(-100000000, Money.EURO);

            boolean success = bank.maakOver(sendingAccount, receivingAccount, money);

            assertEquals("kasjfhjdsnjfds", false, success);
        }

        @Test
        public void TransferTestUnknownSender() throws NumberDoesntExistException {
            exception.expect(NumberDoesntExistException.class);

            money = new Money(100000, Money.EURO);

            boolean success = bank.maakOver(sendingAccount + 100, receivingAccount, money);

            assertEquals("Het lukte", false, success);
        }

        @Test
        public void TransferTestUnknownNumber() throws NumberDoesntExistException {
            exception.expect(NumberDoesntExistException.class);

            money = new Money(100000, Money.EURO);

            int testAccount = 1234;
            while (testAccount == sendingAccount || testAccount == receivingAccount) {
                testAccount++;
            }

            boolean success = bank.maakOver(sendingAccount, testAccount, money);

            assertEquals("Het lukte", false, success);
        }

        @Test
        public void TransferTestSameAccount() throws NumberDoesntExistException {
            exception.expect(RuntimeException.class);

            money = new Money(100000, Money.EURO);

            boolean success = bank.maakOver(sendingAccount, sendingAccount, money);

            assertEquals("Het lukte", false, success);
        }
    }
}
