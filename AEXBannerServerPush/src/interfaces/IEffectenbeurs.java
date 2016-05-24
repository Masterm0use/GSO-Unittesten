/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import aexbanner.Fonds;
import java.rmi.Remote;
import java.util.List;

/**
 *
 * @author Mario, Robin
 */
public interface IEffectenbeurs extends Remote {

	List<IFonds> getFondsen();
}
