/*
Map of the first house in Trump City
 */
package Maps;

import Events.Teleport;


public class TrumpH1 extends Map{

    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    public TrumpH1()
    {
        super("GenericH1");
        eventArray[23][14] = new Teleport();
        eventArray[23][14].setCoord(3, 5, 66, 28);
    }
    
    
}
