/*
This is the map for the first house in Amulet Town
 */
package Maps;

import Events.Teleport;

public class AmuletH2 extends Map
{
    public AmuletH2 ()
    {
     /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
        super ("AT House2");
        eventArray [12][21] = new Teleport();
        eventArray [12][21].setCoord(9, 9, 21, 12);
    }
}
