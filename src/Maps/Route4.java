/*
Map of Route 4
 */
package Maps;

import Events.Teleport;
import java.util.Arrays;


public class Route4 extends Map {

    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    public Route4()
    {
        super("Route 4");
        shifts[0] = 7 * 32;
        shifts[2] = 10 * 32;
        bLevel = 2;
        hLevel = 6;

        eventArray[17][13] = new Teleport();
        eventArray[17][13].setCoord(5, 3, 23, 42);
        eventArray[42][22] = new Teleport();
        eventArray[42][22].setCoord(5, 3, 12, 17);

        eventArray[25][30] = new Teleport();
        eventArray[25][30].setCoord(5, 3, 54, 33);
        eventArray[33][54] = new Teleport();
        eventArray[33][54].setCoord(5, 3, 30, 25);

        grassPokemon = Arrays.asList(new String[]{"Pidgey", "Ekans", "Pichu", "Nidoran M", "Nidoran F", "Ralts", "Blitzle"});
        waterPokemon = Arrays.asList(new String[]{"Magikarp", "Tirtouga", "Frillish"});
        fishPokemon = Arrays.asList(new String[]{"Magikarp"});

    }
}
