/*
Map of the first house in Amishville
 */
package Maps;

import Events.Teleport;


public class AmishH1 extends Map{

    
    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    public AmishH1()
    {
        super("GenericH1");
        
        eventArray[23][14] = new Teleport();
        eventArray[23][14].setCoord(5, 2, 12, 17);
        
    }
    
    
    
}
