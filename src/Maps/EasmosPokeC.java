/*
This is the map for the pokemon center in Easmos
 */
package Maps;

import Events.Nurse;
import Events.Teleport;


public class EasmosPokeC extends Map {

    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    public EasmosPokeC()
    {
        super("PokeCenter");
        eventArray[23][18] = new Teleport();
        eventArray[23][18].setCoord(8, 5, 4, 23);
        eventArray[14][18] = new Nurse();
    }

}
