/*
This is the map for the first house in Amulet Town
 */
package Maps;

import Events.Teleport;

public class AmuletH1 extends Map {

    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    public AmuletH1()
    {
        super("AT House1");

        eventArray[17][13] = new Teleport();
        eventArray[17][13].setCoord(1, 3, 14, 10);
        eventArray[12][21] = new Teleport();
        eventArray[12][21].setCoord(10, 10, 21, 12);
    }

}
