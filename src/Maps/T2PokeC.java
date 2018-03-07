/*
This is the map of the Pokemon Center in Town 2
 */
package Maps;

import Events.DumbNPC;
import Events.Nurse;
import Events.Teleport;




public class T2PokeC extends Map{
    
    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    public T2PokeC()
    {
        super("PokeCenter");
        eventArray [23][18] = new Teleport();
        eventArray [23][18].setCoord(3, 3, 19, 15);
        eventArray[14][18] = new Nurse();
        eventArray[18][14] = new DumbNPC(9, 3);
        eventArray[18][14].addText("I'm so tired that I can't count the French alphabet right now.");
        eventArray[14][24] = new DumbNPC(12, 0);
        eventArray[14][24].addText("I channel my Italian to make Asian food.");
    }
}
