/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Timer;

/**
 *
 * @author Mastermouse
 * Voorbeeld van fontys aangepast naar situatie
 */
public class RMIClient {
    
    private static final String bindingName = "Koekje";
    private Timer pollingTimer;

    // References to registry and student
    private Registry registry = null;
    private BannerController bannerController;
    private IEffectenbeurs effectenbeurs = null;

    // Constructor
    public RMIClient(String ipAddress, int portNumber, BannerController bc)
    {
        bannerController = bc;
        System.out.println("Client: IP Address: " + ipAddress);
        System.out.println("Client: Port number " + portNumber);

        // Locate registry at IP address and port number
        try
        {
            registry = LocateRegistry.getRegistry(ipAddress, portNumber);
        }
        catch (RemoteException ex)
        {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
            registry = null;
        }
        // Print result locating registry
        if (registry != null)
        {
            System.out.println("Client: Registry located");
        }
        else
        {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: Registry is null pointer");
        }


        if (registry != null)
        {
            try
            {
                effectenbeurs = (IEffectenbeurs) registry.lookup(bindingName);
                bannerController.setEffectenbeurs(effectenbeurs);
                bannerController.addListener();
            }
            catch (RemoteException ex)
            {
                System.out.println("Client: Cannot bind student administration");
                System.out.println("Client: RemoteException: " + ex.getMessage());
                bannerController = null;
            }
            catch (NotBoundException ex)
            {
                System.out.println("Client: Cannot bind student administration");
                System.out.println("Client: NotBoundException: " + ex.getMessage());
                bannerController = null;
            }
        }

        // Print result binding student administration
        if (effectenbeurs != null)
        {
            System.out.println("Client: Effectenbeurs bound");
        }
        else
        {
            System.out.println();
            System.out.println("Client: IEffectenbeurs is null pointer");
        }

    }

    public IEffectenbeurs getEffectenbeurs(){
        return bannerController.getEffectenbeurs();
    }
}

