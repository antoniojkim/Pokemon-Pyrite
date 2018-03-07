/*
Map of first house in Town 2
 */
package Maps;

import Events.DumbNPC;
import Events.Teleport;


public class Town2H1 extends Map{
    
    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    public Town2H1 ()
    {
        super("GenericH1");
        eventArray[23][14] = new Teleport();
        eventArray[23][14].setCoord(3, 3, 6, 22);
        eventArray[18][20] = new DumbNPC(24, 0);
        eventArray[18][20].addText("Yesterday I accidentally swallowed some food coloring. The doctor says I'm OK, but I feel like I've dyed a little inside.");
        eventArray[18][20].addText("I'm sorry...NOT! ");
    }
}
