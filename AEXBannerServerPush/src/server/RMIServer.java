/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import aexbanner.MockEffectenbeurs;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author Mario, Robin
 */
public class RMIServer {
	
    // Set port number
	public static final int portNumber = 1099;
	
    // Set binding name for student administration
    public static final String bindingName = "KoersAdmin";
	
	// Constructor
	public RMIServer() throws RemoteException{
		MockEffectenbeurs mockEffectenbeurs = new MockEffectenbeurs();
        Registry registry = LocateRegistry.createRegistry(portNumber);
        registry.rebind(bindingName, mockEffectenbeurs.getPublisher());
	}
	
	public static void main(String[] args) throws RemoteException{
//		System.out.println("Start the server on port: ");
//		portNumber = new Scanner(System.in).nextInt();

		// Display the port that will be used
		System.out.println(String.format("Server starting on port %s", portNumber));
		// Start the server
		RMIServer server = new RMIServer();
	}
}
