/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner;

import fontyspublisher.RemotePublisher;
import interfaces.IEffectenbeurs;
import interfaces.IFonds;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Mario, Robin
 */
public class Effectenbeurs implements IEffectenbeurs {

	private List<IFonds> fondsen;

	@Override
	public List<IFonds> getFondsen() {
		return fondsen;
	}

	// Constructor
	public Effectenbeurs() throws RemoteException {
		fondsen = new ArrayList();
	}
}
