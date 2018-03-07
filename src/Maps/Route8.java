/*
Map for Route 8
 */
package Maps;

import Events.DumbNPC;
import java.util.Arrays;


public class Route8 extends Map {

    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    public Route8()
    {
        super("Route 8");
        shifts[1] = -4 * 32;
        shifts[3] = 2*32; 
        
        bLevel = 10;
        hLevel = 15;
        
        eventArray[35][38] = new DumbNPC(25, 1);
        eventArray[35][38].addText("I couldn't quite remember how to throw a boomerang, but eventually it came back to me.");
        
        grassPokemon = Arrays.asList("Sandshrew", "Larvitar", "Trapinch", "Baltoy", "Scraggy", "Axew", "Stunfisk","Maractus");
        waterPokemon = Arrays.asList("Magikarp", "Slowpoke","Seel", "Staryu");
        fishPokemon = Arrays.asList("Feebas", "Magikarp");
    }
}
