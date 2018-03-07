/*
Map for Route 7
 */
package Maps;

import java.util.Arrays;

public class Route7 extends Map {

    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
     */
    public Route7()
    {
        super("Route 7");
        shifts[1] = -4 * 32;
        shifts[3] = 4 * 32;
        shifts[2] = -8 * 32;

        bLevel = 18;
        hLevel = 39;

        grassPokemon = Arrays.asList("Growlithe", "Pinsir", "Eevee", "Sunkern", "Kecleon", "Riolu", "Patrat", "Cottonee", "Solosis");
    }
}
