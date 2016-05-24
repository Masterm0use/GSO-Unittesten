/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner;

import interfaces.IFonds;
import java.io.Serializable;

/**
 *
 * @author Mario, Robin
 */
public class Fonds implements IFonds, Serializable {

	private String naam;
	private double koers;

	public Fonds(String naam, double koers) {
		this.naam = naam;
		this.koers = koers;
	}

	@Override
	public String getNaam() {
		return naam;
	}

	@Override
	public double getKoers() {
		return koers;
	}

	@Override
	public String toString() {
		return naam + " : " + koers;
	}
}
