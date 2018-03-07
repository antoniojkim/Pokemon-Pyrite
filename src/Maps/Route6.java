/*
Map for Route 6
 */
package Maps;

import java.util.Arrays;


public class Route6 extends Map {
    
    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    public Route6 ()
    {
        super ("Route 6");
        shifts [1] = -24*32;
        shifts [3] = 4*32;
        
        bLevel = 23;
        hLevel = 29;
        
        grassPokemon = Arrays.asList("Shaymin (Land Forme)", "Magnemite","Jigglypuff","Teddiursa","Nuzleaf","Taillow","Makuhita", "Munna");
    }
}
