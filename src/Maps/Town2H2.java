/*
Map of the second house in Town 2
 */
package Maps;

import Events.Teleport;


public class Town2H2 extends Map{
    
    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    public Town2H2()
    {
        super("GenericH2");
        eventArray[23][14] = new Teleport();
        eventArray[23][14].setCoord(3, 3, 11, 15);
    }
    
}
