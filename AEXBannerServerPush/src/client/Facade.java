/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import fontyspublisher.IRemotePropertyListener;
import fontyspublisher.IRemotePublisherForListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.RMIServer;

/**
 *
 * @author Mario, Robin
 */
public class Facade implements IRemotePublisherForListener {
	
	private IRemotePublisherForListener publisher;
	
	public Facade() throws RemoteException {
		Registry reg = LocateRegistry.getRegistry("localhost", RMIServer.portNumber);
		try {
			publisher = (IRemotePublisherForListener) reg.lookup(RMIServer.bindingName);
		} catch (NotBoundException ex) {
			Logger.getLogger(BannerController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void subscribeRemoteListener(IRemotePropertyListener listener, String property) throws RemoteException {
		publisher.subscribeRemoteListener(listener, property);
	}

	@Override
	public void unsubscribeRemoteListener(IRemotePropertyListener listener, String property) throws RemoteException {
		publisher.unsubscribeRemoteListener(listener, property);
	}
}
