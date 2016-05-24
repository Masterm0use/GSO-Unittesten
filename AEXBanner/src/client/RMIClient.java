/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import aexbanner.BannerController;
import aexbanner.IEffectenbeurs;
import aexbanner.Koers;
import aexbanner.MockEffectenbeurs;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mastermouse
 * Voorbeeld van fontys aangepast naar situatie
 */
public class RMIClient extends UnicastRemoteObject {

    // Set binding name for student administration
    private static final String bindingName = "KoersAdmin";

    // References to registry and student administration
    private Registry registry = null;
    private IEffectenbeurs beurs = null;

    // Constructor
    public RMIClient(String ipAddress, int portNumber) throws RemoteException{

        // Print IP address and port number for registry
        System.out.println("Client: IP Address: " + ipAddress);
        System.out.println("Client: Port number " + portNumber);

        // Locate registry at IP address and port number
        try {
            registry = LocateRegistry.getRegistry(ipAddress, portNumber);
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
            registry = null;
        }

        // Print result locating registry
        if (registry != null) {
            System.out.println("Client: Registry located");
        } else {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: Registry is null pointer");
        }

        // Print contents of registry
        if (registry != null) {
            printContentsRegistry();
        }

        // Bind student administration using registry
        if (registry != null) {
            try {
                beurs = (IEffectenbeurs) registry.lookup(bindingName);
            } catch (RemoteException ex) {
                System.out.println("Client: Cannot bind koers administration");
                System.out.println("Client: RemoteException: " + ex.getMessage());
                beurs = null;
            } catch (NotBoundException ex) {
                System.out.println("Client: Cannot bind koers administration");
                System.out.println("Client: NotBoundException: " + ex.getMessage());
                beurs = null;
            }
        }

        // Print result binding student administration
        if (beurs != null) {
            System.out.println("Client: Beurs administration bound");
        } else {
            System.out.println("Client: Beurs administration is null pointer");
        }

        // Test RMI connection
        if (beurs != null) {
            testKoersAdministration();
        }
    }

    // Print contents of registry
    private void printContentsRegistry() {
        try {
            String[] listOfNames = registry.list();
            System.out.println("Client: list of names bound in registry:");
            if (listOfNames.length != 0) {
                for (String s : registry.list()) {
                    System.out.println(s);
                }
            } else {
                System.out.println("Client: list of names bound in registry is empty");
            }
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot show list of names bound in registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
        }
    }
    
    

    // Test RMI connection
    private void testKoersAdministration() {
        
        try {
            System.out.println("Client: Number of koersen: " + beurs.getKoersen().size());
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot get number of koersen");
            System.out.println("Client: RemoteException: " + ex.getMessage());
        }

        // Add student Jan to student administration
        try {
            Koers koers = beurs.setKoers("Jan Inc", beurs.getRandomKoers());
            System.out.println("Client: Koers " + beurs.toString() + " added to Koers administration");
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot add first koers to beurs administration");
            System.out.println("Client: RemoteException: " + ex.getMessage());
        }

        // Add student Jan to student administration
        try {
            Koers koers = beurs.setKoers("Freek Inc", beurs.getRandomKoers());
            System.out.println("Client: Koers " + beurs.toString() + " added to Koers administration");
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot add first koers to Koers administration");
            System.out.println("Client: RemoteException: " + ex.getMessage());
        }

        // Get first student
        try {
            System.out.println("Client: First Koers: " + beurs.getKoersen().get(0));
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot get first student");
            System.out.println("Client: RemoteException: " + ex.getMessage());
        }

        // Get second student
        try {
            System.out.println("Client: second Koers: " + beurs.getKoersen().get(1));
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot get second Koers");
            System.out.println("Client: RemoteException: " + ex.getMessage());
        }

        // Get third student (does not exist)
        try {
            try {
                System.out.println("Client: third Koers: " + beurs.getKoersen().get(2));   
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Client: third Koers does not exist");
            }
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot get third Koers");
            System.out.println("Client: RemoteException: " + ex.getMessage());
        }
    }

    
    // Main method
    public static void main(String[] args) {

        // Welcome message
        System.out.println("CLIENT USING REGISTRY");

        // Get ip address of server
        Scanner input = new Scanner(System.in);
        System.out.print("Client: Enter IP address of server: ");
        String ipAddress = input.nextLine();

        // Get port number
        System.out.print("Client: Enter port number: ");
        int portNumber = input.nextInt();

		try {
			// Create client
			RMIClient client = new RMIClient(ipAddress, portNumber);
		} catch (RemoteException ex) {
			Logger.getLogger(RMIClient.class.getName()).log(Level.SEVERE, null, ex);
		}
    }
}


