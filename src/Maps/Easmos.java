/*
This is the map for Easmos Town
 */
package Maps;

import Events.DumbNPC;
import Events.Teleport;


public class Easmos extends Map{
    
    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    
    public Easmos ()
    {
        super ("Easmos");
        shifts[1] = 12*32;
        
        eventArray[25][19] = new DumbNPC(26, 0);
        eventArray[25][19].addText("I wasn't originally going to get a brain transplant, but then I changed my mind.");
        eventArray[32][19] = new DumbNPC(0, 0);
        eventArray[32][19].addText("I was called to a daycare where a three-year-old was resisting a rest.");
        eventArray[29][4] = new DumbNPC(20, 0);
        eventArray[29][4].addText("Have you ever tried to eat a clock? It's very time consuming.");
        eventArray[32][18] = new DumbNPC(1, 0);
        eventArray[32][18].addText("Television is a medium because anything well done is rare.");
        eventArray[23][10] = new DumbNPC(18, 0);
        eventArray[23][10].addText("A flat rate is the monthly rent for an apartment. Hehe...I wish I hadn't forgotten to pay rent...");
        
        eventArray[22][4] = new Teleport();
        eventArray[22][4].setCoord(11, 1, 18, 22);
    }
}
