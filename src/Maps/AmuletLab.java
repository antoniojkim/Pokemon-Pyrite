/*
This is the map for the Lab in Amulet Town
 */
package Maps;

import Events.Teleport;


public class AmuletLab extends Map{
    
    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    public AmuletLab ()
    {
        super ("Amulet Lab");
        eventArray [27][21] = new Teleport();
        eventArray [27][21].setCoord(1, 3, 29, 11);
    }
}
