/*
Map for Route 12
 */
package Maps;

import java.util.Arrays;


public class Route12 extends Map {

    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    Route12()
    {
        super("Route 12");
        shifts[0] = 8 * 32;
        shifts[2] = -7*32;
                bLevel = 2;
        hLevel = 6;
        grassPokemon = Arrays.asList(new String [] {"Pidgey", "Ekans", "Pichu","Nidoran M", "Nidoran F","Ralts", "Blitzle"});
        waterPokemon = Arrays.asList(new String [] {"Magikarp", "Tirtouga", "Frillish"});
        fishPokemon = Arrays.asList(new String [] {"Magikarp"});
        
    }
}
