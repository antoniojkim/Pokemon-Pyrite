/*
Map of Route 3
 */
package Maps;

import Events.Teleport;
import java.util.Arrays;


public class Route3 extends Map {

    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    public Route3()
    {
        super("Route 3");
        shifts[3] = 6 * 32;

        bLevel = 2;
        hLevel = 6;
        
        eventArray[21][11] = new Teleport();
        eventArray[21][11].setCoord(4, 3, 38, 21);
        
        grassPokemon = Arrays.asList(new String[]{"Pidgey", "Ekans", "Mew", "Nidoran M", "Nidoran F", "Ralts", "Blitzle"});
        waterPokemon = Arrays.asList(new String[]{"Magikarp", "Tirtouga", "Frillish"});
        fishPokemon = Arrays.asList(new String[]{"Magikarp"});
    }
}
