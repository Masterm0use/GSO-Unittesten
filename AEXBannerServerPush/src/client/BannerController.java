/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import client.AEXBanner;
import fontyspublisher.IRemotePropertyListener;
import fontyspublisher.IRemotePublisherForListener;
import interfaces.IFonds;
import java.beans.PropertyChangeEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mario, Robin
 */
public class BannerController extends UnicastRemoteObject implements IRemotePropertyListener {

	private AEXBanner banner;
	public Facade facade;

	public BannerController(AEXBanner banner) throws RemoteException {
		this.banner = banner;
		facade = new Facade();
		facade.subscribeRemoteListener(this, "fondsen");
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) throws RemoteException {
		List<IFonds> newFondsen = (List<IFonds>) evt.getNewValue();
		if (evt.getPropertyName().equals("fondsen")){
			String fondsen = "";
			for (IFonds fonds : newFondsen){
				fondsen += String.format("%1$s : %2$.2f  /  ", fonds.getNaam(), fonds.getKoers());
				banner.setKoersen(fondsen);
			}
		}
	}
}