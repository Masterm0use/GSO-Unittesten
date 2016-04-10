/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner;

import client.RMIClient;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author Mastermouse
 */
public class BannerController {
    
    private AEXBanner banner;
    private IEffectenbeurs effectenbeurs;
    private Timer pollingTimer;
    private RMIClient client;
    private List<IFonds> fondsen;
    
    private static final String bindingName = "KoersAdmin";
    private Registry registry = null;

    public BannerController(AEXBanner banner) {
        
        this.banner = banner;
        //this.effectenbeurs = new MockEffectenbeurs();
        
        // Locate registry at IP address and port number
        // Print IP address and port number for registry
        System.out.println("Client: IP Address: 127.0.0.1");
        System.out.println("Client: Port number 1099");

        // Locate registry at IP address and port number
        try {
            registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
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
                this.effectenbeurs = (MockEffectenbeurs)registry.lookup(bindingName);
            } catch (RemoteException ex) {
                System.out.println("Client: Cannot bind koers administration");
                System.out.println("Client: RemoteException: " + ex.getMessage());
                this.effectenbeurs = null;
            } catch (NotBoundException ex) {
                System.out.println("Client: Cannot bind koers administration");
                System.out.println("Client: NotBoundException: " + ex.getMessage());
                this.effectenbeurs = null;
            }
        }

        // Print result binding student administration
        if (this.effectenbeurs != null) {
            System.out.println("Client: Beurs administration bound");
        } else {
            System.out.println("Client: Beurs administration is null pointer");
        }
        
        // Start polling timer: update banner every two seconds
        pollingTimer = new Timer();
        
        class PeriodiekeActie extends java.util.TimerTask {
            String alleFondsen = "";
            @Override
            public void run() {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try
                    {
                        fondsen = effectenbeurs.getKoersen();
                        for (IFonds f : fondsen){
                            alleFondsen = alleFondsen + " " +  f.getNaam() + " " + f.getKoers();
                        }
                        
                        banner.setKoersen(alleFondsen);
                        
                        System.out.println(alleFondsen.toString());
                        
                        alleFondsen = "";
                        fondsen.clear();
                    } catch (RemoteException ex)
                    {
                        Logger.getLogger(BannerController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            }
        }
        pollingTimer.schedule(new PeriodiekeActie(),0,2000);
        

    }

    // Stop banner controller
    public void stop() {
        pollingTimer.cancel();
        // Stop simulation timer of effectenbeurs
        // TODO
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
}
