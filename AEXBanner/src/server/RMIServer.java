/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;


import aexbanner.IEffectenbeurs;
import aexbanner.IFonds;
import aexbanner.Koers;
import aexbanner.MockEffectenbeurs;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mastermouse
 */
public class RMIServer {

    // Set port number
    private static final int portNumber = 1099;

    // Set binding name for student administration
    private static final String bindingName = "KoersAdmin";

    // References to registry and student administration
    private Registry registry = null;
    
    private MockEffectenbeurs mockEffectenbeurs = null;

    // Constructor
    public RMIServer() {

        // Print port number for registry
        System.out.println("Server: Port number " + portNumber);

		try {
			mockEffectenbeurs = new MockEffectenbeurs();
		} catch (RemoteException ex) {
			Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
		}
        System.out.println("Server: Beurs administration created");

        // Create registry at port number
        try {
            registry = LocateRegistry.createRegistry(portNumber);
            System.out.println("Server: Registry created on port number " + portNumber);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot create registry");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            registry = null;
        }
        
        mockEffectenbeurs.setKoers("Test", 10);
		
		try {
			System.out.println("Koersen: " + mockEffectenbeurs.getKoersen().size());
		} catch (RemoteException ex) {
			Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
		}
                
        // Bind beurs administration using registry
        try {
            registry.rebind(bindingName, mockEffectenbeurs);
			System.out.println("Server: Beurs administration bound");
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot bind beurs administration");
            System.out.println("Server: RemoteException: " + ex.getMessage());
        }
        
        Timer timer = new Timer();
        
        class UpdateTask extends java.util.TimerTask
        {
            private String bindingName;
            private MockEffectenbeurs mockEffectenbeurs;
            
            public UpdateTask(String bindingName, MockEffectenbeurs mockEffectenbeurs)
            {
                this.bindingName = bindingName;
                this.mockEffectenbeurs = mockEffectenbeurs;
            }
            
            @Override
            public void run()
            {
                try {
                    mockEffectenbeurs.setKoers("bedrijf1", mockEffectenbeurs.getRandomKoers());
                } catch (RemoteException ex) {
                    Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        timer.schedule(new UpdateTask(bindingName, mockEffectenbeurs), 1, 1);
    }

    // Print IP addresses and network interfaces
    private static void printIPAddresses() {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            System.out.println("Server: IP Address: " + localhost.getHostAddress());
            // Just in case this host has multiple IP addresses....
            InetAddress[] allMyIps = InetAddress.getAllByName(localhost.getCanonicalHostName());
            if (allMyIps != null && allMyIps.length > 1) {
                System.out.println("Server: Full list of IP addresses:");
                for (InetAddress allMyIp : allMyIps) {
                    System.out.println("    " + allMyIp);
                }
            }
        } catch (UnknownHostException ex) {
            System.out.println("Server: Cannot get IP address of local host");
            System.out.println("Server: UnknownHostException: " + ex.getMessage());
        }

        try {
            System.out.println("Server: Full list of network interfaces:");
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                System.out.println("    " + intf.getName() + " " + intf.getDisplayName());
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    System.out.println("        " + enumIpAddr.nextElement().toString());
                }
            }
        } catch (SocketException ex) {
            System.out.println("Server: Cannot retrieve network interface list");
            System.out.println("Server: UnknownHostException: " + ex.getMessage());
        }
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Welcome message
        System.out.println("SERVER USING REGISTRY");

        // Print IP addresses and network interfaces
        printIPAddresses();

        // Create server
        RMIServer server = new RMIServer();
    }
}

