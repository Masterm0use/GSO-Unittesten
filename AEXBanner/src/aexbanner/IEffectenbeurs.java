/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner;

import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Mastermouse
 */
public interface IEffectenbeurs {
    public List<IFonds> getKoersen() throws RemoteException;;
}
