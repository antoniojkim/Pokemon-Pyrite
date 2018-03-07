/*
Map for Route 11
 */
package Maps;

import java.util.Arrays;


public class Route11 extends Map
{
    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    public Route11 ()
    {
        super ("Route 11");
        shifts [2] = 8*32;
                bLevel = 2;
        hLevel = 6;
        grassPokemon = Arrays.asList(new String [] {"Pidgey", "Ekans", "Pichu","Nidoran M", "Nidoran F","Ralts", "Blitzle"});
        waterPokemon = Arrays.asList(new String [] {"Magikarp", "Tirtouga", "Frillish"});
        fishPokemon = Arrays.asList(new String [] {"Magikarp"});
        
    }
}
