/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mastermouse
 */
public class Effectenbeurs implements IEffectenbeurs, Serializable
{
    private ArrayList<IFonds> fondsen;

    @Override
    public ArrayList<IFonds> getKoersen() {
        return fondsen;
    }   
}
