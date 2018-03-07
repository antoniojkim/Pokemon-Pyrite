/*
This is the map for the area under Town 2
 */
package Maps;

import Events.DumbNPC;
import Events.Sign;


public class Mystery extends Map{

    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    public Mystery()
    {
        super("Mystery");
        
        eventArray[4][13] = new Sign();
        eventArray[4][13].addText("A card reads: My fear of roses is a thorny issue. I'm not sure what it stems from, but it seems likely I'll be stuck with it.");
    }
    
    
    
}
