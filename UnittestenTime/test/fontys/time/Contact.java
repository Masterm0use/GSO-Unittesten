/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

import java.util.ArrayList;

/**
 *
 * @author Robin de Kort
 */
public class Contact {
    
    private String name;
    private ArrayList appointments;
    
    public Contact(String name){
        this.name = name;
        appointments = new ArrayList();
    }
    
    public String getName(){
        return name;
    }
}
