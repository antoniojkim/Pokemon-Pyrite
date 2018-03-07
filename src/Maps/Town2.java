/*
This is the map of Town 2
 */
package Maps;

import Events.DumbNPC;
import Events.Sign;
import Events.Teleport;
import java.util.Arrays;


public class Town2 extends Map{
 
    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    public Town2 () 
    {
        super ("Town 2");
        shifts [1] = -9*32;
        shifts [3] = 11*32;
        shifts [0] = 8*32;
        
        bLevel = 5;
        hLevel = 10;
        
        waterPokemon = Arrays.asList("Magikarp");
        
        eventArray[14][19] = new Teleport();
        eventArray[14][19].setCoord(9, 7, 18, 22);
        eventArray[17][17] = new DumbNPC(14, 0);
        eventArray[17][17].addText("Don't let your memes be dreams. JUST DO IT!");
        eventArray[4][5] = new DumbNPC(23, 1);
        eventArray[4][5].addText("I can't believe I got fired from the calendar factory. All I did was take a day off.");
        eventArray[21][12] = new DumbNPC(17, 0);
        eventArray[21][12].addText("I started a band called 1023 Megabytes - we never got a gig.");
        eventArray[21][6] = new Teleport();
        eventArray[21][6].setCoord(10, 4, 14, 22);
        eventArray[14][11] = new Teleport();
        eventArray[14][11].setCoord(11, 1, 14, 22);
        eventArray[14][25] = new Teleport();
        eventArray[14][25].setCoord(9, 3, 14, 19);
        eventArray[5][14] = new Sign();
        eventArray[5][14].addText("Closed because gym-leader is out wandering with random trainer in different region.");
        
        
    }
}
