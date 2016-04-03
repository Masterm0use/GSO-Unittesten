/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aexbanner;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mastermouse
 */
public class MockEffectenbeurs implements IEffectenbeurs {
    
    @Override
    public List<IFonds> getKoersen() {

        ArrayList<IFonds> fonds = new ArrayList<>();
        
        fonds.add(new Fonds("InstaRich", 100.666));
        fonds.add(new Fonds("5Euro?", 64.6464));
        fonds.add(new Fonds("Boem", 2.033));
        return fonds;
    }

    @Override
    public String toString() {
        
        StringBuilder stringding = new StringBuilder();
        
        for (IFonds fond : getKoersen()) {
            stringding.append(fond.getNaam());
            stringding.append(" # ");
            stringding.append(String.valueOf(fond.getKoers()));
            stringding.append(" | ");
        }
        return stringding.toString();
    }
    
}
