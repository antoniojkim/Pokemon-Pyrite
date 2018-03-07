/*
Map for Route 5
 */
package Maps;

import Events.DumbNPC;
import Events.Sign;
import java.util.Arrays;


public class Route5 extends Map
{
    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    public Route5 ()
    {
        super ("Route 5");
        shifts[2] = -8*32;
        shifts[0] = 20*32;
        bLevel = 11;
        hLevel = 17;
        
        eventArray[44][14] = new DumbNPC(0, 0);
        eventArray[44][14].addText("In order to understand recursion you must first understand recursion.");
        eventArray[45][16] = new Sign();
        eventArray[45][16].addText("UNDER CONSTRUCTION (It'll be something one day...)");
                
        grassPokemon = Arrays.asList("Rotom", "Pansage", "Pansear", "Panpour", "Sewaddle", "Bellsprout", "Cherubi", "Solosis");
        waterPokemon = Arrays.asList("Poliwag","Wingull","Surskit","Azurill","Mantyke");
        fishPokemon = Arrays.asList("Feebas","Poliwag");
    }
}
