/*
Map of the Trump City Pokemon Center
 */
package Maps;

import Events.Nurse;
import Events.Teleport;


public class TrumpPokeC extends Map {

    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    public TrumpPokeC()
    {
        super("PokeCenter");

        eventArray[23][18] = new Teleport();
        eventArray[23][18].setCoord(3, 5, 61, 48);

        eventArray[14][18] = new Nurse();
    }

}
