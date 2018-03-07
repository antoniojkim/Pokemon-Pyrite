/*
This is the map for the Amishville Pokemon Center
 */
package Maps;

import Events.Nurse;
import Events.Teleport;

public class AmishPokeC extends Map {

    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    public AmishPokeC()
    {
        super("PokeCenter");

        eventArray[23][18] = new Teleport();
        eventArray[23][18].setCoord(5, 2, 25, 7);

        eventArray[14][18] = new Nurse();
    }

}
