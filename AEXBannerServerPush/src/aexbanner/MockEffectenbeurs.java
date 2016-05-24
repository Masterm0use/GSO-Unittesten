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
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mario, Robin
 */
public class MockEffectenbeurs implements IEffectenbeurs {

	private List<IFonds> fondsen;
	private RemotePublisher remPublisher;

	@Override
	public List<IFonds> getFondsen(){
		return fondsen;
	}
	
	public RemotePublisher getPublisher(){
		return remPublisher;
	}

	// Constructor
	public MockEffectenbeurs() throws RemoteException {
		fondsen = new ArrayList();
		remPublisher = new RemotePublisher();
		remPublisher.registerProperty("fondsen");
		startTimer();
	}
	
	private void startTimer(){
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask(){
			// Timer will do this after 0 milliseconds, every 1000 milliseconds
			@Override
			public void run() {
				List<IFonds> oudeFondsen = fondsen;
				fondsen = generateRandomFondsen();
				System.out.println(fondsen);
				try {
					remPublisher.inform("fondsen", oudeFondsen, fondsen);
				} catch (RemoteException ex) {
					Logger.getLogger(MockEffectenbeurs.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}, 0, 1000);
	}
	
	private List<IFonds> generateRandomFondsen(){
		List<IFonds> fondsen = new ArrayList();
		String[] fondsNamen = new String[]{"Kappa", "Keepo", "PogChamp"};
		
		for (String fondsNaam : fondsNamen){
			fondsen.add(new Fonds(fondsNaam, generateRandomFondsWaarde()));
		}
		
		return fondsen;
	}

	public double generateRandomFondsWaarde() {
		Random random = new Random();
		
		double minKoers = 10;
		double maxKoers = 100;
		double randomKoers = minKoers + (maxKoers - minKoers) * random.nextDouble();
		
		return randomKoers;
	}
}
