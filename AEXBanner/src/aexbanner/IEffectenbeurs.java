/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Mastermouse
 */
public interface IEffectenbeurs extends Remote {
    public List<IFonds> getKoersen() throws RemoteException;
	public int getRandomKoers() throws RemoteException;
	public Koers setKoers(String naam, int koers) throws RemoteException;
}