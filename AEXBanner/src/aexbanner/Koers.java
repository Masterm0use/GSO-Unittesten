/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner;

import java.io.Serializable;

/**
 *
 * @author Mastermouse
 */
public class Koers implements IFonds, Serializable{
    String naam;
    double koers;

    Koers(String naam, double koers)
    {
        this.naam = naam;
        this.koers = koers;
    }
    
    @Override
    public String getNaam() {
        return this.naam;
  
    }

    @Override
    public double getKoers() {
        return this.koers;
    }
    
    @Override
    public String toString(){
        return naam + " : " + koers;
    }
}