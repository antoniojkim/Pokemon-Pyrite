/*
Map of Route 9
 */
package Maps;

import java.util.Arrays;


public class Route9 extends Map {

    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    public Route9()
    {
        super("Route 9");
        shifts[1] = -2 * 32;
        shifts[3] = -12 * 32;
        
        bLevel = 31;
        hLevel = 41;
        
        grassPokemon = Arrays.asList("Farfetch'd", "Scyther", "Sudowoodo", "Celebi","Emolga");
        
        
    }
}
