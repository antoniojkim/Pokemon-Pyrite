/*
The map of the third house in Amulet Town
 */
package Maps;

import Events.Teleport;

public class AmuletH3 extends Map
{
    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    public AmuletH3 ()
    {
        super ("AT House3");
        eventArray[16][12] = new Teleport ();
        eventArray [16][12].setCoord(1, 3, 14, 4);
    }
}
